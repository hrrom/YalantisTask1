package com.ukr.mfyp;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private String[] images; //[Comment] Use google code style. Wrong name
    private Context context; //[Comment] Use google code style. Wrong name

    public ImageAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);

        // Changing by half width of items of RecycleView
        DisplayMetrics displaymetrics = new DisplayMetrics(); //[Comment] Unused
        int width = (Resources.getSystem().getDisplayMetrics().widthPixels) / 2 - 24; //[Comment] Magic numbers
        LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT); //[Comment] Use Item Decoration instead layout params
        view.setLayoutParams(imgParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
       Picasso
               .with(context)
               .load(images[i])
               .fit()
               .centerInside()
               .placeholder(R.mipmap.clock)
               .error(R.mipmap.no_image)
               .into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView img_android; //[Comment] Wrong name, wrong visibility modifier

        public ViewHolder(View v) {
            super(v);
            img_android = (ImageView)v.findViewById(R.id.item_view);
            img_android.setOnClickListener(this);

        }
        /** Process pressing on item of RecycleView and shows Toast message   */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(context, "Image #" + position + " is clicked", Toast.LENGTH_SHORT).show();

        }

    }
}
