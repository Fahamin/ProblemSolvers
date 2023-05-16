package com.tbl.problemsolvers;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.gson.Gson;
import com.tbl.problemsolvers.UiForm.QuestionAnswer;
import com.tbl.problemsolvers.UiForm.UiForm;
import com.tbl.problemsolvers.utils.LuncherActivityResult;
import com.tbl.problemsolvers.utils.MiscUtil;
import com.tbl.problemsolvers.utils.TinyDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SteperQuestion extends AppCompatActivity {
    String responseS = "{\n" +
            "  \"Message\": \"Success\",\n" +
            "  \"Result\": {\n" +
            "    \"ID\": 2,\n" +
            "    \"Name\": \"Personal Information\",\n" +
            "    \"QuestionAnswer\": [\n" +
            "      {\n" +
            "        \"Id\": 38,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-1\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"TEXTBOX\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"Id\": 39,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-2\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"TEXTBOX\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 2\n" +
            "      },\n" +
            "      {\n" +
            "        \"Id\": 40,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-3\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"SELECT\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "         \"YES\",\n" +
            "          \"NO\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"Id\": 41,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-4\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"TEXTBOX\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 1,\n" +
            "        \"SL\": 4\n" +
            "      },\n" +
            "      {\n" +
            "        \"Id\": 42,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-5\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"RADIO\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"A\",\n" +
            "          \"B\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 1,\n" +
            "        \"SL\": 5\n" +
            "      },\n" +
            "      {\n" +
            "        \"Id\": 44,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-6\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"CHECKBOX\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"YES\",\n" +
            "          \"NO\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 6\n" +
            "      },\n" +
            "     \n" +
            "      {\n" +
            "        \"Id\": 47,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-9\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"IMAGE\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 9\n" +
            "      }\n" +
            ",\n" +
            "     \n" +
            "      {\n" +
            "        \"Id\": 48,\n" +
            "        \"SurveyId\": 2,\n" +
            "        \"Question\": \"Question-10\",\n" +
            "        \"Answer\": \"\",\n" +
            "        \"AnswerType\": \"IMAGE\",\n" +
            "        \"AnswerTypeInfo\": [\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"AnswerPlaceholder\": \"\",\n" +
            "        \"AnswerRequered\": 0,\n" +
            "        \"SL\": 10\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";


    UiForm data;

    MiscUtil miscUtil;
    private int REQUEST_CODE_PERMISSIONS = 1001;

    //Create a layout---------------
    LinearLayout linearLayout;
    List<QuestionAnswer> qa;
    LinearLayout.LayoutParams params1;
    LinearLayout.LayoutParams params2;
    View view;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    TinyDB tinyDB;
    List<String> checkValid = new ArrayList<>();
    int id;
    String type, address, lat, lon, create_date;
    protected final LuncherActivityResult<Intent, ActivityResult> activityLauncher = LuncherActivityResult.registerActivityForResult(this);

    int current_step = 0;
    int MAX_STEP;

    @BindView(R.id.tv_steps)
    TextView tvStepNum;


    @BindView(R.id.btnSaveview)
    Button saveBtn;


    List<QuestionAnswer> newList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steper_question);
        ButterKnife.bind(this);

        miscUtil = new MiscUtil(this, SteperQuestion.this);
        tinyDB = new TinyDB(this);
