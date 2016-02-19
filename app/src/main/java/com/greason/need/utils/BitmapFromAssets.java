package com.greason.need.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Greason on 15/11/7.
 */
public class BitmapFromAssets {

    /**
     * 获取 assets下的 图片资源
     * @param path
     * @return
     */
    public static Bitmap getBitmap(Context context ,String path ) {
        Bitmap bitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        try {
            bitmap = BitmapFactory.decodeStream(context.getAssets().open(path  + ".png" ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap != null && !bitmap.isRecycled()) {
            return bitmap;
        }
        return null;
    }

}
