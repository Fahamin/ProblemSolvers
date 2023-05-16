package com.tbl.problemsolvers

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel

class ExportEmportDBSqlite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_emport_dbsqlite)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                exportDB()
            } else {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }

        //use it button click
        importDB()

    }

    fun getBatchDirectoryName(): String? {
        var app_folder_path = ""
        app_folder_path = Environment.getExternalStorageDirectory().toString() + "/images"
        val dir = File(app_folder_path)
        if (!dir.exists() && !dir.mkdirs()) {
            dir.mkdirs()
        }
        return app_folder_path
    }

    private fun exportDB() {
        val DatabaseName = "DurbinPepsi2023.db"
        val sd = Environment.getExternalStorageDirectory()
        val data = Environment.getDataDirectory()
        var source: FileChannel? = null
        var destination: FileChannel? = null
        val currentDBPath = "/data/com.transcombd.durbin/databases/$DatabaseName"
        val backupDBPath = "DurbinPepsi2023.db"
        val currentDB = File(data, currentDBPath)
        val backupDB = File(getBatchDirectoryName(), backupDBPath)
        try {
            source = FileInputStream(currentDB).channel
            destination = FileOutputStream(backupDB).channel
            destination.transferFrom(source, 0, source.size())
            source.close()
            destination.close()
            Toast.makeText(this, "Your Database is Exported !!", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun importDB() {
        // TODO Auto-generated method stub
        try {
            val sd = Environment.getExternalStorageDirectory()
            val data = Environment.getDataDirectory()
            if (sd.canWrite()) {
                val currentDBPath = "/data/" + "com.transcombd.durbin" + "/databases/" + "DurbinPepsi2023.db"
                val backupDBPath = "/BackupFolder/DatabaseName"
                val backupDB = File(data, currentDBPath)
                val currentDB = File(getBatchDirectoryName(), "DurbinPepsi2023.db")
                val src = FileInputStream(currentDB).channel
                val dst = FileOutputStream(backupDB).channel
                dst.transferFrom(src, 0, src.size())
                src.close()
                dst.close()
                Toast.makeText(baseContext, backupDB.toString(),
                        Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(baseContext, e.toString(), Toast.LENGTH_LONG)
                    .show()
        }
    }
}