//        Log.e("UiForm", "" + data.getResult().getName());

        //Create a layout---------------
        linearLayout = findViewById(R.id.llview);
        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        data = new Gson().fromJson(responseS, UiForm.class);
        qa = data.getResult().getQuestionAnswer();
        newList = new ArrayList<>();

        MAX_STEP = qa.size() - 1;
        String str_progress = String.format(getString(R.string.step_of), current_step + 1, MAX_STEP + 1);
        tvStepNum.setText(str_progress);
        generateLayout(current_step);


    }

    @OnClick(R.id.lyt_back)
    public void backStep() {
        backStep(current_step);
    }

    @OnClick(R.id.lyt_next)
    public void nextStep() {
        QuestionAnswer answer = new QuestionAnswer();
        answer.setAnswer("" + getAnswer(qa.get(current_step)));
        answer.setId(qa.get(current_step).getId());
        answer.setQuestion(qa.get(current_step).getQuestion());
        answer.setAnswerType(qa.get(current_step).getAnswerType());
        answer.setAnswerTypeInfo(qa.get(current_step).getAnswerTypeInfo());

        newList.add(answer);

        Log.e("ans", newList.get(current_step).getAnswer());

        nextStep(current_step);
    }

    private void nextStep(int progress) {
        if (progress < MAX_STEP) {
            progress++;
            current_step = progress;
            String str_progress = String.format(getString(R.string.step_of), current_step + 1, MAX_STEP + 1);
            tvStepNum.setText(str_progress);
            generateLayout(current_step);
        }
        if (progress == MAX_STEP) {
            saveBtn.setVisibility(View.VISIBLE);
        } else {
            saveBtn.setVisibility(View.GONE);

        }
    }

    private void backStep(int progress) {
        if (progress > 0) {
            progress--;
            current_step = progress;

            String str_progress = String.format(getString(R.string.step_of), current_step + 1, MAX_STEP + 1);

            tvStepNum.setText(str_progress);
            generateLayout(current_step);
        }

        if (progress == MAX_STEP) {
            saveBtn.setVisibility(View.VISIBLE);
        } else {
            saveBtn.setVisibility(View.GONE);

        }
    }

    @OnClick(R.id.btnSaveview)
    public void SaveBtn() {
        for (int i = 0; i < newList.size(); i++) {
            Log.e("ansNew", newList.get(i).getQuestion() + " : " + newList.get(i).getAnswer());
        }
    }

    public Boolean checkValidiaty() {
        List<QuestionAnswer> qa = data.getResult().getQuestionAnswer();
        for (int i = 0; i < qa.size(); i++) {

            QuestionAnswer answer = qa.get(i);
            if (qa.get(i).getAnswerType().equals("TEXTBOX")) {

                EditText etans = findViewById(qa.get(i).getId());
                if (etans.getText().toString().isEmpty()) {
                    checkValid.add("check Textbox");
                }
            }

            if (qa.get(i).getAnswerType().equals("SELECT")) {

                Spinner mySpinner = findViewById(qa.get(i).getId());
                String value = mySpinner.getSelectedItem().toString();
                if (value.isEmpty())
                    checkValid.add("check Spiner");

            }

            if (qa.get(i).getAnswerType().equals("RADIO")) {
                RadioGroup rgitem = findViewById(qa.get(i).getId());
                int position = rgitem.indexOfChild(findViewById(rgitem.getCheckedRadioButtonId()));
                if (position == -1) {
                    checkValid.add("check RadioBtn");

                }
            }

            if (qa.get(i).getAnswerType().equals("CHECKBOX")) {
                final List<String> AnserList = qa.get(i).getAnswerTypeInfo();
                String value = "";
                for (int j = 0; j < AnserList.size(); j++) {

                    int id = Integer.parseInt("" + qa.get(i).getId() + "" + j);
                    CheckBox checkBox = findViewById(id);
                    if (checkBox.isChecked()) {
                        value = value + AnserList.get(j) + ";";

                    }
                }

                if (value.isEmpty()) {
                    checkValid.add("CHECK Checkbox");
                }
            }

            if (qa.get(i).getAnswerType().equals("RATINBAR")) {
                int id = Integer.parseInt("" + qa.get(i).getId());
                RatingBar ratingBar = findViewById(id);
                String value = "" + ratingBar.getNumStars();
                if (value.isEmpty())
                    checkValid.add("check Ratingbar");
            }

            if (qa.get(i).getAnswerType().equals("IMAGE")) {
                String value = "" + getImageUrl(qa.get(i).getId());
                if (value.isEmpty())
                    checkValid.add("Add Image");
            }
        }


        if (checkValid.size() <= 0) {
            return true;
        } else {
            return false;
        }

    }



    // Get Answer from Question Here
    public String getAnswer(QuestionAnswer qadata) {

        Log.e("UIQA", qadata.getAnswerType() + ":" + qadata.getQuestion());

        switch (qadata.getAnswerType()) {
            case "TEXTBOX":
                return edittextAnswer(qadata.getId());

            case "RATINBAR":
                return ratingBarAnswer(qadata.getId());

            case "SELECT":
                return spinerAnswer(qadata.getId());

            case "RADIO":
                return radioGroupAnswer(qadata.getId(), qadata.getAnswerTypeInfo());

            case "CHECKBOX":
                return checkBoxAnswer(qadata.getId(), qadata.getAnswerTypeInfo());

            case "IMAGE":
                return getImageUrl(qadata.getId());

        }

        return "object";
    }

    private void setImageUrl(Integer id, String picPath) {
        tinyDB.putString("" + id, picPath);
    }

    private String getImageUrl(Integer id) {
        String value = "" + tinyDB.getString("" + id);
        return value;
    }

    public String edittextAnswer(int id) {
        EditText etans = findViewById(id);
        String value = "" + etans.getText();
        return value;
    }

    public String ratingBarAnswer(int id) {
        RatingBar ratingBar = findViewById(id);
        String value = "" + ratingBar.getNumStars();
        return value;
    }

    public String spinerAnswer(int id) {
        Spinner mySpinner = findViewById(id);
        String value = mySpinner.getSelectedItem().toString();
        return value;
    }

    public String radioGroupAnswer(int id, List<String> AnserList) {
        int positon = 0;
        String value = "";
        RadioGroup rgitem = findViewById(id);
        int position = rgitem.indexOfChild(findViewById(rgitem.getCheckedRadioButtonId()));
        if (position != -1) {
            value = AnserList.get(position);
        }
        return value;

    }

    public String checkBoxAnswer(int ied, List<String> AnserList) {
        String value = "";
        for (int j = 0; j < AnserList.size(); j++) {

            int id = Integer.parseInt("" + ied + "" + j);
            CheckBox checkBox = findViewById(id);
            if (checkBox.isChecked()) {
                value = value + AnserList.get(j) + ";";
            }

        }
        return value;
    }


    //Layout Create Here

    public void generateLayout(int i) {
        linearLayout.removeAllViews();
        linearLayout.addView(questionTextViewMethod(qa.get(i).getId(), qa.get(i).getQuestion()));
        linearLayout.addView(checkLayout(qa.get(i)));
    }

    public View checkLayout(QuestionAnswer qadata) {

        Log.e("UIQA", qadata.getAnswerType() + ":" + qadata.getQuestion());

        View view = new View(this);

        switch (qadata.getAnswerType()) {
            case "TEXTBOX":
                return editTextMethod(qadata.getId(), qadata.getQuestion());

            case "RATINBAR":
                return ratingBarMethod(qadata.getId());

            case "SELECT":
                return spinnerMethod(qadata.getId(), qadata.getAnswerTypeInfo());

            case "RADIO":
                return radioGroupMethod(qadata.getId(), qadata.getAnswerTypeInfo());

            case "CHECKBOX":
                return checkBoxMethod(qadata.getId(), qadata.getAnswerTypeInfo());

            case "IMAGE":
                return ImageViewMethod(qadata.getId());
        }

        return view;
    }

    public View questionTextViewMethod(int id, String ques) {
        TextView textView = new TextView(this);
        textView.setText("" + ques);
        textView.setTag(id + ".Qus");
        textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
        textView.setGravity(Gravity.LEFT);
        textView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
        textView.setLayoutParams(params1);
        return textView;
    }

    public View editTextMethod(int id, String hint) {
        EditText editText = new EditText(this);
        editText.setText("");
        editText.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
        editText.setGravity(Gravity.LEFT);
        editText.setBackground(getResources().getDrawable(R.drawable.simple_rect_black_border));
        editText.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
        editText.setLayoutParams(params1);
        editText.setTag(id + ".Ans");
        editText.setId(id);
        return editText;
    }

    public View ratingBarMethod(int id) {
        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setBackground(getResources().getDrawable(R.drawable.simple_rect_black_border));
        ratingBar.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
        ratingBar.setLayoutParams(params1);
        ratingBar.setTag(id + ".Ans");
        ratingBar.setId(id);
        return ratingBar;
    }

    public View spinnerMethod(int id, List<String> AnswerList) {

        final Spinner spinner = new Spinner(this);
        spinner.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
        spinner.setLayoutParams(params1);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AnswerList);
        spinner.setAdapter(arrayAdapter);
        spinner.setTag(id + ".Ans");
        spinner.setId(id);
        spinner.setBackground(getResources().getDrawable(R.drawable.simple_rect_black_border));
        spinner.setGravity(Gravity.RIGHT);
        return spinner;
    }

    public View radioGroupMethod(int id, List<String> AnswerList) {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        radioGroup.setLayoutParams(params1);
        radioGroup.setTag(id + ".Ans");
        radioGroup.setId(id);

        for (int j = 0; j < AnswerList.size(); j++) {
            RadioButton radioButton = new RadioButton(this);

            radioButton.setGravity(Gravity.CENTER);
            radioButton.setText(AnswerList.get(j));
            radioButton.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
            radioButton.setTypeface(null, Typeface.BOLD);
            radioGroup.addView(radioButton);
        }
        return radioGroup;
    }

    public View checkBoxMethod(int id, List<String> AnswerList) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params1);

        for (int j = 0; j < AnswerList.size(); j++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(id + "" + j));
            checkBox.setLayoutParams(params1);
            checkBox.setText("" + AnswerList.get(j));
            linearLayout.addView(checkBox);
        }
        return linearLayout;
    }

    public View ImageViewMethod(int id) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
        imageView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
        imageView.setLayoutParams(params1);
        imageView.setTag(id + ".Ans");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                if (allPermissionsGranted()) {
                    activityLauncher.launch(pickPhoto, result -> {
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
                                    setImageUrl(id, picturePath);
                                    Log.e("uri", picturePath);
                                    imageView.setImageBitmap(BitmapFactory.decodeFile(compressImage(picturePath)));

                                    cursor.close();
                                }
                            }
                        }
                    });

                } else {
                    ActivityCompat.requestPermissions(SteperQuestion.this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
                }

            }
        });
        return imageView;
    }


    //get image from gallery and compress here
    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 300.0f;
        float maxWidth = 302.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}
