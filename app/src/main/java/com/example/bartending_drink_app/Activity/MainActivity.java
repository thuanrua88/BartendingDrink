package com.example.bartending_drink_app.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.bartending_drink_app.fragment.CardListFragment;
import com.example.bartending_drink_app.NotificationFragment;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.adapter.PopularAdapter;
import com.example.bartending_drink_app.fragment.HomeFragment;
import com.example.bartending_drink_app.fragment.IngredientFragment;
import com.example.bartending_drink_app.fragment.ProfileFragment;
import com.example.bartending_drink_app.utils.SessionAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SessionAccount sessionAccount;
    private static final String TAG_HOME = "HOME";
    private static final String TAG_PROFILE = "PROFILE";
    private static final String TAG_CARD = "CARD";
    private static final String TAG_NOTIFICATION = "NOTIFICATION";
    private static final String TAG_INGREDIENT = "INGREDIENT";

    BottomNavigationView bottomNavigationView;
    final Fragment homeFragment = new HomeFragment();
    final Fragment profileFragment = new ProfileFragment();
    final Fragment cardListFragment = new CardListFragment();
    final Fragment notificationFragment = new NotificationFragment();
    final Fragment ingredientFragment = new IngredientFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionAccount = new SessionAccount(MainActivity.this);
        loadFragment(homeFragment, TAG_HOME);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            String tag = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = homeFragment;
                    tag = TAG_HOME;
                    break;
                case R.id.navigation_account:
                    fragment = profileFragment;
                    tag = TAG_PROFILE;
                    break;
                case R.id.navigation_cart:
                    fragment = cardListFragment;
                    tag = TAG_CARD;
                    break;
                case R.id.navigation_stats:
                    fragment = notificationFragment;
                    tag = TAG_NOTIFICATION;
                    break;
                case R.id.navigation_material:
                    fragment = ingredientFragment;
                    tag = TAG_INGREDIENT;
                    break;
            }
            return loadFragment(fragment, tag);
        });
    }

    private boolean loadFragment(Fragment fragmentSelect, String tag) {
        Fragment fragmentExist = fm.findFragmentByTag(tag);
        if (fragmentExist == null) {
            fm.beginTransaction().add(R.id.frame_container, fragmentSelect, tag).hide(fragmentSelect).commit();
        }
        if (active == null) {
            fm.beginTransaction().show(fragmentSelect).commit();
        } else {
            fm.beginTransaction().hide(active).show(fragmentSelect).commit();
        }
        active = fragmentSelect;
        return true;
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.navigation_home) {
            super.onBackPressed();
            finish();  //exit app
        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PopularAdapter.REQUEST_CODE && resultCode == FoodDetailActivity.RESULT_CODE) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_cart);
        }
        if(requestCode == CardListFragment.REQUEST_CODE_CONFIRM || resultCode == VerifyConfirmActivity.RESULT_CODE_CONFIRM) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}