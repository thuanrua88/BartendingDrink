package com.example.bartending_drink_app.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.databinding.ActivityCreatePostBinding;
import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.example.bartending_drink_app.utils.Functions;
import com.example.bartending_drink_app.utils.SessionAccount;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {

    SOService soService;
    SessionAccount sessionAccount;
    ActivityCreatePostBinding binding;
    Blog blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(CreatePostActivity.this, R.layout.activity_create_post);
        sessionAccount = new SessionAccount(CreatePostActivity.this);
        soService = ApiUtils.getInstance();
        blog = new Blog();

        FillData();
        Handle();

    }

    private void Handle() {

        setBlog();
        ActivityResultLauncher<Intent> CreatPostBlogActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() != AppCompatActivity.RESULT_OK)
                        return;
                    assert result.getData() != null;
                    Uri uri = result.getData().getData();
                    try {
                        InputStream is = getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        binding.imvCover.setImageBitmap(bitmap);
                        binding.imvCover.setVisibility(View.VISIBLE);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);

                        byte[] imageByteArray = baos.toByteArray();
                        String imageToString = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
                        blog.setCover_img(imageToString);
                        blog.setBanner_img(imageToString);

                    } catch (Exception exception) {
                        Log.e("ERROR", exception.getMessage());
                    }
                }
        );

        binding.imvAddImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(Functions.checkPermission(CreatePostActivity.this)){
                    Functions.requestPermission(CreatePostActivity.this);
                    return;
                }
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                //startActivityForResult(Intent.createChooser(intent, "Choose a picture"), Functions.REQUEST_IMAGE);
                CreatPostBlogActivityForResult.launch(Intent.createChooser(intent, "Choose a pícture"));
            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.processbar.setVisibility(View.VISIBLE);
                soService = ApiUtils.getInstance();
                soService.PostBlog(blog).enqueue(new Callback<Blog>() {
                    @Override
                    public void onResponse(Call<Blog> call, Response<Blog> response) {
                        if(response.isSuccessful()){
                            Functions.showMessage(CreatePostActivity.this, "Post successfull");
                            finish();
                        }else
                            Functions.showMessage(CreatePostActivity.this, response.message());

                        Log.e("POST_BLOG", response.message());
                        binding.processbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<Blog> call, Throwable t) {
                        Log.e("POST_BLOG", t.getMessage());
                        binding.processbar.setVisibility(View.GONE);
                        Functions.showMessage(CreatePostActivity.this, t.getMessage());

                    }
                });
            }
        });

        binding.imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                blog.setDescription(s.toString());
                blog.setSummary(s.toString().substring(0, s.toString().length()/2) +"...");
            }
        });

    }

    private void FillData() {
        binding.tvName.setText(sessionAccount.getString(SessionAccount.FULL_NAME));
        Glide.with(CreatePostActivity.this).load(sessionAccount.getString(SessionAccount.AVATAR)).into(binding.imvAvt);
    }
    private void setBlog() {

        blog = new Blog(
                0,
                "Thêm bài viết mới",
                "",
                "",
                "3 hours",
                binding.edtContent.getText().toString().substring(0, binding.edtContent.getText().toString().length()/2),
                binding.edtContent.getText().toString(),
                "",
                0,
                0,
                sessionAccount.getString(SessionAccount.USER_ID),
                1,
                Functions.getCurrentTime(),
                Functions.getCurrentTime()
        );


    }


}