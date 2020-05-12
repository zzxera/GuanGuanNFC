package com.example.guanguannfc.view.mainInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;

import java.util.List;

public class PushAdapter extends ArrayAdapter {
    private final int resourceId;

    public PushAdapter(Context context, int textViewResourceId, List<PushItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PushItem pushItem =(PushItem)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView imageView = view.findViewById(R.id.img_back);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_author = view.findViewById(R.id.tv_author);
        TextView tv_content = view.findViewById(R.id.tv_content);

        imageView.setImageResource(pushItem.getImageId());
        tv_title.setText(pushItem.getTitle());
        tv_author.setText(pushItem.getAuthor());
        tv_content.setText(pushItem.getContent());


        return view;
    }
}
