package com.tbl.problemsolvers.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all the commonly used methods.
 */
public class MiscUtil {
    Context context;
    Activity activity;

    /**
     * Default Constructor
     */
    public MiscUtil() {
        //Empty
    }



    /**
     * @param dp the value of dp  to be converted
     *           Calculate the dp into pixels from the value to set in the view layout params
     */
    public int calculateDP(int dp) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);


    }

    public boolean getSpecialCharacterFound(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        return m.find();

    }

    /**
     * Constructor
     *
     * @param activity the activity from which it is called
     * @param context  the context of the activity
     */
    public MiscUtil(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    /**
     * Shows permission dialog
     */
    public void showAlertDialogPermission(String title, String body) {
        AlertDialog.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
            }
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(body)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        //finishActivity();
                    }
                });
        if (!activity.isFinishing()){
            builder.show();
        }
    }

    /**
     * Shows a alert dialog with dynamic title and body
     */
    public void showAlertDialogDefault(String title, String body) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)          .setMessage(body)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // continue with delete
                });

        if (!activity.isFinishing()){
            builder.show();
        }

    }


    /**
     * Gets the current date only
     */
    public String getCurrentdate() {
        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public String TodaysDateFormat() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public String getdatetopicker() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    /**
     * Gets the current date and time
     */
    public String getCurrentDateAndTime() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(c);
    }

    /**
     * Get timestamp for file naming
     */
    public String getTimestamp() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        return df.format(c);
    }
    public String getOrderTimestamp() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");

        return df.format(c);
    }



    public static int toMins() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return Integer.parseInt(sdf.format(cal));

    }

    /**
     * Get timestamp for file naming
     */
    public String getDatetoTime(String strDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = dateFormat.parse(strDate);
            strDate = outputformat.format(date);
            return strDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static int RandomNumber(int lowerBound, int upperBound) {

        Random random = new Random();
        int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
        return randomNumber;
    }

    /**
     * Get timestamp for file naming
     */


    public int GenerateRandomint(int min, int max) {
        int b = (int) (Math.random() * (max - min + 1) + min);
        return b;
    }

    public double GenerateRandomdouble(int min, int max) {
        double a = Math.random() * (max - min + 1) + min;
        return a;
    }

    public String GeneratingRandomAlphabeticString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public static String NumberEtoB(String number) {
        if (number.length() == 0) {
            return number;
        }
        List<String> bnumberList = Arrays.asList("০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯");

        char[] charArr = number.toCharArray();
        StringBuilder finalnumber = new StringBuilder();
        for (char ch : charArr) {
            {

                if (ch < 48 || ch > 57) {
                    finalnumber.append(ch);
                } else {
                    int location = (int) ch - 48;
                    String bangla = bnumberList.get(location);
                    finalnumber.append(bangla);
                }
            }
        }
        Log.e("finalnumber", finalnumber.toString());

        return finalnumber.toString();
    }


}
