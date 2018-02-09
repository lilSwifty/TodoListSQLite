package com.iths.manisedighi.databasetodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    DBHelper dbHelper = new DBHelper(this);

    ListView myListView;
    String [] tasks;
    String [] categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper.getTodoList();
        dbHelper.getAllCategories();
        findViews();
        showCategories();
        dbHelper.testAllCategories();

        Log.i("Spinner", "SELECTED category: " + categorySpinner.getSelectedItem());
        //dbHelper.onUpgrade();
    }

    public void showCategories(){
        List<Category> categoryItems =  dbHelper.getAllCategories();
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        Log.i("Spinner", "SELECTED category: " + categorySpinner.getSelectedItem());
    }

    private void findViews(){
        categorySpinner = findViewById(R.id.categorySpinner);
        myListView = findViewById(R.id.myListView);

    }

    public void onOKclicked(View v){
        Log.i("Spinner", "SELECTED category: " + categorySpinner.getSelectedItem());
    }
}
