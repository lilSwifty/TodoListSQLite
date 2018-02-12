package com.iths.manisedighi.databasetodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manisedighi on 09/02/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DBLOG = "TODOAPP";
    private static final String DB_NAME = "todo13.db";
    private static final int DB_VERSION = 1;

    // Todolist table
    public static final String TABLE_TODOLIST = "todolist";
    public static final String TODOLIST_ID = "_id";
    public static final String TODOLIST_TASK = "todolistTask";
    public static final String TODOLIST_CATEGORY_ID = "todolistCategoryId";

    private static final String TABLE_TODOLIST_CREATE =
            "CREATE TABLE " + TABLE_TODOLIST + " (" +
                    TODOLIST_ID + " INTEGER PRIMARY KEY, "+
                    TODOLIST_TASK + " TEXT, " +
                    TODOLIST_CATEGORY_ID  + " INTEGER" +")";



    //Category table
    public static final String TABLE_CATEGORY = "category";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";

    private static final String TABLE_CATEGORY_CREATE =
            "CREATE TABLE " + TABLE_CATEGORY + " (" +
                    CATEGORY_ID + " INTEGER PRIMARY KEY," +
                    CATEGORY_NAME + " TEXT" +")";


    //User table
    public static final String TABLE_USER = "user";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";

    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER + " (" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    USER_NAME + " TEXT" + ")";



    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_TODOLIST_CREATE);
        db.execSQL(TABLE_CATEGORY_CREATE);
        db.execSQL(TABLE_USER_CREATE);
        Log.i(DBLOG, "Tables created");

        db.execSQL("INSERT INTO user (userName) VALUES ('Mani')");
        db.execSQL("INSERT INTO category (categoryName) VALUES ('Hem'), ('Skola'), ('Jobb')");
        db.execSQL("INSERT INTO todolist (todolistTask , todolistCategoryId)" + "VALUES ('Städa!', 1), " +
        "('VG i Databashantering', 2), " + "('Sök jobb', 3)");


        //db.execSQL("delete from "+ TABLE_TODOLIST);
        //db.execSQL("delete from "+ TABLE_CATEGORY);
    }

    public List <TodoInfo> getTodoList(){
        List<TodoInfo> todolistTask = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(DBHelper.TABLE_TODOLIST, null, null, null, null, null, null);
        Log.i(DBLOG, "TodolistTasks: " + c.getCount() + " rows");
        if (c.getCount() > 0){
            while(c.moveToNext()){
                TodoInfo todoInfo = new TodoInfo();
                todoInfo.setTodolistId(c.getInt(0));
                todoInfo.setTodolistTask(c.getString(1));
                todoInfo.setTodolistCategoryId(c.getInt(2));
                todolistTask.add(todoInfo);

                Log.i("Todos: ", todoInfo.getTodolistId() + ", "
                    + todoInfo.getTodolistTask() + ", " + todoInfo.getTodolistCategoryId());
            }
        }

        return todolistTask;
    }

    public List<UserInfo> getAllUsers(){
        List<UserInfo> userInfoList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_USER, null,null,null,null,null,null);

        boolean success = c.moveToFirst();

        if (success){
            do{
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(c.getInt(c.getColumnIndex(DBHelper.USER_ID)));
                userInfo.setUserName(c.getString(c.getColumnIndex(DBHelper.USER_NAME)));
                userInfoList.add(userInfo);
            }while(c.moveToNext());
        }
        return userInfoList;
    }


    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_CATEGORY,null, null, null, null, null, null, null);

        boolean success = c.moveToFirst();

        if(success){
            do{
                Category category = new Category();
                category.setCategoryId(c.getInt(c.getColumnIndex(DBHelper.CATEGORY_ID)));
                category.setCategoryName(c.getString(c.getColumnIndex(DBHelper.CATEGORY_NAME)));
                categoryList.add(category);
            }while(c.moveToNext());
        }

        return categoryList;
    }


    public void addTodo(SQLiteDatabase db, TodoInfo todoInfo){

        db.execSQL("INSERT INTO todoList(todolistTask , todolistCategoryId) VALUES ('"
                + todoInfo.getTodolistTask()+"', "+ todoInfo.getTodolistCategoryId() +");");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todolist");
        db.execSQL("DROP TABLE IF EXISTS category");

        Log.i(DBLOG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TodoInfo getTodoById(long id){
        String selection = "_id=?";
        String[] selectionArgs = new String[]{Long.toString(id)};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_TODOLIST,null,selection,selectionArgs,null,null,null);

        boolean success = cursor.moveToFirst();

        TodoInfo todoInfo = new TodoInfo();

        if (success){
            do{
              todoInfo.setTodolistId(cursor.getInt(0));
              todoInfo.setTodolistTask(cursor.getString(1));
              todoInfo.setTodolistCategoryId(cursor.getInt(2));


            }while (cursor.moveToNext());
        }

        return todoInfo;
    }

    public void deleteTask(long id){
        SQLiteDatabase db = getWritableDatabase();
        String[] selectionArgs = new String[]{Long.toString(id)};
        int result = db.delete(TABLE_TODOLIST, " _id=?", selectionArgs);
    }

    public void editTask(long id, String task){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TODOLIST_TASK, task);

        String selection = "_id=?";
        String[] selectionArgs = new String[] {Long.toString(id)};
        int result = db.update(TABLE_TODOLIST, values, selection, selectionArgs);

    }



    /*


        String query = "SELECT * FROM " + "category INNER JOIN todolist ON " +
                "category.categoryId = todolist.todolistCategoryId;";


        String query = " SELECT todolistTask, todolistId FROM todolist INNER JOIN category ON "
                + "category.categoryId = todolist.todolistCategoryId;";

    Cursor c = db.rawQuery(query, null);

        Log.i(DBLOG, "todos/category: " + c.getCount());


     */

}
