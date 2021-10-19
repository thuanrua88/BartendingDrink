package com.example.bartending_drink_app.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.adapter.DetailEachCategoryAdapter;
import com.example.bartending_drink_app.model.Dish;
import com.example.bartending_drink_app.model.DishResponse;
import com.example.bartending_drink_app.network.APIManager;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailCategoryActivity extends AppCompatActivity {
    private ImageView imgCategory, imgFoodBack;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private DetailEachCategoryAdapter detailEachCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        imgCategory = findViewById(R.id.img_category);
        tvTitle = findViewById(R.id.tv_title_category);

        ArrayList<Dish> dishArrayList = new ArrayList<>();
        detailEachCategoryAdapter = new DetailEachCategoryAdapter(dishArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.rv_food_cate);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailEachCategoryAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getBundle();
    }

    private void getBundle(){
        String image = getIntent().getStringExtra("img_category");
        String title = getIntent().getStringExtra("tv_name_category");
        Glide.with(this.getApplicationContext()).load(image).into(imgCategory);
        tvTitle.setText(title);

        getListData(title);
    }

    private void getListData(String categoryName){
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.labbel_loading));
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager servicePopular = retrofit.create(APIManager.class);
        servicePopular.apiGetListData(categoryName,50).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body() == null) return;
                Log.d("Test",response.body().toString());
                detailEachCategoryAdapter.setData(response.body().getData());
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SuggestedDishesActivity.onHandleResult(requestCode, resultCode, this);
    }
}