package com.tbl.problemsolvers.LeaderBoard;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.imageview.ShapeableImageView;
import com.tbl.problemsolvers.R;
import com.tbl.problemsolvers.utils.MiscUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.nameTV)
    TextView nameTxt;

    @BindView(R.id.coinTV)
    TextView coinTxt;

    @BindView(R.id.rankingTV)
    TextView rankingTV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.srTV)
    TextView srTV;

    @BindView(R.id.rrTV)
    TextView rrTV;

    @BindView(R.id.lpscTV)
    TextView lpscTV;

    @BindView(R.id.non_BayerTV)
    TextView non_BayerTV;

    @BindView(R.id.spcialSkuTV)
    TextView spcialSkuTV;

    @BindView(R.id.growthStarTV)
    TextView growthStarTV;

    @BindView(R.id.edit_profile_picture_button)
    ImageView editImageView;

    @BindView(R.id.profile_picture)
    ShapeableImageView profilePic;

    Uri masterUri = null;
    String masterName = "";
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE",};
    private MiscUtil miscUtil;
    private static final int RC_CAMERA_STORAGE = 1001;
    Bitmap myBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        miscUtil = new MiscUtil();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar.setNavigationIcon(R.drawable.ic_menu);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.edit_profile_picture_button)
    public void takePicture(View view) {
        if (allPermissionsGranted()) {
            chooseImage();
        }
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
                    ActivityCompat.requestPermissions(ProfileActivity.this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
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
                        profilePic.setImageURI(masterUri);
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
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                myBitmap = BitmapFactory.decodeFile(picturePath);
                                profilePic.setImageBitmap(myBitmap);
                                cursor.close();
                            }
                        }
                    }
                }
            });

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        //File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        Log.e("kitkat", "createImageFile: " + storageDir);

        ifp = image.getAbsolutePath();

        return image;
    }


    /**
     * Required for permissions
     */
    private void methodRequiresTwoPermission() {

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "Camera and Gallery required",
                    RC_CAMERA_STORAGE, perms);
        }
    }

    /**
     * Required for permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}