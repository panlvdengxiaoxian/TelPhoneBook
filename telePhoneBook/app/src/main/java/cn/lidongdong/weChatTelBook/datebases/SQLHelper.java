package cn.lidongdong.weChatTelBook.datebases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 16/8/17.
 * 创建数据库的辅助类,用来创建表的
 */
public class SQLHelper extends SQLiteOpenHelper {
    //建表语句
    public static final String CREATE_TABLE = "create table " + SQLValues.TABLE_NAME +
            " (id integer primary key autoincrement,"
                    + SQLValues.COLUMN_NAME + " text,"
                    + SQLValues.COLUMN_NUM + " text)";

    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
