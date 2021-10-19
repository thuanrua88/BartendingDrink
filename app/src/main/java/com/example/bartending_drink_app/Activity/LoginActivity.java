package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.databinding.ActivityLoginBinding;
import com.example.bartending_drink_app.databinding.ActivityLoginNewBinding;
import com.example.bartending_drink_app.evenbus.LoginEvent;
import com.example.bartending_drink_app.model.LoginRequest;
import com.example.bartending_drink_app.model.LoginResponse;
import com.example.bartending_drink_app.model.object_backend.login.RequestLogin;
import com.example.bartending_drink_app.model.object_backend.login.ResponseLogin;
import com.example.bartending_drink_app.model.object_backend.login.Value;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.example.bartending_drink_app.utils.Functions;
import com.example.bartending_drink_app.utils.SessionAccount;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edUsername, edPassword;
    private TextView tvRegister;
    public static final String SHARE_PREF_NAME = "useFile";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    private ProgressBar progressBar;
    private ImageView imv_showPassword;
    ActivityLoginNewBinding binding;
    SOService soService;
    SessionAccount sessionAccount;
    boolean flagShowPass = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_new);
        soService = ApiUtils.getInstance();
        sessionAccount = new SessionAccount(this);
        finb();


        Sprite circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);

        tvRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        /*btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())){
                String message = "All inputs required...";
                Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
            }else{
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(edUsername.getText().toString());
                loginRequest.setPassword(edPassword.getText().toString());

                loginUser(loginRequest);
            }
        });*/

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                RequestLogin login = new RequestLogin(binding.edUsername.getText()+"", binding.edPassword.getText()+"");
                soService.GetResponseLogin(login).enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if(response.isSuccessful()){
                            ResponseLogin responseLogin = response.body();
                            Value user = responseLogin.getInfor().getValue();
                            sessionAccount.writeAccount(user);
                            String token = responseLogin.getToken();
                            sessionAccount.writeToken(token);
                            progressBar.setVisibility(View.GONE);
                            Functions.showMessage(LoginActivity.this, "Login successful!");
                            finish();
                        }
                        Log.e("RETROFIT", response.message());
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Log.e("RETROFIT", t.getMessage());
                        Functions.showMessage(LoginActivity.this, t.getMessage());
                        progressBar.setVisibility(View.GONE);

                    }
                });
            }
        });

        imv_showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagShowPass = !flagShowPass;
                if(flagShowPass) {
                    binding.edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.imvShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_hidden));
                }else{
                    binding.edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.imvShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye));
                }
            }
        });


    }

    private void finb() {
        edUsername = binding.edUsername;
        edPassword = binding.edPassword;
        btnLogin =binding.btnLogin;
        tvRegister = binding.tvRegister;
        progressBar = binding.spinKit;
        imv_showPassword = binding.imvShowPassword;

    }

    public void loginUser(LoginRequest loginRequest){
        progressBar.setVisibility(View.VISIBLE);
        Call<LoginResponse> loginResponseCall = ApiClient.getService().login(loginRequest);
        /*loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    assert response.body() != null;
                    savePreferences(response.body().getUsername(),response.body().getEmail(),response.body().getJwt());
                    String message = "Successful...";
                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
                    //emit to update info user
                    EventBus.getDefault().post(new LoginEvent(true));
                    finish();

                }else{
                    String message = "An error occurred please try again later...";
                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message,Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void savePreferences(String username, String email, String token) {
        Log.d("Token: ", token);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

}