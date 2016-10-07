package com.example.lightory.mynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lightory on 2016/8/4.
 */
public abstract class ListAdapter<T> extends BaseAdapter {

    int position = 0;
    private Context context;
    private int resid;
    List<T> list = new ArrayList<T>();
    TextView title,content,date;
    ListAdapter(Context context, int resid){
        this.context = context;
        this.resid = resid;
    }

    public void add(T t){
        list.add(t);
    }

    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(resid,null);
        }
        initView(view,i);
        return view;
    }
    abstract public void initView(View view,int position);
    abstract  public void startActivity(View view,int position);
    abstract  public void delView(int position);
}
