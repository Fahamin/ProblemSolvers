package com.tbl.problemsolvers.customQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.tbl.problemsolvers.R;
import com.tbl.problemsolvers.UiForm.QuestionAnswer;
import com.tbl.problemsolvers.UiForm.UiForm;
import com.tbl.problemsolvers.utils.LuncherActivityResult;
import com.tbl.problemsolvers.utils.MiscUtil;
import com.tbl.problemsolvers.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomLayoutQuestionActivity extends AppCompatActivity implements FragmentToActivity {
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
        setContentView(R.layout.activity_custom_layout_question);
        ButterKnife.bind(this);

        miscUtil = new MiscUtil(this, CustomLayoutQuestionActivity.this);
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


    //Layout Create Here

    public void generateLayout(int i) {
        checkLayout(qa.get(i));
    }

    public View checkLayout(QuestionAnswer qadata) {

        Log.e("UIQA", qadata.getAnswerType() + ":" + qadata.getQuestion());

        View view = new View(this);

        switch (qadata.getAnswerType()) {
            case "TEXTBOX":
                getSupportFragmentManager().beginTransaction().replace(R.id.llview, TextFragement.newInstance(qadata.getQuestion(), "data2"), "MyFragment").commit();
                break;
            case "RATINBAR":
               // return ratingBarMethod(qadata.getId());

            case "SELECT":
                getSupportFragmentManager().beginTransaction().replace(R.id.llview, SpineerFragment.newInstance(qadata.getQuestion(), (ArrayList<String>) qadata.getAnswerTypeInfo()), "MyFragment").commit();
                break;

            case "RADIO":
                getSupportFragmentManager().beginTransaction().replace(R.id.llview, RadioFragment.newInstance(qadata.getQuestion(), (ArrayList<String>) qadata.getAnswerTypeInfo()), "MyFragment").commit();
                break;

            case "CHECKBOX":
                getSupportFragmentManager().beginTransaction().replace(R.id.llview, CheckBoxFragment.newInstance(qadata.getQuestion(), (ArrayList<String>) qadata.getAnswerTypeInfo()), "MyFragment").commit();
                break;

            case "IMAGE":
                getSupportFragmentManager().beginTransaction().replace(R.id.llview, ImageSelectedFragment.newInstance(qadata.getQuestion(), ""), "MyFragment").commit();

                // return ImageViewMethod(qadata.getId());
                break;
        }

        return view;
    }


    @Override
    public void dataFromFragment(String result) {
        Log.e("result", result);

        QuestionAnswer answer = new QuestionAnswer();
        answer.setAnswer("" + result);
        answer.setId(qa.get(current_step).getId());
        answer.setQuestion(qa.get(current_step).getQuestion());
        answer.setAnswerType(qa.get(current_step).getAnswerType());
        answer.setAnswerTypeInfo(qa.get(current_step).getAnswerTypeInfo());

        newList.add(answer);

          //   Log.e("ans", newList.get(current_step).getAnswer());

    }
}
