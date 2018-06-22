package com.like.ne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener{
    private static final int NUM_LIST_ITEMS = 100;
    private GreenAdapter greenAdapter;
    private RecyclerView numbersList;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbersList = (RecyclerView) findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);
        numbersList.hasFixedSize();
        greenAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        numbersList.setAdapter(greenAdapter);
    }

    @Override
    public void onListItemClick(int position) {
        if (toast != null)
            toast.cancel();
        toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
