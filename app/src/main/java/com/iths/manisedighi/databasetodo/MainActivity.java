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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);
    private ArrayAdapter adapter;



    TodoInfo todoInfo = new TodoInfo();
    ListView myListView;
    private List<Integer> categoryArray = new ArrayList<Integer>();
    private List<TodoInfo> todoList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        findNotes();

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





    public void onOKclicked(View v){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);


        //Log.i("Spinner", "SELECTED category: " + categorySpinner.getSelectedItem());
    }

    public void findViews(){
        myListView = findViewById(R.id.myListView);
    }




    public void findNotes(){
        ListAdapter itemAdapter = new ItemAdapter(this, dbHelper.getTodoList());
        myListView.setAdapter(itemAdapter);




        /*
        todoList = dbHelper.getTodoList();

        for (int i = 0;  i < todoList.size(); i++){
            categoryArray.add(todoList.get(i).getTodolistCategoryId());
        }
        */


        /*
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringArray);
        myListView.setAdapter(adapter);
        */


    }


}
