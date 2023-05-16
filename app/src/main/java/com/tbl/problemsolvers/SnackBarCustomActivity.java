package com.tbl.problemsolvers;

import static com.tbl.problemsolvers.utils.SnackBarUtills.btn1;
import static com.tbl.problemsolvers.utils.SnackBarUtills.btn2;
import static com.tbl.problemsolvers.utils.SnackBarUtills.btn3;
import static com.tbl.problemsolvers.utils.SnackBarUtills.btn4;
import static com.tbl.problemsolvers.utils.SnackBarUtills.showSnackBar;
import static com.tbl.problemsolvers.utils.SnackBarUtills.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SnackBarCustomActivity extends AppCompatActivity {

    Button bShowSnackbar, bShowSnackbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_custom);
        bShowSnackbar = findViewById(R.id.showSnackbarButton);
        bShowSnackbar1 = findViewById(R.id.showSnackbarButton1);

        bShowSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(v, SnackBarCustomActivity.this);

                // now handle the same button with onClickListener

                btn1.setVisibility(View.GONE);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 1 ", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();

                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 2", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 3", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 4", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });


            }
        });


        bShowSnackbar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar(v, SnackBarCustomActivity.this);

                // now handle the same button with onClickListener

                //   btn1.setVisibility(View.GONE);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 1 ", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();

                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 2", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 3", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackBarCustomActivity.this, "click Btn 4", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });


            }
        });
    }
}