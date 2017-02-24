package com.driver.go.utils.image;

import android.widget.ImageView;

import com.driver.go.base.DriverGoApplication;
import com.squareup.picasso.Picasso;

/**
 * Created by malijie on 2017/2/24.
 */

public class ImageLoader {
    public static void showImage(String url, ImageView imageView){
        Picasso.with(DriverGoApplication.sContext).load(url).into(imageView);
    }
}
