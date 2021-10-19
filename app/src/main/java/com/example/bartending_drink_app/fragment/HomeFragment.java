package com.example.bartending_drink_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bartending_drink_app.Activity.CreatePostActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.adapter.BannerAdapter;
import com.example.bartending_drink_app.adapter.BlogAdapter;
import com.example.bartending_drink_app.adapter.CategoryAdapter;
import com.example.bartending_drink_app.adapter.MaterialAdapter;
import com.example.bartending_drink_app.adapter.PopularAdapter;
import com.example.bartending_drink_app.evenbus.LogOutEvent;
import com.example.bartending_drink_app.evenbus.LoginEvent;
import com.example.bartending_drink_app.model.CategoryDomain;
import com.example.bartending_drink_app.model.CategoryResponse;
import com.example.bartending_drink_app.model.Dish;
import com.example.bartending_drink_app.model.DishResponse;
import com.example.bartending_drink_app.model.object_backend.ImgProductFeature;
import com.example.bartending_drink_app.model.Material;
import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.model.object_backend.blogers.BlogAllResponse;
import com.example.bartending_drink_app.network.APIManager;
import com.example.bartending_drink_app.services.ApiUtils;
import com.example.bartending_drink_app.services.SOService;
import com.example.bartending_drink_app.utils.SessionAccount;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private RecyclerView.Adapter adapterMaterial;
    private RecyclerView recyclerViewCategoryList, recycleViewPopularList;
    private RecyclerView rcvBlogs;
    private CategoryAdapter categoryAdapter;
    private BlogAdapter blogAdapter;
    private ViewPager vpBanner;
    private BannerAdapter bannerAdapter;
    private List<ImgProductFeature> bannerList;
    private SOService soService;
    private List<Blog> blogs;
    private ArrayList<Dish> populars;
    private PopularAdapter popularAdapter;
    private LinearLayout lnPost;
    private EditText edtStatus;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onLogOutEvent(LogOutEvent event) {
        Log.d("TAG_EVENT_LOGOUT", "---------------------------Remove card : " + event.getLogOut() + "-----------------------");
    }

    @Subscribe
    public void onLogInEvent(LoginEvent event) {
        Log.d("TAG_EVENT_LOGIN", "--------------------------- event : " + event.getLoginSuccess() + "-----------------------");
        //handleTextWelcome();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soService = ApiUtils.getInstance();
        initDataBanner();
        initDataCategory();
        initDataMaterial();
        initDataPopular();


        getListDataCategory();
        getDataBlogs();
        getDataPopular();


    }

    private void initDataPopular() {
        populars = new ArrayList<>();
        popularAdapter = new PopularAdapter(populars, getActivity());
    }

    private void initDataBanner() {
        bannerList = new ArrayList<>();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataBlog();
        initDataBlog();
        setViewPagerBanner(view);
        setRecyclerViewCategoryList(view);
        initDataBlog();
        setRecycleViewPopularList(view);
        setRcvBlogs(view);
        handleCreatePost(view);

    }

    private void handleCreatePost(View view) {
        RelativeLayout rlPost = view.findViewById(R.id.rl_post);
        edtStatus = view.findViewById(R.id.edt_status);
        rlPost.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                SessionAccount sessionAccount = new SessionAccount(getActivity());
                if(sessionAccount.getString(SessionAccount.TOKEN)!= null)
                {
                    Intent intent = new Intent(getContext(), CreatePostActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), com.example.bartending_drink_app.Activity.LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void setViewPagerBanner(View view) {
        vpBanner = view.findViewById(R.id.vp_banner);
        bannerAdapter = new BannerAdapter(bannerList, getContext());
        vpBanner.setAdapter(bannerAdapter);

        fakeLocalData();
        createBannerAutoNext();

    }

    int currentPos = 0;
    Timer timer;

    private void createBannerAutoNext() {

        final Handler handle = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPos == bannerList.size())
                    currentPos = 0;
                currentPos++;
                vpBanner.setCurrentItem(currentPos, true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handle.post(runnable);
            }
        }, 250, 2500);
    }

    private void fakeLocalData() {
        bannerList.add(new ImgProductFeature(1, 1, "https://i.imgur.com/SE5mlUk.jpg"));
        bannerList.add(new ImgProductFeature(1, 1, "https://i.imgur.com/7HVAPGk.jpg"));
        bannerList.add(new ImgProductFeature(1, 1, "https://i.imgur.com/KrpNjnO.jpg"));
        bannerList.add(new ImgProductFeature(1, 1, "https://i.imgur.com/AGjj7o8.jpg"));
        bannerList.add(new ImgProductFeature(1, 1, "https://i.imgur.com/8IR2mxx.jpg"));
        bannerList.add(new ImgProductFeature(1, 1, "https://zone1-ibizaspotlightsl.netdna-ssl.com/sites/default/files/styles/auto_1500_width/public/article-images/129270/slideshow-1560178120.jpg"));
        bannerAdapter.notifyDataSetChanged();

    }

    /*@SuppressLint("SetTextI18n")
    private void handleTextWelcome() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(KEY_TOKEN, "");
        assert token != null;
        if (!token.equals("")) {
            tv_username.setText("Hi, " + sharedPreferences.getString(KEY_USERNAME, ""));
        } else {
            tv_username.setText("Hi");
        }
    }*/

    private void initDataCategory() {
        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryDomains, getActivity());
    }


    private void initDataBlog() {
        blogs = new ArrayList<>();
        blogAdapter = new BlogAdapter(getActivity(), blogs);
    }



    private void initDataMaterial() {
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new Material("Cà rốt", R.drawable.carrot));
        materials.add(new Material("Cải ngọt", R.drawable.spinach));
        materials.add(new Material("Xúp lơ", R.drawable.broccoli));
        materials.add(new Material("Cà tím", R.drawable.eggplant));
        materials.add(new Material("Khoai tây", R.drawable.potato));
        materials.add(new Material("Cà chua", R.drawable.tomato));
        materials.add(new Material("Bắp cải", R.drawable.cabbage));
        materials.add(new Material("Củ rau tím", R.drawable.beet));
        materials.add(new Material("Dưa chuột", R.drawable.cucumber));
        materials.add(new Material("Ớt chuông vàng", R.drawable.bell_pepper));
        adapterMaterial = new MaterialAdapter(materials);
    }

    private void setRecyclerViewCategoryList(View viewCategoryList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = (AnimatedRecyclerView) viewCategoryList.findViewById(R.id.rv_categories);
        recyclerViewCategoryList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
    }

