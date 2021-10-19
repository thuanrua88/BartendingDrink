package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.databinding.ActivityDetailBlogBinding;
import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.model.object_backend.blogers.BlogAllResponse;
import com.example.bartending_drink_app.model.object_backend.blogers.BlogResponse;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.example.bartending_drink_app.utils.SessionAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBlogActivity extends AppCompatActivity {

    ActivityDetailBlogBinding binding;
    SOService soService;
    SessionAccount sessionAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_blog);
        soService = ApiUtils.getInstance();
        sessionAccount = new SessionAccount(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("blog_id", -1);
        if(id != -1) {
            soService.GetBlogByID(String.valueOf(id)).enqueue(new Callback<BlogResponse>() {
                @Override
                public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                    if(response.isSuccessful()){
                        Blog blog = response.body().getData();
                        binding.tvName.setText(blog.getName());
                        binding.tvCreateTime.setText(blog.getCreate_at());
                        binding.tvView.setText(blog.getView()+"");
                        binding.tvContent.setText(blog.getDescription());
                        binding.tvUser.setText(sessionAccount.getString(SessionAccount.FULL_NAME));
                        Glide.with(DetailBlogActivity.this).load(blog.getCover_img()).into(binding.imvCover);
                    }

                    Log.e("BLOGID", response.message());
                }

                @Override
                public void onFailure(Call<BlogResponse> call, Throwable t) {
                    Log.e("BLOGID", t.getMessage());

                }
            });
        }
    }

}