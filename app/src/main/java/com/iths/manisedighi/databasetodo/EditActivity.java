package com.iths.manisedighi.databasetodo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private Context context = EditActivity.this;
    private TodoInfo todoInfo;
    private long id;
    private TextView oldNote;
    private EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        todoInfo = getThisTodo();
        oldNote = findViewById(R.id.oldNote);
        editNote = findViewById(R.id.editNote);
        oldNote.setText(todoInfo.getTodolistTask());
    }

    public TodoInfo getThisTodo(){
        Intent i = getIntent();
        dbHelper = new DBHelper(context);
        id = i.getLongExtra("todoId",0);

        todoInfo = dbHelper.getTodoById(id);

        return dbHelper.getTodoById(id);
    }

    public void saveButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        dbHelper.editTask(todoInfo.getTodolistId(),editNote.getText().toString());
        startActivity(intent);
    }

    public void deleteButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        dbHelper.deleteTask(todoInfo.getTodolistId());
        startActivity(intent);
    }
}
