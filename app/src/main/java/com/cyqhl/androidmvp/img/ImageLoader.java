package com.cyqhl.androidmvp.img;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cyqhl.androidmvp.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageLoader {

    private static ImageLoader instance;

    private RequestOptions mDefRequestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.color.bg_page)
            .error(R.color.bg_page);

    private RequestOptions mCircleRequestOptions = new RequestOptions()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.color.bg_page)
            .error(R.color.bg_page);

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public RequestOptions getRoundRequest(int radius, RoundedCornersTransformation.CornerType type) {
        return RequestOptions
                .bitmapTransform(new RoundedCornersTransformation(radius, 0, type))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.bg_page)
                .error(R.color.bg_page);
    }

    public RequestOptions getCircleRequest() {
        return mCircleRequestOptions;
    }


    public void load(Context context, String url, ImageView img, RequestOptions requestOptions) {
        if (TextUtils.isEmpty(url) || !checkContext(context)) {
            return;
        }
        Glide.with(context).load(url).apply(requestOptions).into(img);
    }

    public void load(Context context, String url, ImageView img) {
        load(context, url, img, mDefRequestOptions);
    }

    //是否有效context
    private boolean checkContext(Context context) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            return false;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            return !activity.isFinishing() &&
                    (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed());

        }
        return true;
    }
}
