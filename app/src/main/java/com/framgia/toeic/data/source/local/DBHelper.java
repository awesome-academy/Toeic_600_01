package com.framgia.toeic.data.source.local;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.framgia.toeic/databases/";
    private static String DB_NAME = "toeic600.db";
    private SQLiteDatabase mSQLiteDatabase;
    private final Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void openDataBase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        mSQLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void deleteDataBase() {
        String path = DB_PATH + DB_NAME;
        SQLiteDatabase.deleteDatabase(new File(path));
    }

    @Override
    public synchronized void close() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
        }
        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            String path = DB_PATH + DB_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            throw new Error(e.getMessage());
        }
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        return sqLiteDatabase != null;
    }

    private void copyDataBase() throws IOException {
        InputStream inputStream = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void createDataBase() throws IOException {
        this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
