package com.example.socialmedia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia.R;
import com.example.socialmedia.activity.PostActivity;
import com.example.socialmedia.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    //mendeklarasikan list post dengan tipe data arraylist dengan class Post
    private ArrayList<Post> postArrayList;
    private Context context;

    //constructor untuk menginisialisasi list post dari HomeFragment
    public PostAdapter(ArrayList<Post> postArrayList, Context context) {
        this.postArrayList = postArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mendeklarasikan layout apa yang akan dicetak pada recyclerview
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("post", postArrayList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //menangkap tiap objek post berdasarkan posisi saat ini
        Post post = postArrayList.get(position);
        holder.tvPersonName.setText(String.valueOf(post.getId_user()));
        holder.tvContent.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPersonName;
        private TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //menghubungkan view-view didalam layout item_post ke adapter
            tvPersonName = itemView.findViewById(R.id.tv_person_name);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}