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

        // 获得了数据库对象之后,我们遇到了下一个问题,那就是解析参数
        // 我们希望通过解析参数来知道ContentProvider的用户想要把什么样的数据插入到哪个表里
        // 我们知道uri中有我们需要的表名.contentValues中存储着需要插入数据表的数据.
        // 数据我们可以直接传给sqLiteDatabase.insert方法.剩下的工作就是从uri中解析出数据表名.
        // 下面我们先用比较基本,原始的方法来解析.
        // --------------------------------
        // 我们用斜杠("/")把uri字符串分隔成5部分,其中strings[3]这一部分就是我们需要的,也就是数据表的名字
        // content://<authorities>/<path>
        //    |    |        |         |
        //    |    |        |         |
        //    V    V        V         V
        //    0    1        2         3
        String uriString = uri.toString();
        String[] strings = uriString.split("/");
        String tableName = strings[3];

        long id = sqLiteDatabase.insert(tableName, null, contentValues);

        // 插入完成之后,方法会返回被插入行的id.
        // 按照这个insert函数的实现惯例,我们要把这个id添加到uri之后形成如下形式的新的uri
        // content://<authorities>/<path>/<rowID>
        // 然后把这个uri返回.
        String newUriString = uriString + "/" + id;
        return Uri.parse(newUriString);
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
