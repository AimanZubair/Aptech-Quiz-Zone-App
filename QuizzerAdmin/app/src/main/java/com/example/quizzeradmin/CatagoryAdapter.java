package com.example.quizzeradmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.Viewholder> {

    private List<CatagoryModel> catagoryModelList;
    private DeleteListner deleteListener;

    public CatagoryAdapter(List<CatagoryModel> catagoryModelList, DeleteListner deleteListner) {
        this.catagoryModelList = catagoryModelList;
        this.deleteListener = deleteListner;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.setData(catagoryModelList.get(position).getUrl(),catagoryModelList.get(position).getName(),catagoryModelList.get(position).getKey(),position);

    }

    @Override
    public int getItemCount() {
        return catagoryModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView title;
        private ImageButton delete;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            title = itemView.findViewById(R.id.title);
            delete = itemView.findViewById(R.id.delete);
        }

        private void setData(String url,String title,final String key, final int position){
            Glide.with(imageView.getContext()).load(url).into(imageView);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent setIntent = new Intent(itemView.getContext(),SetsActivity.class);
                    setIntent.putExtra( "title",title);
                    setIntent.putExtra( "position",position);
                    setIntent.putExtra( "key",key);
                    itemView.getContext().startActivity(setIntent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteListener.onDelete(key,position);
                }
            });
        }
    }

    public interface DeleteListner{
        public void onDelete(String key, int position);
    }
}
