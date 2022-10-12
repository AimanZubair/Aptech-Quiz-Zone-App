package com.example.quizzeradmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdaptor extends BaseAdapter {

    public List<String> sets;
    private String category;
    private GridListner listner;

    public GridAdaptor(List<String> sets, String category,GridListner listner)
    { this.sets = sets;
      this.listner  = listner;
      this.category = category;
    }

    @Override
    public int getCount() {
        return sets.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item,parent, false);
        }
        else
        {
            view = convertView;
        }

        if (position == 0){
            ((TextView)view.findViewById(R.id.textview)).setText("+");
        }
        else {
            ((TextView)view.findViewById(R.id.textview)).setText(String.valueOf(position));
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    listner.addSet();
                }
                else {
                Intent questionIntent = new Intent(parent.getContext(),QuestionsActivity.class);
                questionIntent.putExtra("category",category);
                questionIntent.putExtra( "setId", sets.get(position -1));
                parent.getContext().startActivity(questionIntent);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (position != 0) {
                    listner.onLongCLick(sets.get(position-1),position);
                }
                return false;
            }
        });

        return view;
    }

    public interface GridListner{
        public void addSet();
        
        void onLongCLick(String setId,int position);
    }
}
