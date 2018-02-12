package com.iths.manisedighi.databasetodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manisedighi on 12/02/2018.
 */

public class ItemAdapter extends BaseAdapter{

    private List<TodoInfo> list;
    //private List<Category> catList;
    private Context context;
    private LayoutInflater layoutInflater;


    private static final String DBLOG = "Log";



    public ItemAdapter(Context context, List<TodoInfo> list) {
        this.context = context;
        this.list = list;
        //this.catList = catList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getTodolistId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = layoutInflater.inflate(R.layout.list_detail, parent, false);
        }else{
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.taskText);
        textView.setText(list.get(i).getTodolistTask());
        //TextView textView1 = view.findViewById(R.id.categoryText);
        //textView1.setText(catList.get(i).getCategoryName());

        return view;
    }


}
