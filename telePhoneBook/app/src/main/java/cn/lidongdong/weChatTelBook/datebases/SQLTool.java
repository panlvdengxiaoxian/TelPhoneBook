package cn.lidongdong.weChatTelBook.datebases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.lidongdong.weChatTelBook.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/17.
 * SQL的工具类
 */
public class SQLTool {
    private SQLHelper sqlHelper;
    private SQLiteDatabase database;
    private Context context;

    public SQLTool(Context context) {
        this.context = context;
        sqlHelper = new SQLHelper(context, SQLValues.DB_NAME, null, SQLValues.CURRENT_VERSION);
        database = sqlHelper.getWritableDatabase();
    }

    //插入一条数据到数据库
    public void insert(ContactBean contactBean) {
        String name = contactBean.getName();
        String num = contactBean.getTelNum();
        //格式化电话号码
        String telNum = formatNum(num);
        //查重
//        if(ifHaveTheName(name)){
//          //  Toast.makeText(context, name+""+telNum, Toast.LENGTH_SHORT).show();
//        }else {
        //如果没有数据,插入数据库
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLValues.COLUMN_NAME, name);
        contentValues.put(SQLValues.COLUMN_NUM, num);
        database.insert(SQLValues.TABLE_NAME, null, contentValues);
        // }
    }

    //插入集合到数据库  (insert方法重载)
    public void insert(List<ContactBean> contactBeens) {
        //foreach遍历
        for (ContactBean contactBean : contactBeens) {
            //将循环的每一个对象加入数据库
            insert(contactBean);
        }
    }

    private boolean ifHaveTheName(String name) {
        //以这个名字作为条件查询数据库
        Cursor cursor = database.query(SQLValues.TABLE_NAME, null, "name=?", new String[]{name}, null, null, null);
        int size = cursor.getCount();
        //关闭游标
        cursor.close();
        if (size != 0) {
            //有数据,返回ture;
            return true;
        } else {
            //当size==0时,证明没有数据,返回false
            return false;
        }
    }
//    /***************按条件查询数据库* 给插入时做查重使用的********/
//    private Cursor queryByName(String name){
//        if (database != null) {
//            Cursor cursor = database
//                    .query(TABLE_NAME,
//                            null,METHOD_NAME,
//                            new String[]{name},null,null,null);
//            return cursor;
//        } else {
//            return null;
//        }
//    }

    //格式化电话号码
    private String formatNum(String num) {
        num = num.replace("+86", "");
        num = num.replace(" ", "");
        num = num.replace("-", "");
        return num;
    }

    //查询所有数据
    public List<ContactBean> queryAll() {
        List<ContactBean> contactBeans = new ArrayList<>();
        Cursor cursor = database.query(SQLValues.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(SQLValues.COLUMN_NAME));
            String num = cursor.getString(cursor.getColumnIndex(SQLValues.COLUMN_NUM));
            ContactBean contactBean = new ContactBean(name, num);
            contactBeans.add(contactBean);
        }
        cursor.close();
        return contactBeans;
    }

    //修改数据
    public void update(String selection, String[] args, ContentValues newContentValues) {
        database.update(SQLValues.TABLE_NAME, newContentValues, selection, args);
    }

    //删除数据
    public void delete(String selection, String[] args) {
        database.delete(SQLValues.TABLE_NAME, selection, args);
    }


}
