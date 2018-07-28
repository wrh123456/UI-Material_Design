package com.example.word32_ui_materialdesign;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private static final String TAG = "FruitAdapter";
    private Context context;
    private List<Fruit> mFruitList=new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            imageView=(ImageView)itemView.findViewById(R.id.fruit_img);
            textView=(TextView)itemView.findViewById(R.id.fruit_text);
        }
    }
    public FruitAdapter(List<Fruit> list){
        this.mFruitList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent(context,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.textView.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
