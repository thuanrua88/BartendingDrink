package com.example.bartending_drink_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.Activity.DetailBlogActivity;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.object_backend.blogers.Blog;
import com.example.bartending_drink_app.utils.Functions;
import com.example.bartending_drink_app.utils.SessionAccount;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    Context context;
    List<Blog> blogs;
    SessionAccount sessionAccount;

    public BlogAdapter(Context context, List<Blog> blogs) {
        this.context = context;
        this.blogs = blogs;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Blog> getBlogs() {
        notifyDataSetChanged();
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blog, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        if (blogs == null && position > blogs.size())
            return;
        Blog blog = blogs.get(position);
        holder.imv_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imv_favourite.setImageDrawable(context.getDrawable(R.drawable.ic_loved));
                // code tiếp
            }
        });

        holder.imv_zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Functions.checkToken(context)){
                    Intent intent = new Intent(context, com.example.bartending_drink_app.Activity.LoginActivity.class);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtra("blog_id", blog.getId());

                    context.startActivity(intent);
                }
            }
        });

        holder.tvSumary.setText(blog.getSummary());
        holder.tvTime.setText(blog.getCreate_at().substring(0, blog.getCreate_at().lastIndexOf("T")));


        if (blog.getBanner_img() != null) {
            if (blog.getBanner_img().contains("http"))
                Glide.with(context).load(blog.getBanner_img()).into(holder.imvAvt);
            else {
                holder.imvAvt.setImageBitmap(Functions.convertFromBase64endCode(blog.getBanner_img()));
            }
        }

        if (blog.getCover_img() != null) {
            if (blog.getCover_img().contains("http"))
                Glide.with(context).load(blog.getBanner_img()).into(holder.imvCover);
            else {
                holder.imvCover.setImageBitmap(Functions.convertFromBase64endCode(blog.getCover_img()));
            }
        }else holder.imvCover.setVisibility(View.GONE);

        holder.tv_seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Functions.checkToken(context)){
                    Intent intent = new Intent(context, com.example.bartending_drink_app.Activity.LoginActivity.class);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtra("blog_id", blog.getId());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return blogs == null ? 0 : blogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTime, tvSumary, tvView, tv_seeMore;
        ImageView imvCover;
        CircleImageView imvAvt;
        LinearLayout lnItemFood;
        ImageView imv_favourite, imv_zalo, comment;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imvAvt = itemView.findViewById(R.id.imv_avt);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            imvCover = itemView.findViewById(R.id.imv_cover);
            tvSumary = itemView.findViewById(R.id.tv_sumary);
            lnItemFood = itemView.findViewById(R.id.ln_item_blog);
            imv_favourite = itemView.findViewById(R.id.imv_love);
            comment = itemView.findViewById(R.id.imv_comment);
            imv_zalo = itemView.findViewById(R.id.imv_zalo);
            tv_seeMore = itemView.findViewById(R.id.tv_seeMore);
        }
    }
}
