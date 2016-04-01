package com.ukr.mfyp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    /** Handler for Back Arrow in ActionBar */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    /** Initialization for Back Arrow, RecycleView */
    private void initViews(){
        // display Back Arrow in ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        Resources res = getResources();
        String[] images = res.getStringArray(R.array.images_array);
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(),images, computeImageSize());
        mRecyclerView.setAdapter(adapter);

        int sizeInPixel = getResources().getDimensionPixelSize(R.dimen.padding_4);
        mRecyclerView.addItemDecoration(new PaddingItemDecoration(sizeInPixel));
    }

    /** Computing  width of RecyclerView item for displaying two items at the same time  */
    public int computeImageSize() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int layoutPadding = (int) getResources().getDimension(R.dimen.padding_16);
        int imagePadding = (int) getResources().getDimension(R.dimen.padding_4);
        return (screenWidth - layoutPadding*2 - imagePadding*2)/2;
    }

    /** Process pressing on View and shows Toast message         */
    public void onClickViewHandler(View v){
        int id = v.getId();                                 // get  ID of view
        String IDString = "No ID";
        if(id != View.NO_ID) {                              // check ID isn't empty
            Resources res = v.getResources();               // get resources
            if(res != null)
                IDString = res.getResourceEntryName(id);    // get ID string entry
        }
        Toast.makeText(this, IDString, Toast.LENGTH_SHORT).show();
    }
}
