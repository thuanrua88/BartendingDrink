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
import com.example.bartending_drink_app.Activity.DetailCategoryActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.CategoryDomain;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter {
    final Activity act;
    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdapter( ArrayList<CategoryDomain> categoryDomains,  Activity act) {
        this.categoryDomains = categoryDomains;
        this.act = act;
    }
    public void setData(ArrayList<CategoryDomain> dataCategory){
        this.categoryDomains = dataCategory;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.fragment_item_category,parent,false);
        CategoryViewHolder holder;
        holder = new CategoryViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder vh  = (CategoryViewHolder) holder;
        CategoryDomain model = categoryDomains.get(position);
        vh.categoryName.setText(model.getName());
        Glide.with(act).load(model.getImage()).into(vh.categoryImage);
        vh.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(act, DetailCategoryActivity.class);
            intent.putExtra("img_category", categoryDomains.get(position).getImage());
            intent.putExtra("tv_name_category", categoryDomains.get(position).getName());
            act.startActivityForResult(intent, PopularAdapter.REQUEST_CODE);
        });
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;
        LinearLayout mainLayout;

        public CategoryViewHolder(@NonNull  View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tv_title_category);
            categoryImage = itemView.findViewById(R.id.img_category);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
