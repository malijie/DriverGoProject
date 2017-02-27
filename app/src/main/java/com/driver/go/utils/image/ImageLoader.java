package com.driver.go.utils.image;

import android.widget.ImageView;

import com.driver.go.R;
import com.driver.go.base.DriverGoApplication;
import com.driver.go.utils.Logger;
import com.squareup.picasso.Picasso;

/**
 * Created by malijie on 2017/2/24.
 */

public class ImageLoader {
    private static ImageLoader sImageLoader = null;
    private Picasso mPicasso = null;
    private static int ERROR_RESOURCE_ID = R.mipmap.loading;
    private static int LOADING_RESOURCE_ID = R.mipmap.load_error;


    private ImageLoader(){
        mPicasso = Picasso.with(DriverGoApplication.sContext);
    }

    public static ImageLoader getInstance(){
        if(sImageLoader == null){
            synchronized (ImageLoader.class){
                sImageLoader = new ImageLoader();
            }
        }
        return sImageLoader;
    }

    public void showImage(String url, ImageView imageView){
Logger.d("url=" + url);
        mPicasso.load(url).placeholder(ERROR_RESOURCE_ID).error(LOADING_RESOURCE_ID).into(imageView);
    }
}
