package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.databinding.ActivityRegisterNewBinding;
import com.example.bartending_drink_app.model.LoginResponse;
import com.example.bartending_drink_app.model.object_backend.login.ResponseLogin;
import com.example.bartending_drink_app.model.object_backend.register.RegisterRequest;
import com.example.bartending_drink_app.model.object_backend.register.RegisterResponse;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText edUsername, edEmail, edPassword, edCpassword;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private RadioButton radAdmin, radShipper;
    private boolean isRadAdmin, isRadShipper;
    ActivityRegisterNewBinding binding;
    SOService soService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_new);

        FindViewByID();
        SetEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        soService = ApiUtils.getInstance();
        Sprite circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
    }

    private void SetEvent() {
        btnRegister.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edEmail.getText().toString()) ||
                    TextUtils.isEmpty(edPassword.getText().toString()) || TextUtils.isEmpty(edCpassword.getText().toString())) {

                String message = "All inputs required...";
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                return;
            }

            if (!edPassword.getText().toString().equals(edCpassword.getText().toString())) {
                String message = "Password and confirm password must is sample";
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                return;
            }
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setFullName(edUsername.getText().toString());
            registerRequest.setEmail(edEmail.getText().toString());
            registerRequest.setPassword(edPassword.getText().toString());
            registerRequest.setActive(true);
            registerRequest.setAdmin(isRadAdmin);
            registerRequest.setShipper(isRadShipper);

            registerUser(registerRequest);
        });
        radAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRadAdmin = isChecked;
                isRadShipper = !isRadAdmin;

            }
        });
        radShipper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRadShipper = isChecked;
                isRadAdmin = !isRadShipper;
            }
        });
    }

    private void FindViewByID() {
        edUsername = binding.edUsername;
        edEmail = binding.edEmail;
        edPassword = binding.edPassword;
        edCpassword = binding.edCpassword;
        btnRegister = binding.btnRegister;
        radioGroup = binding.radGroup;
        radAdmin = binding.radAdmin;
        radShipper = binding.radShipper;
        progressBar = binding.spinKit;

    }

    public void registerUser(RegisterRequest registerRequest) {
        progressBar.setVisibility(View.VISIBLE);
        soService.GetResponseRegister(registerRequest).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    ResponseLogin responseLogin = response.body();
                    String message = "Sign up Successful...";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    GotoLoggin();

                } else {
                    String message = "An error occurred please try again later...";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                }
                Log.e("REGISTER", response.message());
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e("REGISTER", t.getMessage());
            }
        });
    }

    private void GotoLoggin() {
        //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
/*

 progressBar.setVisibility(View.GONE);
         if (response.isSuccessful()){
         RegisterResponse registerResponse = response.body();

         if(registerResponse.getSuccess() && !registerResponse.getEmail().isEmpty()
         && !registerResponse.getEmail().isEmpty() && registerResponse.getId()!=0)
         {
         String message = "Sign up Successful...";
         Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
         GotoLoggin();
         }

         }else{
         String message = "An error occurred please try again later...";
         Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();
         }*/
/*
 progressBar.setVisibility(View.GONE);
         String message = t.getLocalizedMessage();
         Toast.makeText(RegisterActivity.this, message,Toast.LENGTH_LONG).show();*/
