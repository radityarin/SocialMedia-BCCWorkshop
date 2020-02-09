package com.example.socialmedia.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialmedia.activity.CreatePostActivity;
import com.example.socialmedia.activity.MainActivity;
import com.example.socialmedia.adapter.PostAdapter;
import com.example.socialmedia.R;
import com.example.socialmedia.api.ApiClient;
import com.example.socialmedia.api.MyApi;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.responses.AllPostResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvPost;
    private FloatingActionButton btn_add_post;
    private MyApi myApi;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPost = view.findViewById(R.id.rv_post);


        myApi = ApiClient.getClient().create(MyApi.class);

        Call<AllPostResponse> allPostCall = myApi.getAllPost();

        allPostCall.enqueue(new Callback<AllPostResponse>() {
            @Override
            public void onResponse(Call<AllPostResponse> call, Response<AllPostResponse> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    ArrayList<Post> posts = new ArrayList<>();

                    //memfilter postingan yang memiliki isDeleted = 0
                    // yg mana 0 adalah post yg belum di delete
                    for (Post post : response.body().getData()) {
                        if (post.getIsDeleted() != '1'){
                            posts.add(post);
                        }

//                        Log.d("coba berusaha: ", String.valueOf(post.getIsDeleted()+ post.getIsDeleted() instanceof  String));
                    }


                    Toast.makeText(getActivity(), String.valueOf(posts.size()), Toast.LENGTH_SHORT).show();

                    rvPost.setAdapter(new PostAdapter(posts, getContext()));
                    rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvPost.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AllPostResponse> call, Throwable t) {

            }
        });


        btn_add_post = view.findViewById(R.id.btn_add_post);
        btn_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
            }
        });

    }
}
