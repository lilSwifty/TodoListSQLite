package com.iths.manisedighi.databasetodo;

/**
 * Created by manisedighi on 09/02/2018.
 */

public class TodoInfo {

    private long todolistId;
    private String todolistTask;
    private int todolistCategoryId;

    public TodoInfo(){

    }

    public TodoInfo(long todolistId, String todolistTask, int todolistCategoryId){
        this.todolistId = todolistId;
        this.todolistTask = todolistTask;
        this.todolistCategoryId = todolistCategoryId;
    }

    public TodoInfo(String todolistTask, int todolistCategoryId){
        this.todolistTask = todolistTask;
        this.todolistCategoryId = todolistCategoryId;

    }

    public long getTodolistId() {

        return todolistId;
    }

    public void setTodolistId(long todolistId){
        this.todolistId = todolistId;
    }

    public String getTodolistTask(){
        return todolistTask;
    }

    public void setTodolistTask(String todolistTask){
        this.todolistTask = todolistTask;
    }

    public int getTodolistCategoryId(){
        return  todolistCategoryId;
    }

    public void setTodolistCategoryId(int todolistCategoryId){
        this.todolistCategoryId = todolistCategoryId;
    }
}
