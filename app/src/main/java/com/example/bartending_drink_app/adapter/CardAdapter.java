package com.example.bartending_drink_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.ItemCart;
import com.example.bartending_drink_app.network.OnCLickDelete;
import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter {
    ArrayList<ItemCart> data;
    Context context;
    OnCLickDelete clickable;

    public CardAdapter(ArrayList<ItemCart> items, Context context, OnCLickDelete clickable) {
        this.data = items;
        this.context = context;
        this.clickable = clickable;
    }

    public void setData(ArrayList<ItemCart> items) {
        this.data = items;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_cart, parent, false);
        CardAdapter.CardViewHolder holder;
        holder = new CardAdapter.CardViewHolder(itemView);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CardAdapter.CardViewHolder vh = (CardAdapter.CardViewHolder) holder;
        ItemCart item = data.get(position);
        vh.name.setText(item.getDish().getName());
        vh.price.setText(String.format("$ %s", item.getDish().getPrice()));
        vh.quantity.setText(Integer.toString(item.getQuantity()));
        Context context = vh.itemView.getContext();
        Glide.with(context).load(item.getDish().getImage_url()).into(vh.imgFood);
        vh.btnDelete.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "Succefull...",Toast.LENGTH_LONG).show();
            clickable.onClick(item.getId());
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void clearData() {
        data.clear();
        this.notifyDataSetChanged();
    }
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView name;
        TextView quantity;
        TextView price;
        ImageView btnDelete;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imv_cover);
            name = itemView.findViewById(R.id.tv_title_food);
            quantity = itemView.findViewById(R.id.tv_quantity);
            price = itemView.findViewById(R.id.tv_price);
            btnDelete = itemView.findViewById(R.id.img_delete);
        }
    }
}
