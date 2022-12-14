package com.app.testingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "KanbanBoardTask.db";
    private static final int DATABASE_VERSION = 6;

    private static final String TABLE_NAME = "my_tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "task_title";
    private static final String COLUMN_PROGRESS = "task_progress";
    private static final String COLUMN_MEMBERS = "task_members";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                COLUMN_PROGRESS + " TEXT, " +
                COLUMN_MEMBERS + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String title, String progress, String members){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result = -1;

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_PROGRESS, progress);
        cv.put(COLUMN_MEMBERS, members);
        if ((title.length() == 0) | (progress.length() == 0) | (members.length() == 0)){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            result = db.insert(TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String progress, String members) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result = -1;
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_PROGRESS, progress);
        cv.put(COLUMN_MEMBERS, members);

        if ((title.length() == 0) | (progress.length() == 0) | (members.length() == 0)) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
