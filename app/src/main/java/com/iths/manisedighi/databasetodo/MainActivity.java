package com.iths.manisedighi.databasetodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);
    private UserInfo userInfo = new UserInfo();
    TodoInfo todoInfo = new TodoInfo();
    ListView myListView;
    private Spinner userSpinner;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        showUser();
        findNotes();

        Log.i("Spinner", "SELECTED category: " + userSpinner.getSelectedItem());

        Log.i("Todos: ", todoInfo.getTodolistId() + ", "
                + todoInfo.getTodolistTask() + ", " + todoInfo.getTodolistCategoryId());

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),EditActivity.class);
                i.putExtra("todoId", id);
                i.putExtra("todoNote", dbHelper.getTodoById(position).getTodolistTask());
                startActivity(i);
            }
        });

    }

    public void showUser(){
        List<UserInfo> userInfoList =  dbHelper.getAllUsers();
        ArrayAdapter<UserInfo> adapter = new ArrayAdapter<UserInfo>(this, android.R.layout.simple_spinner_item, userInfoList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);

    }


    public void onOKclicked(View v){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void findViews(){
        myListView = findViewById(R.id.myListView);
        userSpinner = findViewById(R.id.userSpinner);
    }




    public void findNotes(){
        ListAdapter itemAdapter = new ItemAdapter(this, dbHelper.getTodoList());
        myListView.setAdapter(itemAdapter);
    }


}
