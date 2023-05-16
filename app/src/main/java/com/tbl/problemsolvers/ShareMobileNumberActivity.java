package com.tbl.problemsolvers;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareMobileNumberActivity extends AppCompatActivity {
    String mobileNum = "8801749184744"; //without '+'
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CALL_PHONE", "android.permission.READ_CONTACTS"};
    private int REQUEST_CODE_PERMISSIONS = 1001;

    String whatsAppPakage = "com.whatsapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_mobile_number);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.whatsAppIDcall)
    public void whatscall() {

        if (isAppInstalled(this, whatsAppPakage) && allPermissionsGranted()) {
            int id = getContactIDFromNumber(mobileNum, this);
            whatsAppCall(this, hasWhatsapp(id));
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            Log.e("app", "Appnotfound");
        }

    }

    @OnClick(R.id.whatsAppIDMes)
    public void whatsMessage() {

        if (isAppInstalled(this, whatsAppPakage)) {
            sendMsg();
            // openWhatsAppForMessage();
        } else {
            Log.e("app", "Appnotfound");
        }

    }

    @OnClick(R.id.imoAppID)
    public void openImoNum(View view) {
        if (isAppInstalled(this, "com.imo.android.imoim")) {
            //  openIMOAppForMessage();
            openApp(this, "com.imo.android.imoim");
        } else {
            Log.e("app", "Appnotfound");
            Toast.makeText(this, "App Not Found ", Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick(R.id.duoAppID)
    public void openduo()
    {

        Intent intent = new Intent();
        intent.setPackage("com.google.android.apps.tachyon");
        intent.setAction("com.google.android.apps.tachyon.action.CALL");
        intent.setData(Uri.parse("tel:" + mobileNum));
        startActivity(intent);
    }

    private void sendMsg() {
        String msgurl = "https://api.whatsapp.com/send?phone=+" + mobileNum + "&text=Hello";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(msgurl));
        startActivity(i);
    }

    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                return false;
                //throw new ActivityNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, "0145676545676");
            context.startActivity(i);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("e", e.getMessage());
            return false;
        }
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public String hasWhatsapp(int contactID) {
        String rowContactId = null;
        boolean hasWhatsApp;

        String[] projection = new String[]{ContactsContract.RawContacts._ID};
        String selection = ContactsContract.Data.CONTACT_ID + " = ? AND account_type IN (?)";
        String[] selectionArgs = new String[]{String.valueOf(contactID), whatsAppPakage};
        Cursor cursor = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null) {
            hasWhatsApp = cursor.moveToNext();
            if (hasWhatsApp) {
                rowContactId = cursor.getString(0);
            }
            cursor.close();
        }
        return rowContactId;
    }

    public static int getContactIDFromNumber(String contactNumber, Context context) {
        contactNumber = Uri.encode(contactNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, contactNumber), new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void whatsAppCall(Context context, String rawContactId) {
        try {

            long id = whatsAppCallId(context, rawContactId);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            String uriString = "content://com.android.contacts/data/" + id;
            intent.setDataAndType(Uri.parse(uriString), "vnd.android.cursor.item/vnd.com.whatsapp.voip.call");
            intent.setPackage(whatsAppPakage);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "whatsAppCall Exception: " + e);
        }
    }

    @SuppressLint("Range")
    private long whatsAppCallId(Context context, String rawContactId) {
        ContentResolver resolver = context.getContentResolver();

        String selection = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.Data.RAW_CONTACT_ID + " = ? ";
        String[] selectionArgs = new String[]{"vnd.android.cursor.item/vnd.com.whatsapp.voip.call", rawContactId};


        Cursor cursor = resolver.query(
                ContactsContract.Data.CONTENT_URI,
                null, selection, selectionArgs,
                ContactsContract.Contacts.DISPLAY_NAME);
        long _id = 0;

        while (cursor.moveToNext()) {
            _id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID));
            String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
            Log.d(TAG, "Data: " + _id + " " + displayName + " " + mimeType);
        }
        return _id;

    }
    private void openWhatsAppForMessage() {
        String smsNumber = "8801749184744"; //without '+'
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
            sendIntent.setPackage(whatsAppPakage);
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}