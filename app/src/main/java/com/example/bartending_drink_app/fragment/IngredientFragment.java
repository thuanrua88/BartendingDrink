package com.example.bartending_drink_app.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bartending_drink_app.Activity.SuggestedDishesActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.network.Clickable;
import com.example.bartending_drink_app.adapter.IngredientAdapter;
import com.example.bartending_drink_app.model.Ingredient;
import com.example.bartending_drink_app.model.IngredientResponse;
import com.example.bartending_drink_app.network.APIManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bartending_drink_app.adapter.PopularAdapter.REQUEST_CODE;

public class IngredientFragment extends Fragment implements Clickable {
    private RecyclerView recycleView;
    private IngredientAdapter ingredientAdapter;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    TextView tvNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        tvNext = view.findViewById(R.id.tv_next);
        tvNext.setOnClickListener(v -> {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < ingredients.size(); i++){
                Ingredient x = ingredients.get(i);
                if (x.isSelected() == true){
                    arrayList.add(x.getName());
                }
            }
            Log.d("Test",arrayList.toString());

            Intent intent = new Intent(getContext(), SuggestedDishesActivity.class );
            intent.putStringArrayListExtra("ingredients", arrayList);
            getActivity().startActivityForResult(intent, REQUEST_CODE);
        });

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListData();

        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ingredientAdapter = new IngredientAdapter(ingredientArrayList, getContext(), this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerViewIngredientList(view);
    }

    private void setRecyclerViewIngredientList(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false );
        recycleView = view.findViewById(R.id.rv_ingredients);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(ingredientAdapter);
    }

    private void getListData() {
        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.labbel_loading));
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager service = retrofit.create(APIManager.class);
        service.apiGetListIngredientData().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.body() == null) return;
                Log.d("Test",response.body().toString());
                ingredientAdapter.setData(response.body().getData());
                ingredients = response.body().getData();
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(String title) {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient x = ingredients.get(i);
            if (x.getName() == title) {
                x.setSelected(!x.isSelected());
                ingredients.set(i,x);
            }
           // ingredientArrayList.get(isStateSaved() )
        }
        ingredientAdapter.setData(ingredients);
    }


//    public void goToSuggestedDishes() {
//        if (getActivity() != null) {
//            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//            SuggestedDishesActivity suggestedDishesActivity = new SuggestedDishesActivity();
//            fragmentTransaction.replace(R.id.frame_container, suggestedDishesActivity);
//            fragmentTransaction.addToBackStack(SuggestedDishesActivity.TAG);
//            fragmentTransaction.commit();
//        }
//    }
}