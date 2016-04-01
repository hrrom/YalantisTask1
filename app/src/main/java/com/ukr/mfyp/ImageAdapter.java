package com.ukr.mfyp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private String[] mImages;
    private Context mContext;
    private int mImageSize;          // Size of image side for resize

    public ImageAdapter(Context context, String[] images, int imageSize) {
        mContext = context;
        mImages = images;
        mImageSize = imageSize;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        // Changing by half width of items of RecycleView

        Picasso
               .with(mContext)
               .load(mImages[i])
               .resize(mImageSize, mImageSize)
               .centerCrop()
               .placeholder(R.mipmap.clock)
               .error(R.mipmap.no_image)
               .into(viewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImage;

        public ViewHolder(View v) {
            super(v);
            mImage = (ImageView)v.findViewById(R.id.item_view);
            mImage.setOnClickListener(this);

        }

        /** Process pressing on item of RecyclerView and shows Toast message  */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(mContext, "Image #" + position + " is clicked", Toast.LENGTH_SHORT).show();

        }

    }
}
