package com.example.guanguannfc.view.management;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private String [] boxnames;
    public SearchAdapter(Context context ,String boxnames){

    }
    @Override
    public int getCount() {
        return boxnames.length;
    }

    @Override
    public Object getItem(int i) {
        return boxnames[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
