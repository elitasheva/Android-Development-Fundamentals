package com.example.etasheva.spotifyapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewCuctomDecoration extends RecyclerView.ItemDecoration {

    private Paint mBackground;
    private int offset;
    private Context mContext;

    public RecyclerViewCuctomDecoration(Context context) {
        this.mContext = context;
        this.offset = 0;

        this.mBackground = new Paint();

        int backgroundColor = ContextCompat.getColor(context, R.color.color_background);

        this.mBackground.setColor(backgroundColor);

        this.mBackground.setStyle(Paint.Style.FILL);

        //this.mBackground.setStrokeWidth(5f);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + offset,
                    layoutManager.getDecoratedTop(child) + offset,
                    layoutManager.getDecoratedRight(child) - offset,
                    layoutManager.getDecoratedBottom(child) - offset,
                    this.mBackground);


            ImageView imageView = (ImageView) child.findViewById(R.id.imageView);;
           if (!child.isDrawingCacheEnabled()){
               if (i % 2 == 0) {
                   imageView.setImageResource(R.drawable.download_cut);
                   imageView.setMaxWidth(20);
               } else {
                   imageView.setImageResource(R.drawable.explicit_cut);
                   imageView.setMaxWidth(40);
               }
           }
            child.setDrawingCacheEnabled(true);
            //layoutManager.addView(imageView);


        }

    }
}
