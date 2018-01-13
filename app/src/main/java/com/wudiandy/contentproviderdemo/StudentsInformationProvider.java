package com.wudiandy.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Author: Wu Di
 * Date: 2018/1/4
 */

public class StudentsInformationProvider extends ContentProvider {
    private DBHelper mDBHelper;

    /**
     * This method is called at application launch time.
     * @return true if the provider was successfully loaded, false otherwise
     */
    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDBHelper = new DBHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // 首先的一个问题，就是我们如何获取到数据库对象
        // 因为我们需要向数据库写入数据，所以我们通过DBHelper的getWritableDatabase方法来取得数据库对象。
        final SQLiteDatabase sqLiteDatabase = mDBHelper.getWritableDatabase();
        long id = sqLiteDatabase.insert("Student", null, contentValues);
        // 如果插入操作成功，则insert方法返回被插入位置的id，如果失败，则返回-1
        // 一般的，我们要验证insert方法的返回值，从而知道插入操作是否成功。
        Uri returnUri;
        if (id > 0) {
            // 在id大于0的情况下,我们需要返回一个URI
            // 那么问题来了,我们如何生成一个URI呢?
            // 我们先使用一些比较基础的方法
            
        } else {
            throw new SQLException("向" + uri + "中的插入操作失败.");
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
