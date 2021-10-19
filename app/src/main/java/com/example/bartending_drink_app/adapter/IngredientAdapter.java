package com.example.bartending_drink_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.Ingredient;
import com.example.bartending_drink_app.network.Clickable;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter {
    ArrayList<Ingredient> ingredients;
    Context context;
    Clickable onClickable;

    public IngredientAdapter(ArrayList<Ingredient> ingredients, Context context, Clickable onClick) {
        this.ingredients = ingredients;
        this.context = context;
        this.onClickable = onClick;
    }

    public void setData(ArrayList<Ingredient> dataIngredient){
        this.ingredients = dataIngredient;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.fragment_item_ingredient,parent,false);
        IngredientAdapter.IngredientViewHolder holder;
        holder = new IngredientViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IngredientViewHolder vh = (IngredientViewHolder) holder;
        Ingredient model = ingredients.get(position);
        vh.title.setText(model.getName());
        Glide.with(context).load(model.getImage()).into(vh.imgIngredient);
        vh.checkBox.setChecked(model.isSelected());
        vh.ctrRoot.setOnClickListener(v ->
        {
            onClickable.onClick(model.getName());
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIngredient;
        TextView title;
        CheckBox checkBox;
        ConstraintLayout ctrRoot;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_ingredient);
            imgIngredient = itemView.findViewById(R.id.img_ingredient);
            checkBox = itemView.findViewById(R.id.cb_selected);
            ctrRoot = itemView.findViewById(R.id.ctr_root);
        }
    }

}
