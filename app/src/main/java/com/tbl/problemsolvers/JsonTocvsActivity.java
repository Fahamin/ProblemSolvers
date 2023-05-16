package com.tbl.problemsolvers;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tbl.problemsolvers.UiForm.QuestionAnswer;
import com.tbl.problemsolvers.UiForm.UiForm;
import com.tbl.problemsolvers.utils.CSVWriter;
import com.tbl.problemsolvers.utils.LuncherActivityResult;
import com.tbl.problemsolvers.utils.TinyDB;

import org.json.JSONArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonTocvsActivity extends AppCompatActivity {
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

    ImageView imageView;
    private int REQUEST_CODE_PERMISSIONS = 1001;

    protected final LuncherActivityResult<Intent, ActivityResult> activityLauncher = LuncherActivityResult.registerActivityForResult(this);
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    TinyDB tinyDB;
    JSONArray jsonObject;
    List<QuestionAnswer> qa;
    UiForm fromdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_tocvs);

        tinyDB = new TinyDB(this);
        fromdata = new Gson().fromJson(responseS, UiForm.class);
        qa = fromdata.getResult().getQuestionAnswer();


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        try {
            saveCsv(qa);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Json to Cvs
    public void saveCsv(List<QuestionAnswer> outerArray) throws IOException {

        File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "cvs");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "rr" + ".csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        // heading of column
        String[] arrayOfArrays = new String[9];
        arrayOfArrays[0] = "id";
        arrayOfArrays[1] = "question";
        arrayOfArrays[2] = "AnswerType";
        arrayOfArrays[3] = "AnswerTypeInfo";
        arrayOfArrays[4] = "Answer";


        if (file.exists()) {
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            Log.e("TimeLog", "Start");
            writer.writeNext(arrayOfArrays);
            for (int i = 0; i < 100000; i++) {

                // value of column
                String[] stringArray1 = new String[9];

                stringArray1[0] = "" + outerArray.get(1).getId();
                stringArray1[1] = "" + outerArray.get(1).getQuestion();
                stringArray1[2] = "" + outerArray.get(1).getAnswerType();
                stringArray1[3] = "" + outerArray.get(1).getAnswerTypeInfo();
                stringArray1[4] = "" + outerArray.get(1).getAnswer();

                writer.writeNext(stringArray1);

            }
            Log.e("TimeLog", "End");
            writer.close();
            Toast.makeText(this, "Download Complete", Toast.LENGTH_SHORT).show();
            openFile(file);
        }
    }

    protected void openFile(File fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW, FileProvider.getUriForFile(this, "com.durbin.mapmarker.fileprovider", fileName));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(Uri.fromFile(fileName), "image/jpeg");
        startActivity(intent);
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
        }
        return type;
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

}