package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.evenbus.OrderSuccessEvent;
import com.example.bartending_drink_app.model.CartResponse;
import com.example.bartending_drink_app.model.PurchaseRequest;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyConfirmActivity extends AppCompatActivity {
    public static final int RESULT_CODE_CONFIRM = 112;
    private Button btnOk, btnCancel;
    private EditText edPhone, edAddress;
    public static final String SHARE_PREF_NAME = "useFile";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_verify_confirm);

        edPhone = findViewById(R.id.ed_phone);
        edAddress = findViewById(R.id.ed_address);
        btnOk = findViewById(R.id.btn_confirm);
        btnCancel = findViewById(R.id.btn_destroy);

        progressBar = findViewById(R.id.spin_kit);
        Sprite circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);

        btnCancel.setOnClickListener(v -> finish());

        btnOk.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edPhone.getText().toString()) || TextUtils.isEmpty(edAddress.getText().toString())){
                String message = "All inputs required...";
                Toast.makeText(VerifyConfirmActivity.this, message,Toast.LENGTH_LONG).show();
            }else {
                PurchaseRequest purchaseRequest = new PurchaseRequest();
                purchaseRequest.setPhone(edPhone.getText().toString());
                purchaseRequest.setAddress(edAddress.getText().toString());

                confirmUser(purchaseRequest);
            }
        });
    }

    public String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void confirmUser(PurchaseRequest purchaseRequest){
        progressBar.setVisibility(View.VISIBLE);
        Call<CartResponse> cartResponseCall = ApiClient.getCardService().saveCart(purchaseRequest, getToken());
        cartResponseCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    EventBus.getDefault().post(new OrderSuccessEvent(true));
                    openDialog();
                }else {
                    String message = "An error occurred please try again later...";
                    Toast.makeText(VerifyConfirmActivity.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                String message = t.getLocalizedMessage();
                Toast.makeText(VerifyConfirmActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_popup_order);
        Button btnGoHome = dialog.findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(v -> {
            setResult(VerifyConfirmActivity.RESULT_CODE_CONFIRM);
            finish();
        });
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.show();
    }



}