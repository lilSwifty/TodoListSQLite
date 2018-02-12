package com.iths.manisedighi.databasetodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private Spinner categorySpinner2;
    private Category category = new Category();
    private int categoryId;
    private TextView newNote;
    DBHelper dbHelper = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dbHelper.getTodoList();
        dbHelper.getAllCategories();
        findViews();
        showCategories();


        //dbHelper.testAllCategories();

        Log.i("Spinner", "SELECTED category: " + categorySpinner2.getSelectedItem());
        //dbHelper.onUpgrade();


    }

    public void addNewNote(View view){

        //String task = newNote.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        category = (Category)categorySpinner2.getSelectedItem();
        categoryId = (int)category.getCategoryId();
        TodoInfo ti = new TodoInfo(newNote.getText().toString(), categoryId);
        dbHelper.addTodo(dbHelper.getWritableDatabase(),ti);

        /*
        if (categorySpinner2.getSelectedItem().equals("Hem")){
            TodoInfo ti = new TodoInfo(task, 1);
            dbHelper.addTodo(dbHelper.getWritableDatabase(), ti);
            //startActivity(intent);
        }else if(categorySpinner2.getSelectedItem().equals(("Skola"))){
            TodoInfo ti = new TodoInfo(task, 2);
            dbHelper.addTodo(dbHelper.getWritableDatabase(), ti);
            //startActivity(intent);
        }else if(categorySpinner2.getSelectedItem().equals("Jobb")){
            TodoInfo ti = new TodoInfo(task, 3);
            dbHelper.addTodo(dbHelper.getWritableDatabase(), ti);
            //startActivity(intent);
        }
        */

        startActivity(intent);
        //finish();
    }

    public void goBack(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void showCategories(){
        List<Category> categoryItems =  dbHelper.getAllCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner2.setAdapter(adapter);

    }

    private void findViews(){
        categorySpinner2 = findViewById(R.id.categorySpinner2);
        newNote = findViewById(R.id.newNote);

    }

    //Log.i("Spinner", "SELECTED category: " + categorySpinner2.getSelectedItem());


}
