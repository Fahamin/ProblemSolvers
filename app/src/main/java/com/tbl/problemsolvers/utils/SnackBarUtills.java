package com.tbl.problemsolvers.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.tbl.problemsolvers.R;

public class SnackBarUtills {

    public static Button btn1, btn2, btn3, btn4;
    public static Snackbar snackbar;

    public static void showSnackBar(View view, Context context) {
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customSnackView = inflater.inflate(R.layout.custom_sncakbar, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.setPadding(5, 5, 5, 5);


        btn1 = customSnackView.findViewById(R.id.btn1_ID);
        btn2 = customSnackView.findViewById(R.id.btn2_ID);
        btn3 = customSnackView.findViewById(R.id.btn3_ID);
        btn4 = customSnackView.findViewById(R.id.btn4_ID);

        snackbarLayout.addView(customSnackView, 0);

        snackbar.show();
    }
}
