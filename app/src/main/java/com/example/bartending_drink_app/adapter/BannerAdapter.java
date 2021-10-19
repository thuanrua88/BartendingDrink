package com.example.bartending_drink_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bartending_drink_app.R;
import com.example.bartending_drink_app.model.object_backend.ImgProductFeature;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    List<ImgProductFeature> listBanner;
    Context context;
    int currentPos = 0;

    public BannerAdapter(List<ImgProductFeature> listBanner, Context context) {
        this.listBanner = listBanner;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {

        return view == object;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        if (currentPos >= listBanner.size())
            currentPos = 0;
        ImgProductFeature banner = listBanner.get(currentPos);
        currentPos++;
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, container, false);

        //LinearLayout lnPager = (LinearLayout) view.findViewById(R.id.ln_pager);
        ImageView imgSlide = (ImageView) view.findViewById(R.id.imv_banner);

//        ImagesSlide imagesSlide = imagesSlideList.get(position);

        //imgSlide.setImageResource(imagesSlide.getSrc());

        //Picasso.get().load(imagesSlide.getSrc()).placeholder(R.drawable.server_1).into(imgSlide);
        Glide.with(context).load(banner.getAvatar_feature()).into(imgSlide);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