//    private void setRecycleViewMaterialList(View viewMaterialList) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recycleViewMaterialList = viewMaterialList.findViewById(R.id.rv_material);
//        recycleViewMaterialList.setLayoutManager(linearLayoutManager);
//        recycleViewMaterialList.setAdapter(adapterMaterial);
//    }

    private void setRcvBlogs(View viewEventList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvBlogs =  viewEventList.findViewById(R.id.rv_blogers);
        rcvBlogs.setItemAnimator(new DefaultItemAnimator());
        rcvBlogs.setLayoutManager(linearLayoutManager);
        rcvBlogs.setAdapter(blogAdapter);
    }

    private void setRecycleViewPopularList(View viewPopularList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewPopularList = viewPopularList.findViewById(R.id.rv_popular);
        recycleViewPopularList.setItemAnimator(new DefaultItemAnimator());
        recycleViewPopularList.setLayoutManager(linearLayoutManager);
        recycleViewPopularList.setAdapter(popularAdapter);
    }
/*
    private void setRecycleViewMealList(View viewMealList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewMealList = viewMealList.findViewById(R.id.rv_meal_courses);
        recycleViewMealList.setItemAnimator(new DefaultItemAnimator());
        recycleViewMealList.setLayoutManager(linearLayoutManager);
        recycleViewMealList.setAdapter(mealAdapter);
    }

    private void setRecyclerViewSpecialList(View viewSpecialList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycleViewSpecialDietsList = viewSpecialList.findViewById(R.id.rv_special_diets);
        recycleViewSpecialDietsList.setItemAnimator(new DefaultItemAnimator());
        recycleViewSpecialDietsList.setLayoutManager(linearLayoutManager);
        recycleViewSpecialDietsList.setAdapter(specialDietsAdapter);
    }

    private void setRecyclerViewWorldList(View viewWorldList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //recycleViewWorldFlavoursList = viewWorldList.findViewById(R.id.rv_world_flavours);
        recycleViewWorldFlavoursList.setItemAnimator(new DefaultItemAnimator());
        recycleViewWorldFlavoursList.setLayoutManager(linearLayoutManager);
        //recycleViewWorldFlavoursList.setAdapter(worldFlavoursAdapter);
    }*/

    /*private void getListDataDish() {
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.labbel_loading));
        mProgressDialog.show();
        *//*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager serviceDish = retrofit.create(APIManager.class);
        serviceDish.apiGetDataDish().enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body() == null) return;
                Log.d("Test", response.body().toString());
                dishAdapter.setData(response.body().getData());
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });*//*
    }*/


    private void getListDataCategory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager serviceCate = retrofit.create(APIManager.class);
        serviceCate.apiGetListCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.body() == null) return;
                Log.d("Test", response.body().toString());
                categoryAdapter.setData(response.body().getData());
                recyclerViewCategoryList.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    private void getDataBlogs() {

        soService.GetAllBlogs().enqueue(new Callback<BlogAllResponse>() {
            @Override
            public void onResponse(Call<BlogAllResponse> call, Response<BlogAllResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        blogs = response.body().getData();
                        blogAdapter.setBlogs(blogs);
                    }
                }
                Log.e("RETROFIT", response.message());
            }

            @Override
            public void onFailure(Call<BlogAllResponse> call, Throwable t) {
                Log.e("RETROFIT", t.getMessage());
            }
        });


    }

    private void getDataPopular() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager servicePopular = retrofit.create(APIManager.class);
        servicePopular.apiGetListData("Popular", 5).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (response.body() == null) return;
                Log.d("Test", response.body().toString());
                popularAdapter.setData(response.body().getData());
                recycleViewPopularList.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {

            }
        });
    }
