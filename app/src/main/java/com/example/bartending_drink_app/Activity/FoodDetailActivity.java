package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.evenbus.AddItemToCardEvent;
import com.example.bartending_drink_app.model.request.AddItemCardParam;
import com.example.bartending_drink_app.model.response.AddItemCardResponse;
import com.example.bartending_drink_app.utils.SessionAccount;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity {
    public static final int RESULT_CODE = 111;
    private ImageView imgFood, imgFoodBack, imgFoodFav;
    private TextView tvNameFood,tvFee, tvIngredient,tvMethod;
    private Button btnAddToCard;
    private int numberOrder = 1;
    private int foodId;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String SHARE_PREF_NAME = "useFile";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initView();
        getBundle();
//        imgFoodBack.setOnClickListener(v -> onBackPressed());
    }

    public String getToken() {
        SessionAccount sessionAccount = new SessionAccount(FoodDetailActivity.this);
        return sessionAccount.getString(SessionAccount.TOKEN);
    }

    private void initView(){
        imgFood = findViewById(R.id.imv_cover);
//        imgFoodBack = findViewById(R.id.img_food_back);
        tvNameFood = findViewById(R.id.tv_name_food);
        tvFee = findViewById(R.id.tv_fee);
        tvIngredient = findViewById(R.id.tv_ingredient);
        tvMethod = findViewById(R.id.tv_method);
        btnAddToCard = findViewById(R.id.btn_addToCart);
    }

    private void getBundle (){
        String image = getIntent().getStringExtra("img_food");
        foodId = getIntent().getIntExtra("id_food", 0);
        String title = getIntent().getStringExtra("tv_name_food");
        //String fee = getIntent().getStringExtra("tv_fee");
        String method = getIntent().getStringExtra("tv_method");
        String ingredient_desc = getIntent().getStringExtra("tv_ingredient_desc");
        Glide.with(this).load(image).into(imgFood);
        tvNameFood.setText(title);
        //tvFee.setText(fee);
        tvMethod.setText(method);
        tvIngredient.setText(ingredient_desc);
        btnAddToCard.setOnClickListener(v -> {
            if (!getToken().equals("")){
                addFoodToCard();
            }else{
                Intent intent = new Intent(FoodDetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        imgFoodBack.setOnClickListener(v -> onBackPressed());
    }

    private void addFoodToCard() {
        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.labbel_loading));
        mProgressDialog.show();
        Call<AddItemCardResponse> addItemCall = ApiClient.getCardService().addItemToCard(new AddItemCardParam(Integer.toString(foodId)), getToken());
        addItemCall.enqueue(new Callback<AddItemCardResponse>() {

            @Override
            public void onResponse(Call<AddItemCardResponse> call, Response<AddItemCardResponse> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.isSuccessful()){
//                    //emit to update card and info user
                    EventBus.getDefault().post(new AddItemToCardEvent(true));
                    setResult(FoodDetailActivity.RESULT_CODE);
                    finish();
                }else{
                    String message = "An error occurred please try again later...";
                    Toast.makeText(FoodDetailActivity.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddItemCardResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                String message = t.getLocalizedMessage();
                Toast.makeText(FoodDetailActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }
}
