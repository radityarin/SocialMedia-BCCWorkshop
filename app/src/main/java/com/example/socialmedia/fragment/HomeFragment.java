package com.example.socialmedia.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia.activity.CreatePostActivity;
import com.example.socialmedia.activity.MainActivity;
import com.example.socialmedia.adapter.PostAdapter;
import com.example.socialmedia.R;
import com.example.socialmedia.model.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvPost;
    private FloatingActionButton btn_add_post;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Post post = new Post(1, 1, "Semangat gais", "123123", "123123", false);
        Post post2 = new Post(2, 2, "Ayok", "123123", "123123", false);

        ArrayList<Post> list_post = new ArrayList<>();
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);
        list_post.add(post);
        list_post.add(post2);

        rvPost = view.findViewById(R.id.rv_post);
        rvPost.setAdapter(new PostAdapter(list_post,getContext()));
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_add_post = view.findViewById(R.id.btn_add_post);
        btn_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
