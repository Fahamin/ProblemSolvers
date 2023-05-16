package com.tbl.problemsolvers.LeaderBoard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbl.problemsolvers.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderBoardActivity extends AppCompatActivity implements LeaderBoardAdapter.LeaderBoardListClick{
    @BindView(R.id.learBoardRecycleView)
    RecyclerView recyclerView;
    List<LeaderBordModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        ButterKnife.bind(this);

        list = new ArrayList<>();

        list.add(new LeaderBordModel("Habib", "1200"));
        list.add(new LeaderBordModel("karim", "1000"));
        list.add(new LeaderBordModel("Nabid", "800"));
        list.add(new LeaderBordModel("Monir", "500"));
        list.add(new LeaderBordModel("Kamal", "300"));
        list.add(new LeaderBordModel("Jamal", "100"));

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(this, list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void leaderBoardItemClickListener(int position) {

        Intent i = new Intent(this,ProfileActivity.class);
        i.putExtra("postion",position);
        startActivity(i);
    }
}