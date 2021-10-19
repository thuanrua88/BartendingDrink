package com.example.bartending_drink_app.network;
import com.example.bartending_drink_app.model.Cart;
import com.example.bartending_drink_app.model.CartResponse;
import com.example.bartending_drink_app.model.HistoryCartResponse;
import com.example.bartending_drink_app.model.PurchaseRequest;
import com.example.bartending_drink_app.model.request.AddItemCardParam;
import com.example.bartending_drink_app.model.response.AddItemCardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICart {
    String SERVER_URL = "https://thawing-falls-63605.herokuapp.com/api/";
    String ListCartURL = "cart/temp";
    String ItemCartURL = "item";
    String RemoveItemURL = "item/remove";
    String SaveCartURL = "cart";
    String HistoryCartURL = "cart";

    @POST(ItemCartURL)
    Call<AddItemCardResponse> addItemToCard(@Body AddItemCardParam param, @Header("Auth") String authHeader);

    @GET(ListCartURL)
    Call<CartResponse> getListItem(@Header("Auth") String authHeader);

    @GET(RemoveItemURL)
    Call<Cart> removeItem(@Query("id") Integer id,@Header("Auth") String authHeader);

    @POST(SaveCartURL)
    Call<CartResponse> saveCart(@Body PurchaseRequest purchaseRequest, @Header("auth") String authHeader);

    @GET(HistoryCartURL)
    Call<HistoryCartResponse> getHistoryCart(@Header("Auth") String authHeader);


}
