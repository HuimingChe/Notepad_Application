package com.notepad.android.huiming.notepadapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

import java.util.Map;

/**
 * Created by huiming on 15-6-4.
 */
public class PictureUtils {
    /**
     * 将图片缩放到设备默认的显示尺寸。
     * @param a
     * @param path
     * @return
     */
    @SuppressWarnings("deprecation")
    public static BitmapDrawable getScaledDrawable(Activity a, String path) {
        Display display = a.getWindowManager().getDefaultDisplay();
        float desWidth = display.getWidth();
        float desHeight = display.getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > desHeight || srcWidth > desWidth){
            if (srcWidth>srcHeight){
                inSampleSize = Math.round(srcHeight/desHeight);
            }else {
                inSampleSize = Math.round(srcWidth/desWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        return new BitmapDrawable(a.getResources(),bitmap);

    }

    /**
     * 卸载图片
     * @param imageView
     */
    public static void cleanImageView(ImageView imageView){
        if (!(imageView.getDrawable() instanceof BitmapDrawable)){
            return;
        }

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        bitmapDrawable.getBitmap().recycle();
        imageView.setImageDrawable(null);
    }
}
