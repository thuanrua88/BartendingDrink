package com.example.bartending_drink_app.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bartending_drink_app.Activity.VerifyConfirmActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.adapter.CardAdapter;
import com.example.bartending_drink_app.evenbus.AddItemToCardEvent;
import com.example.bartending_drink_app.evenbus.LogOutEvent;
import com.example.bartending_drink_app.evenbus.LoginEvent;
import com.example.bartending_drink_app.evenbus.OrderSuccessEvent;
import com.example.bartending_drink_app.model.Cart;
import com.example.bartending_drink_app.model.CartResponse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartending_drink_app.Activity.ApiClient;
import com.example.bartending_drink_app.model.ItemCart;
import com.example.bartending_drink_app.network.OnCLickDelete;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bartending_drink_app.Activity.FoodDetailActivity.KEY_TOKEN;
import static com.example.bartending_drink_app.Activity.FoodDetailActivity.SHARE_PREF_NAME;

public class CardListFragment extends Fragment implements OnCLickDelete  {
    public static final int REQUEST_CODE_CONFIRM = 110;

    public CardListFragment() {
    }

    private RecyclerView recycleView;
    private CardAdapter cardAdapter;
    private ProgressBar progressBar;
    private TextView tvTotalPrice;
    private TextView tvMinus, tvPlus;
    private LinearLayout btnOrder;
    private TextView tvEmpty;
    private ArrayList<ItemCart> data = new ArrayList<>();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card_list, container, false);
    }

    @Subscribe
    public void onLogOutEvent(LogOutEvent event) {
        Log.d("TAG_EVENT_LOGOUT", "---------------------------Remove card : " + event.getLogOut() + "-----------------------");
        tvEmpty.setVisibility(View.VISIBLE);
        tvTotalPrice.setText("$ 0");
        cardAdapter.clearData();
    }

    @Subscribe
    public void onAddItemToCardSuccess(AddItemToCardEvent event) {
        Log.d("TAG_EVENT_ADD_TO_CARD", "---------------------------event: " + event.getIsRefresh() + "-----------------------");
        refresh();
    }

    @Subscribe
    public void onLogInEvent(LoginEvent event) {
        Log.d("TAG_EVENT_LOGIN", "--------------------------- event : " + event.getLoginSuccess() + "-----------------------");
        refresh();
    }

    @Subscribe
    public void onOrderSuccess(OrderSuccessEvent event) {
        Log.d("TAG_EVENT_ADD_TO_CARD", "---------------------------event: " + event.getSuccess() + "-----------------------");
        tvEmpty.setVisibility(View.VISIBLE);
        tvTotalPrice.setText("$ 0");
        cardAdapter.clearData();
        data.clear();
    }

    private void refresh() {
        getListCard();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataCard();
        setUpRecycleView(view);
        btnOrder = view.findViewById(R.id.btnOrder);

        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        tvTotalPrice.setText("$ 0");
        tvEmpty.setVisibility(View.VISIBLE);

        progressBar = view.findViewById(R.id.spin_kit);
        Sprite circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);

        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });

        getListCard();

        eventButton();

    }

    private void eventButton() {
        btnOrder.setOnClickListener(v -> {
            if (data.size() > 0) {
                Intent intent = new Intent(getContext(), VerifyConfirmActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CONFIRM);
            } else {
                Toast.makeText(getContext(), "Giỏ hàng của bạn chưa có sản phẩm!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initDataCard() {
        ArrayList<ItemCart> itemCarts = new ArrayList<>();
        cardAdapter = new CardAdapter(itemCarts, getContext(), this);
    }

    private void setUpRecycleView(View viewCategoryList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycleView = viewCategoryList.findViewById(R.id.rv_my_card);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(cardAdapter);
    }

    private void getListCard() {
        if (getContext() == null) return;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(KEY_TOKEN, "");
        assert token != null;
        if (!token.equals("")) {
            //handle loading
            progressBar.setVisibility(View.VISIBLE);
            //get data
            Call<CartResponse> callItemsCard = ApiClient.getCardService().getListItem(token);

            callItemsCard.enqueue(new Callback<CartResponse>() {
                @Override
                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        ArrayList<ItemCart> list = response.body().getData().getItems();
                        if (list == null || list.size() == 0) {
                            tvEmpty.setVisibility(View.VISIBLE);
                            tvTotalPrice.setText(String.format("$ %s", 0f));
                            cardAdapter.clearData();
                            return;
                        }
                        tvEmpty.setVisibility(View.GONE);
                        tvTotalPrice.setText(String.format("$ %s", response.body().getData().getTotalPrice()));
                        cardAdapter.setData(list);
                        data = list;
                    }
                }

                @Override
                public void onFailure(Call<CartResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    String message = t.getLocalizedMessage();
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public String getToken() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    private void removeItemCart(Integer id) {
        if (getContext() == null) return;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(KEY_TOKEN, "");
        assert token != null;
        if (!token.equals("")) {
            //handle loading
            progressBar.setVisibility(View.VISIBLE);
            //get data
            Call<Cart> cartCall = ApiClient.getCardService().removeItem(id, getToken());
            cartCall.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        for (int i = 0; i < data.size(); i++) {
                            ItemCart x = data.get(i);
                            if (x.getId() == id) {
                                removeItemCart(id);
                            }
                        }
                        cardAdapter.setData(data);

                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    String message = t.getLocalizedMessage();
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onClick(Integer id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Product deletion confirmation");
        builder.setMessage("Are you sure you want to delete this product?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItemCart(id);
            }
        });

        builder.show();
    }
}