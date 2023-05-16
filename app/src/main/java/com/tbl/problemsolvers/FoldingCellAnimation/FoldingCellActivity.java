package com.tbl.problemsolvers.FoldingCellAnimation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;
import com.tbl.problemsolvers.R;

import java.util.List;

public class FoldingCellActivity extends AppCompatActivity implements FoldingCellAdapter.MainListClick, FoldingCellAdapter.DetailsClickListener {
    FoldingCellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folding_cell2);
        // get our folding cell
        // get our list view
        RecyclerView theListView = findViewById(R.id.mainListView);
        // prepare elements to display
        final List<Item> items = Item.getTestingList();
        FoldingCellAdapter adapter = new FoldingCellAdapter(this, items, this, this);
        theListView.setLayoutManager(new LinearLayoutManager(this));
        theListView.setAdapter(adapter);
    }

    @Override
    public void detailsLayoutClick(int position, View view) {
        ((FoldingCell) view).toggle(false);

    }

    @Override
    public void onMainListClick(int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
}