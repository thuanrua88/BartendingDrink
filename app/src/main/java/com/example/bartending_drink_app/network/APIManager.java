package com.example.bartending_drink_app.network;

import com.example.bartending_drink_app.model.CategoryResponse;
import com.example.bartending_drink_app.model.DishResponse;
import com.example.bartending_drink_app.model.IngredientResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIManager {
    String SERVER_URL = "https://thawing-falls-63605.herokuapp.com/api/";
    String DishURL = "dish/category";
    String IngredientURL = "ingredient";
    String CategoryURL = "category";
    String DishIngredientURL = "dish/ingredients";

    @GET(CategoryURL)
    Call<CategoryResponse> apiGetListCategory();

    @GET(DishURL)
    Call<DishResponse> apiGetDataDish();

    @GET("dish/category")
    Call<DishResponse> apiGetListData(@Query("name") String name, @Query("limit") Integer limit);

    @GET(DishIngredientURL)
    Call<DishResponse> apiGetSuggestedDishes(@Query("name") String name, @Query("limit") Integer limit);

    @GET(IngredientURL)
    Call<IngredientResponse> apiGetListIngredientData();

}
