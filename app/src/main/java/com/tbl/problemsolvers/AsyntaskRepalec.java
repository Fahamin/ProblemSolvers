package com.tbl.problemsolvers;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Replac to Asyntask to Executor

public class AsyntaskRepalec extends AppCompatActivity {

    TextView t1,t2;

    private static BroadcastReceiver attachmentDownloadCompleteReceive;
    ProgressDialog mProgressDialog;

    String url = "https://www.pixel4k.com/wp-content/uploads/2019/03/spiderman-miles-lost-in-space-4k_1553071367.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyntask_repalec);

        haveStoragePermission();

        t1 =findViewById(R.id.txt1);
        t2 =findViewById(R.id.txt2);
        DownloadHere();

    }

    public  void DownloadHere()
    {
        Handler handler;
        ExecutorService executor;

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        //preExccute
        // showProgress();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //baground work here
                downLoadFromLink(url);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //post execute
                        //  mProgressDialog.dismiss();

                    }
                });
            }
        });
    }


    public void showProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Downloading... Please Wait!");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //  Log.e("Permission error", "You have permission");
                return true;
            } else {

                //  Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Toast.makeText(this, "Need to Permission for Download", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else { //you dont need to worry about these stuff below api level 23
            //  Log.e("Permission error", "You already have the permission");
            return true;
        }
    }

    protected static boolean need2Download(String fileName) {

        File basePath = new File("BOOK_STORE_PATH");

        File fullPath = new File(basePath, fileName);

        if (fullPath.exists())
            return false;
        return true;
    }

    public void downLoadFromLink(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);

        if (haveStoragePermission()) {
            if (need2Download(fileName)) {

                try {
                    if (url != null && !url.isEmpty()) {
                        Uri uri = Uri.parse(url);
                        registerReceiver(attachmentDownloadCompleteReceive, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setMimeType(getMimeType(uri));
                        request.setTitle(fileName);
                        request.setDescription("Downloading attachment..");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setAllowedOverMetered(true)
                                .setAllowedOverRoaming(false)
                                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName + "zip");
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                        Toast.makeText(this, "Complete Download few second", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {

                }
            }

        } else
            Toast.makeText(this, "Check internet connection", Toast.LENGTH_SHORT).show();

    }

    public String getMimeType(Uri uri) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }


}