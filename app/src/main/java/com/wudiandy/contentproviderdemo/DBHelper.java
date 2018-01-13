package com.wudiandy.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: Wu Di
 * Date: 2018/1/10
 */

/**
 * DBHelper用于
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentInfo.db";
    // 如果你变更了数据库模式，那么你应该增加数据库版本
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * We will create a table to store student information when this method is called.
     * @param sqLiteDatabase 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE Student (" +
                "ID int PRIMARY KEY, " +
                "NAME varchar(255) NOT NULL, " +
                "AGE int NOT NULL, " +
                "SCHOOL varchar(255) NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    /**
     * 如果数据库模式（database schema）出现了变化，这个方法就会被调用。
     * 常见的情况，比如增加或者减少Column，或者改变Column的名字等操作都会导致这个方法被调用。
     * 数据库模式出现变化的时候，我们应该Drop旧表，然后添加新的表。
     * @param sqLiteDatabase 数据库对象
     * @param i 旧的数据库版本号
     * @param i1 新的数据库版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop old table
        String DROP_OLD_TABLE = "DROP TABLE IF EXISTS Student;";
        sqLiteDatabase.execSQL(DROP_OLD_TABLE);

        // Call onCreate to add table to new database
        onCreate(sqLiteDatabase);
    }
}
