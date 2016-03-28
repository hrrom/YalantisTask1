package com.ukr.mfyp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//[Comment] Wrong colors and image sizes. Image sizes should be the same
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
    /** initialization for Back Arrow, RecycleView */
    private void initViews(){
        // display Back Arrow in ActionBar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar(); //[Comment] Please optimize import
        actionBar.setDisplayHomeAsUpEnabled(true); //[Comment] Action bar can be null

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Resources res = getResources();
        String[] images = res.getStringArray(R.array.images_array);
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(),images);
        recyclerView.setAdapter(adapter);
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
