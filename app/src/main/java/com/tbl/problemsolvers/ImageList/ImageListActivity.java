package com.tbl.problemsolvers.ImageList;

import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.tbl.problemsolvers.R;
import com.tbl.problemsolvers.utils.MiscUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ImageListActivity extends AppCompatActivity {

    Button imageAdd;
    Uri masterUri = null;
    String masterName = "";
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE",};
    private MiscUtil miscUtil;
    private static final int RC_CAMERA_STORAGE = 1001;
    Bitmap myBitmap;

    List<Uri> imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imageUri = new ArrayList<>();
        imageAdd = findViewById(R.id.btn_AddImage);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allPermissionsGranted()) {
                    chooseImage();
                }
            }
        });
    }

    private void chooseImage() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_choice);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Button btnCancel = dialog.findViewById(R.id.btnCancle);
        LinearLayout cameraIv, galleryIv;

        cameraIv = dialog.findViewById(R.id.ivCamera);
        galleryIv = dialog.findViewById(R.id.ivGallary);

        cameraIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allPermissionsGranted()) {
                    startCamera();
                    dialog.dismiss();

                    //start camera if permission has been granted by user
                } else {
                    methodRequiresTwoPermission();
                    ActivityCompat.requestPermissions(ImageListActivity.this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
                    dialog.dismiss();

                }
            }
        });

        galleryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allPermissionsGranted()) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    imagePickerFromGallaryLuncher.launch(pickPhoto);
                    dialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Check Permission", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void startCamera() {
        masterName = "";
        /*Intent intent = new Intent(this, CameraPreviewActivity.class);
        startActivity(intent);*/
        String file_name = miscUtil.getTimestamp();
        masterName = file_name + ".jpg";
        //Log.e("mahtab", "startCamera: " + masterName);
        Intent m_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            File wallpaperDirectory = new File(folderPath);
            wallpaperDirectory.mkdirs();
        }
        //File file = new File(folderPath, file_name + ".jpg");
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            //openCameraIntent();
            Intent pictureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(pictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                //Create a file to store the image
                File photoFile = null;
                try {
                    photoFile = new File(folderPath, masterName);
                } catch (Exception ex) {
                    // Error occurred while creating the File
                    Log.e("kitkat", "openCameraIntent: error " + ex.getMessage());

                }
                if (photoFile != null) {
                    masterUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            masterUri);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        grantUriPermission(packageName, masterUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    imagePickerLuncher.launch(pictureIntent);

                }
            }

        } else {
            File file = new File(folderPath, masterName);
            masterUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
            m_intent.putExtra(MediaStore.EXTRA_OUTPUT, masterUri);
            m_intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //m_intent.putExtra("outFile", "filee");
            imagePickerLuncher.launch(m_intent);
        }

    }

    String ifp;
    ActivityResultLauncher<Intent> imagePickerLuncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        imageUri.add(masterUri);
                    }
                }
            });

    // Luncher  imagepick from gallary
    ActivityResultLauncher<Intent> imagePickerFromGallaryLuncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri selectedImage = data.getData();
                        imageUri.add(selectedImage);
                    }
                }
            });

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @AfterPermissionGranted(RC_CAMERA_STORAGE)
    private void methodRequiresTwoPermission() {
        if (SDK_INT >= 33) {
            startCamera();
        } else {
            if (allPermissionsGranted()) {

                startCamera(); //start camera if permission has been granted by user
            } else {
                EasyPermissions.requestPermissions(this, "Camera and Gallery required",
                        REQUEST_CODE_PERMISSIONS, REQUIRED_PERMISSIONS);
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            }


        }
    }
}