/*
    private void getDataMealAndCourse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager servicePopular = retrofit.create(APIManager.class);
        servicePopular.apiGetListData("Meals and courses", 5).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (response.body() == null) return;
                Log.d("Test", response.body().toString());
                mealAdapter.setData(response.body().getData());
                recycleViewMealList.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {

            }
        });
    }

    private void getDataSpecialDiets() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager serviceSpecialDiets = retrofit.create(APIManager.class);
        serviceSpecialDiets.apiGetListData("Special diets", 5).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (response.body() != null) {
                    Log.d("Test", response.body().toString());
                    specialDietsAdapter.setData(response.body().getData());
                    recycleViewSpecialDietsList.scheduleLayoutAnimation();
                }
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {

            }
        });
    }

    private void getDataWorldFlavour() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager serviceWorldFlavour = retrofit.create(APIManager.class);
        serviceWorldFlavour.apiGetListData("World flavours", 5).enqueue(new Callback<DishResponse>() {
            @Override
            public void onResponse(Call<DishResponse> call, Response<DishResponse> response) {
                if (response.body() == null) return;
                Log.d("Test", response.body().toString());
                worldFlavoursAdapter.setData(response.body().getData());
                recycleViewWorldFlavoursList.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<DishResponse> call, Throwable t) {

            }
        });
    }*/

    @Override
    public void onResume() {
        super.onResume();
        getDataBlogs();
        getDataPopular();
    }
}