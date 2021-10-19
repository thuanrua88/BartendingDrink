package com.example.bartending_drink_app.dialog_fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.databinding.DialogCreaeteBlogBinding;
import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.example.bartending_drink_app.utils.Functions;
import com.example.bartending_drink_app.utils.SessionAccount;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;


public class PostBlogDialog extends DialogFragment {
    Blog blog;
    SessionAccount sessionAccount;
    DialogCreaeteBlogBinding binding;
    SOService soService;


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_creaete_blog, null, false);


        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionAccount = new SessionAccount(getContext());
        blog = new Blog();

        binding.tvName.setText(sessionAccount.getString(SessionAccount.FULL_NAME));
        ActivityResultLauncher<Intent> PostBlogActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() != PackageManager.PERMISSION_GRANTED || result.getData() == null)
                        return;
                    Uri uri = result.getData().getData();
                    try {
                        InputStream is = getContext().getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        binding.imvCover.setImageBitmap(bitmap);
                        binding.imvCover.setVisibility(View.VISIBLE);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        String imageToString = String.valueOf(baos);
                        blog.setCover_img(imageToString);
                        blog.setBanner_img(imageToString);

                    } catch (Exception exception) {
                        Log.e("ERROR", exception.getMessage());
                    }
                }
        );
        Glide.with(getContext()).load(sessionAccount.getString(SessionAccount.AVATAR)).into(binding.imvAvt);
        binding.imvAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                PostBlogActivityForResult.launch(Intent.createChooser(intent, "Choose a picture"));
            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soService = ApiUtils.getInstance();
                setBlog();
            }
        });

        binding.imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


    }

    private void setBlog() {

        blog.setUser_id(sessionAccount.getString(SessionAccount.USER_ID));
        blog.setDescription(binding.edtContent.getText().toString());
        String summary = blog.getDescription().substring(0, blog.getDescription().length() / 2) + "...";
        blog.setSummary(summary);
        blog.setCreate_at(Functions.getCurrentTime());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Functions.REQUEST_IMAGE: {
                if (resultCode != PackageManager.PERMISSION_GRANTED || data == null)
                    break;
                Uri uri = data.getData();
                try {
                    InputStream is = getContext().getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    binding.imvCover.setImageBitmap(bitmap);
                    binding.imvCover.setVisibility(View.VISIBLE);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    String imageToString = String.valueOf(baos);
                    blog.setCover_img(imageToString);
                    blog.setBanner_img(imageToString);

                } catch (Exception exception) {
                    Log.e("ERROR", exception.getMessage());
                }

                break;
            }
        }
    }
}
