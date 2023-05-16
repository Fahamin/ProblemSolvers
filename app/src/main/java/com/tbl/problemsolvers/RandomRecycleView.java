package com.tbl.problemsolvers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tbl.problemsolvers.adapter.CustomGridRecyclerView;
import com.tbl.problemsolvers.adapter.RandomAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomRecycleView extends AppCompatActivity {

    Button refreshBtn;
    CustomGridRecyclerView recyclerView;
    List<String> randomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_recycle_view);
        recyclerView = findViewById(R.id.randomRecID);
        refreshBtn = findViewById(R.id.refreshBtn);

        randomList = new ArrayList<>();

        randomList.add(("A"));
        randomList.add(("B"));
        randomList.add(("C"));
        randomList.add(("D"));
        randomList.add(("E"));
        randomList.add(("F"));
        randomList.add(("G"));
        randomList.add(("H"));
        randomList.add(("I"));
        randomList.add(("J"));
        randomList.add(("K"));
        randomList.add(("H"));
        randomList.add(("I"));
        randomList.add(("J"));
        randomList.add(("K"));

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refesh();
            }
        });

    }

    public void refesh() {
        Log.e("list", "" + randomList.size());

        Collections.shuffle(randomList);
        RandomAdapter adapter = new RandomAdapter(RandomRecycleView.this, randomList);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(adapter);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(this, R.anim.rotate);
        recyclerView.setLayoutAnimation(controller);
        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
        //  adapter.notifyDataSetChanged();
    }
}