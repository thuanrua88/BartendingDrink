package com.example.bartending_drink_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.Material;
import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter {
    ArrayList<Material> materials;

    public MaterialAdapter(ArrayList<Material> materials) {
        this.materials = materials;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater;
        inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.fragment_material,parent,false);
        MaterialViewHolder holder;
        holder = new MaterialViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MaterialViewHolder vh = (MaterialViewHolder) holder;
        Material model = materials.get(position);
        vh.materialName.setText(model.getTitleMaterial());
        vh.materialImage.setImageResource(model.getImageMaterial());
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder {
        TextView materialName;
        ImageView materialImage;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            materialName = itemView.findViewById(R.id.tv_title_material);
            materialImage = itemView.findViewById(R.id.img_material);
        }
    }


}
