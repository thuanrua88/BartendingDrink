package com.example.bartending_drink_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.Activity.FoodDetailActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.Dish;
import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter {
    public static final int REQUEST_CODE = 100;
    final Activity act;
    ArrayList<Dish> dishArrayList;

    public interface OnOpenFoodDetailActivityListener {
         void onClicked(Dish item);
    }

    public PopularAdapter(ArrayList<Dish> dishArrayList, Activity act) {
        this.dishArrayList = dishArrayList;
        this.act = act;
    }

    public void setData(ArrayList<Dish> dataDish){
        this.dishArrayList = dataDish;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.fragment_item_food,parent,false);
        PopularViewHolder holder;
        holder = new PopularViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        PopularViewHolder vh = (PopularViewHolder) holder;
        Dish model = dishArrayList.get(position);
        vh.title.setText(model.getName());
        vh.price.setText(String.valueOf(model.getPrice()));
        vh.desc.setText(model.getMethod());
        Glide.with(act).load(model.getImage_url()).into(vh.imageFood);
//        vh.imageFood.setImageResource(model.getImage_url());
        vh.lnItemFood.setOnClickListener(v -> {
            Intent intent = new Intent(act, FoodDetailActivity.class);
            intent.putExtra("id_food", dishArrayList.get(position).getId());
            intent.putExtra("img_food", dishArrayList.get(position).getImage_url());
            intent.putExtra("tv_name_food", dishArrayList.get(position).getName());
            intent.putExtra("tv_fee", dishArrayList.get(position).getPrice());
            intent.putExtra("tv_method",dishArrayList.get(position).getMethod());
            intent.putExtra("tv_ingredient_desc", dishArrayList.get(position).getIngredient_des());
            act.startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    public int getItemCount() {
        return dishArrayList.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        TextView title, price,desc;
        ImageView imageFood;
        LinearLayout lnItemFood;
        ImageView imv_favourite, imv_zalo, imv_addCart;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_food);
            price = itemView.findViewById(R.id.tv_price);
            imageFood = itemView.findViewById(R.id.imv_cover);
            desc = itemView.findViewById(R.id.tv_desc);
            lnItemFood = itemView.findViewById(R.id.ln_item_blog);
            imv_favourite = itemView.findViewById(R.id.imv_love);
            imv_addCart = itemView.findViewById(R.id.imv_love);
            imv_zalo = itemView.findViewById(R.id.imv_zalo);
        }
    }

}