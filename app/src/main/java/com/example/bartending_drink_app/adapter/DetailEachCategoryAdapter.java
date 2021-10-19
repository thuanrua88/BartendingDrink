package com.example.bartending_drink_app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.Activity.FoodDetailActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.Dish;

import java.util.ArrayList;

public class DetailEachCategoryAdapter extends RecyclerView.Adapter {
    final Activity act;
    ArrayList<Dish> dishCategoryList;

    public DetailEachCategoryAdapter(ArrayList<Dish> dishCategoryList, Activity act) {
        this.dishCategoryList = dishCategoryList;
        this.act = act;
    }
    public void setData(ArrayList<Dish> dataDishCategory){
        this.dishCategoryList = dataDishCategory;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.fragment_item_detail_category,parent, false);
        DetailEachCategoryViewHolder holder;
        holder = new DetailEachCategoryViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DetailEachCategoryViewHolder vh = (DetailEachCategoryViewHolder) holder;
        Dish model = dishCategoryList.get(position);
        vh.title.setText(model.getName());
        vh.price.setText(String.valueOf(model.getPrice()));
        vh.desc.setText(model.getMethod());
        Glide.with(act).load(model.getImage_url()).into(vh.imageFood);
        vh.ctrDetail.setOnClickListener(v -> {
            Intent intent = new Intent(act, FoodDetailActivity.class);
            intent.putExtra("id_food", dishCategoryList.get(position).getId());
            intent.putExtra("img_food", dishCategoryList.get(position).getImage_url());
            intent.putExtra("tv_name_food", dishCategoryList.get(position).getName());
            intent.putExtra("tv_fee", dishCategoryList.get(position).getPrice());
            intent.putExtra("tv_method",dishCategoryList.get(position).getMethod());
            intent.putExtra("tv_ingredient_desc", dishCategoryList.get(position).getIngredient_des());
            act.startActivityForResult(intent, PopularAdapter.REQUEST_CODE);
        });
    }

    @Override
    public int getItemCount() {
        return dishCategoryList.size();
    }

    public static class DetailEachCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, desc;
        ImageView imageFood;
        ConstraintLayout ctrDetail;

        public DetailEachCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_food);
            price = itemView.findViewById(R.id.tv_price);
            imageFood = itemView.findViewById(R.id.imv_cover);
            desc = itemView.findViewById(R.id.tv_desc_food);
            ctrDetail = itemView.findViewById(R.id.ctr_detail_category);
        }
    }
}
