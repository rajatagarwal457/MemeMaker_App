package com.example.memeapp;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c){
        mContext =c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(985, 1200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(28, 18, 8, 15);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    public Integer[] mThumbIds = {
            R.drawable.img0, R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6, R.drawable.img7, R.drawable.img8,
            R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12,
            R.drawable.img13, R.drawable.img14, R.drawable.img15,
            R.drawable.img16, R.drawable.img17, R.drawable.img18,
            R.drawable.img19, R.drawable.img20, R.drawable.img21, R.drawable.img22,
            R.drawable.img23, R.drawable.img24, R.drawable.img25,
            R.drawable.img26, R.drawable.img27, R.drawable.img28,
            R.drawable.img29, R.drawable.img30, R.drawable.img31, R.drawable.img32,
            R.drawable.img33, R.drawable.img34, R.drawable.img35,
            R.drawable.img36, R.drawable.img37, R.drawable.img38,
            R.drawable.img39, R.drawable.img40, R.drawable.img41, R.drawable.img42,
            R.drawable.img43, R.drawable.img44, R.drawable.img45,
            R.drawable.img46, R.drawable.img47, R.drawable.img48,
            R.drawable.img49, R.drawable.img50, R.drawable.img51, R.drawable.img52,
            R.drawable.img53
    };

    public  Integer[] mExmaples= {
        R.drawable.qe0, R.drawable.qe1, R.drawable.qe2, R.drawable.qe3,
            R.drawable.qe4, R.drawable.qe5, R.drawable.qe6, R.drawable.qe7,
            R.drawable.qe8, R.drawable.qe9, R.drawable.qe10, R.drawable.qe11,
            R.drawable.qe12, R.drawable.qe13, R.drawable.qe14, R.drawable.qe15,
            R.drawable.qe16, R.drawable.qe17, R.drawable.qe18, R.drawable.qe19,
            R.drawable.qe20, R.drawable.qe21, R.drawable.qe22, R.drawable.qe23,
            R.drawable.qe24, R.drawable.qe25,R.drawable.qe26, R.drawable.qe27,
            R.drawable.qe28, R.drawable.qe29, R.drawable.qe30, R.drawable.qe31,
            R.drawable.qe32, R.drawable.qe33,R.drawable.qe34, R.drawable.qe35,
            R.drawable.qe36, R.drawable.qe37, R.drawable.qe38, R.drawable.qe39,
            R.drawable.qe40, R.drawable.qe41, R.drawable.qe42,R.drawable.qe43,
            R.drawable.qe44, R.drawable.qe45, R.drawable.qe46, R.drawable.qe47,
            R.drawable.qe48, R.drawable.qe49, R.drawable.qe50, R.drawable.qe51,
            R.drawable.qe52,  R.drawable.qe53,
    };

}
