package com.tbl.problemsolvers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tbl.problemsolvers.model.StringWithTag;
import com.tbl.problemsolvers.utils.MiscUtil;

import java.util.ArrayList;

public class StringwithTagLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<StringWithTag> datalist = new ArrayList<>();


    TableLayout toptable;
    String sgaet, taget;
    MiscUtil miscUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stringwith_tag_layout);

        toptable =findViewById(R.id.tabelView);

        FloatingActionButton fab = findViewById(R.id.fab);

        miscUtil=new MiscUtil(this,this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(StringwithTagLayoutActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.input_dialog);

                EditText sga = dialog.findViewById(R.id.sgaiD);

                EditText tag = dialog.findViewById(R.id.tagiD);

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sgaet = sga.getText().toString();
                        taget = tag.getText().toString();
                        datalist.add(new StringWithTag(sgaet,taget));
                        dialog.dismiss();

                        setTextView();
                    }
                });

                dialog.show();
            }


        });

    }
    private void setTextView() {

        ArrayList<String> a = new ArrayList<>();
        a.add("SGA");
        a.add("TGT");
        a.add("");

        try {
            toptable.removeAllViews();
        } catch (Exception e) {

        }
        TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        TableRow someRow = new TableRow(this);
        for (int columns = 0; columns < a.size(); columns++) {

            someRow.setLayoutParams(params2);
            TextView textView = new TextView(this);
            textView.setText(a.get(columns));
            textView.setBackground(getResources().getDrawable(R.drawable.cell_border_blue));
            textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
            textView.setTypeface(null, Typeface.BOLD);
            textView.setLayoutParams(params1);
            someRow.addView(textView);

        }

        toptable.addView(someRow);

        for (int i = 0; i < datalist.size(); i++) {

            someRow = new TableRow(this);

            someRow.setLayoutParams(params2);
            TextView textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.cell_border_white));
            textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
            textView.setGravity(Gravity.LEFT);
            textView.setText("" + datalist.get(i).getName());
            textView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
            textView.setLayoutParams(params1);
            someRow.addView(textView);

            someRow.setLayoutParams(params2);
            textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.cell_border_white));
            textView.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
            textView.setLayoutParams(params1);
            textView.setText("" + datalist.get(i).getId());

            someRow.addView(textView);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_baseline_delete_24);
            imageView.setTag(i);
            imageView.setOnClickListener(this);
            imageView.setPadding(miscUtil.calculateDP(5), miscUtil.calculateDP(10), miscUtil.calculateDP(5), miscUtil.calculateDP(10));
            imageView.setLayoutParams(params1);
            someRow.addView(imageView);

            toptable.addView(someRow);
        }

    }

    @Override
    public void onClick(View v) {
        miscUtil.showAlertDialogDefault("Delete",""+v.getTag());
        datalist.remove(Integer.parseInt(String.valueOf(v.getTag())));
        setTextView();
    }
}