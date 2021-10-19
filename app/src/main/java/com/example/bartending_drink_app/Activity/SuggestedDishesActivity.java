package com.example.bartending_drink_app.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.adapter.PopularAdapter;
import com.example.bartending_drink_app.model.Dish;
import com.example.bartending_drink_app.model.DishResponse;
import com.example.bartending_drink_app.network.APIManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuggestedDishesActivity extends AppCompatActivity {
    private ImageView imgHome;
    RecyclerView recyclerView;
    private PopularAdapter popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_dishes);

        imgHome = findViewById(R.id.img_home);
        imgHome.setOnClickListener(v -> {
            startActivity(new Intent(SuggestedDishesActivity.this, MainActivity.class));
        });

        ArrayList<Dish> dishArrayList = new ArrayList<>();
        popularAdapter = new PopularAdapter(dishArrayList, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView = findViewById(R.id.rv_suggested_dishes);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popularAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
       getBundle();
    }

    private void getBundle(){
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("ingredients");
        getSuggestedDish(ingredients);
    }

    private void getSuggestedDish(ArrayList<String> dishName){
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.labbel_loading));
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager service = retrofit.create(APIManager.class);
        String paraAPI = "";
        for (int i=0; i< dishName.size(); i++){
            if(i != dishName.size()-1){
                paraAPI += dishName.get(i)+",";
            }
            else{
                paraAPI += dishName.get(i);
            }
            // String s = String.join(",",strList);
        }
         service.apiGetSuggestedDishes(paraAPI,100).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body() == null) return;
                Log.d("Test",response.body().toString());
                popularAdapter.setData(response.body().getData());
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
        onHandleResult(requestCode, resultCode, this);
    }

    public static void onHandleResult(int requestCode, int resultCode, Activity act) {
        if(requestCode == PopularAdapter.REQUEST_CODE && resultCode == FoodDetailActivity.RESULT_CODE) {
            act.setResult(FoodDetailActivity.RESULT_CODE);
            act.finish();
        }
    }
}