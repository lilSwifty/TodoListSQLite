package com.iths.manisedighi.databasetodo;

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
    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;

    // Todolist table
    public static final String TABLE_TODOLIST = "todolist";
    public static final String TODOLIST_ID = "todolistId";
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
        db.execSQL("INSERT INTO todolist (todolistTask , todolistCategoryId)" + "VALUES ('Städa!', 0), " +
        "('VG i Objective C', 1), " + "('Sök jobb', 2)");


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
                todoInfo.setTodolistId(c.getInt(c.getColumnIndex(DBHelper.TODOLIST_ID)));
                todoInfo.setTodolistTask(c.getString(c.getColumnIndex(DBHelper.TODOLIST_TASK)));
                todoInfo.setTodolistCategoryId(c.getInt(c.getColumnIndex(DBHelper.TODOLIST_CATEGORY_ID)));
                todolistTask.add(todoInfo);

                Log.i("Todos: ", todoInfo.getTodolistId() + ", "
                    + todoInfo.getTodolistTask() + ", " + todoInfo.getTodolistCategoryId());
            }
        }

        return todolistTask;
    }

    public Cursor getAllCategoriesCursor(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_CATEGORY, null, null, null, null, null, null, null);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(TABLE_CATEGORY,null, null, null, null, null, null, null);

        boolean success = c.moveToFirst();

        if(success){
            do{
                Category category = new Category();
                category.setCategoryName(c.getString(c.getColumnIndex(DBHelper.CATEGORY_NAME)));
            }while(c.moveToNext());
        }

        return categoryList;
    }

    public List<TodoInfo> testAllCategories(){
        List<TodoInfo>  categoryItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();


        String query = "SELECT * FROM " + "category INNER JOIN todolist ON " +
                "category.categoryId = todolist.todolistCategoryId;";


        /*String query = " SELECT todolistTask, todolistId FROM todolist INNER JOIN category ON "
                + "category.categoryId = todolist.todolistCategoryId;";*/

        Cursor c = db.rawQuery(query, null);

        Log.i(DBLOG, "todos/category: " + c.getCount());

        if (c.getCount() > 0){
            while(c.moveToNext()){
                TodoInfo todoInfo = new TodoInfo();
                Category category = new Category();

                todoInfo.setTodolistId(c.getInt(c.getColumnIndex(DBHelper.TODOLIST_ID)));
                todoInfo.setTodolistTask(c.getString(c.getColumnIndex(DBHelper.TODOLIST_TASK)));
                todoInfo.setTodolistCategoryId(c.getInt(c.getColumnIndex(DBHelper.TODOLIST_CATEGORY_ID)));
                category.setCategoryId(c.getInt(c.getColumnIndex(DBHelper.CATEGORY_ID)));
                category.setCategoryName(c.getString(c.getColumnIndex(DBHelper.CATEGORY_NAME)));
                categoryItems.add(todoInfo);

                Log.i("Return items/category: ", todoInfo.getTodolistId() + " id , Task: "
                        + todoInfo.getTodolistTask()+ ", Category: " + category.getCategoryName());
            }
//todoInfo.getTodolistCategoryId()
        }
        return categoryItems;
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

}
