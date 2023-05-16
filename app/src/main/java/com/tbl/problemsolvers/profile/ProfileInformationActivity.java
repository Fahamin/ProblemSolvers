package com.tbl.problemsolvers.profile;

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
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class ProfileInformationActivity extends AppCompatActivity {

    @BindView(R.id.psridET)
    EditText psridET;

    @BindView(R.id.codeET)
    EditText codeET;

    @BindView(R.id.nameET)
    EditText nameET;

    @BindView(R.id.presentAddressET)
    EditText presentAddressET;

    @BindView(R.id.permanentAddressET)
    EditText permanentAddressET;

    @BindView(R.id.fatherNameET)
    EditText fatherNameET;

    @BindView(R.id.motherNameET)
    EditText motherNameET;

    @BindView(R.id.personalMobilET)
    EditText personalMobilET;

    @BindView(R.id.officialMobileET)
    EditText officialMobileET;

    @BindView(R.id.dobET)
    EditText dobET;

    @BindView(R.id.religionET)
    EditText religionET;

    @BindView(R.id.maritalStatusET)
    EditText maritalStatusET;

    @BindView(R.id.emergencyContactET)
    EditText emergencyContactET;

    @BindView(R.id.psrImageIV)
    ImageView psrImageIV;

    Uri masterUri = null;
    String picturePath;
    String masterName = "";
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE",};
    private MiscUtil miscUtil;
    private static final int RC_CAMERA_STORAGE = 1001;
    Bitmap myBitmap;
    ArrayList<String> checkValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);
        ButterKnife.bind(this);
        miscUtil = new MiscUtil(this, this);

    }

    @OnClick(R.id.psrTakeImageBtn)
    public void takeImage() {
        chooseImage();
    }

    private void CreateJson() {

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("PsrId", "" + psridET.getText().toString());
            jsonObject.put("Code", "" + codeET.getText().toString());
            jsonObject.put("Name", "" + nameET.getText().toString());
            jsonObject.put("PresentAddress", presentAddressET.getText().toString());
            jsonObject.put("PermanentAddress", "" + permanentAddressET.getText().toString());
            jsonObject.put("FatherName", "" + fatherNameET.getText().toString());
            jsonObject.put("MotherName", "" + motherNameET.getText().toString());
            jsonObject.put("Personal Mobile", "" + personalMobilET.getText().toString());
            jsonObject.put("OfficialMobile", "" + officialMobileET.getText().toString());
            jsonObject.put("DOB", "" + dobET.getText().toString());
            jsonObject.put("Religion", "" + religionET.getText().toString());
            jsonObject.put("MaritalStatus", "" + maritalStatusET.getText().toString());
            jsonObject.put("EmergencyContactNumber", "" + emergencyContactET.getText().toString());
            jsonObject.put("Image", "" + imageConverter(masterName));


        } catch (Exception e) {

        }
        Log.e("json", jsonObject.toString());
        finish();
    }


    boolean isValid() {
        checkValid = new ArrayList<>();

        if (psridET.getText().toString().length() <= 0) {
            checkValid.add("PsrId ");
        }
        if (codeET.getText().toString().length() <= 0) {
            checkValid.add("Code ");
        }
        if (nameET.getText().toString().length() <= 0) {
            checkValid.add("Name ");
        }
        if (presentAddressET.getText().toString().length() <= 0) {
            checkValid.add("PresentAddress ");
        }
        if (permanentAddressET.getText().toString().length() <= 0) {
            checkValid.add("PermanentAddress ");
        }
        if (fatherNameET.getText().toString().length() <= 0) {
            checkValid.add("FatherName ");
        }
        if (motherNameET.getText().toString().length() <= 0) {
            checkValid.add("MotherName");
        }
        if (personalMobilET.getText().toString().length() <= 0) {
            checkValid.add("Personal Mobile");
        }
        if (officialMobileET.getText().toString().length() <= 0) {
            checkValid.add("OfficialMobile");
        }
        if (dobET.getText().toString().length() <= 0) {
            checkValid.add("DOB");
        }
        if (religionET.getText().toString().length() <= 0) {
            checkValid.add("Religion");
        }
        if (maritalStatusET.getText().toString().length() <= 0) {
            checkValid.add("MaritalStatus");
        }
        if (emergencyContactET.getText().toString().length() <= 0) {
            checkValid.add("EmergencyContactNumber");
        }
        if (masterUri == null && picturePath == null) {
            checkValid.add("Capture image");
        }

        if (checkValid.size() <= 0) {
            return true;
        } else {
            return false;
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
                    ActivityCompat.requestPermissions(ProfileInformationActivity.this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
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
                        psrImageIV.setImageURI(masterUri);
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
                                picturePath = cursor.getString(columnIndex);
                                myBitmap = BitmapFactory.decodeFile(picturePath);
                                psrImageIV.setImageBitmap(myBitmap);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {

            if (isValid()) {
                CreateJson();
            } else {

                String Msg = "";
                for (int i = 0; i < checkValid.size(); i++) {
                    Msg = Msg + checkValid.get(i) + "\n";
                }

                miscUtil.showAlertDialogDefault("Warning", "Please Enter\n" + Msg + "");
            }

        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private String imageConverter(String imageName) {

        try {

            String imageFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "";

            File imagePath = new File(imageFilePath, imageName);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath.getAbsolutePath());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // In case you want to compress your image, here it's at 40%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);

            byte[] byteArray = byteArrayOutputStream.toByteArray();

            try {
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }
                Runtime.getRuntime().gc();
            } catch (Exception e) {
            }


            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            return "";
        }
    }

}