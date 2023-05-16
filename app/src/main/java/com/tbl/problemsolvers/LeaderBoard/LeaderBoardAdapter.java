package com.tbl.problemsolvers.LeaderBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tbl.problemsolvers.R;

import java.util.List;

class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {
    private int animation_type = 3;
    Context context;
    List<LeaderBordModel> list;
    LeaderBoardListClick leaderBoardListClick;

    public LeaderBoardAdapter(Context context, List<LeaderBordModel> list, LeaderBoardListClick leaderBoardListClick) {
        this.context = context;
        this.list = list;
        this.leaderBoardListClick = leaderBoardListClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leader_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.coinTxt.setText(list.get(position).getEarnCoinNum());
        holder.nameTxt.setText(list.get(position).getPsrName());
        if (position == 0) {
            holder.rankingIV.setImageResource(R.drawable.ranking_one);
        }
        if (position == 1) {
            holder.rankingIV.setImageResource(R.drawable.ranking_two);
        }
        if (position == 2) {
            holder.rankingIV.setImageResource(R.drawable.ranking_three);
        }
        setAnimation(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaderBoardListClick.leaderBoardItemClickListener(position);
            }
        });
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }


    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, coinTxt;
        RoundedImageView rankingIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rankingIV = itemView.findViewById(R.id.image);
            nameTxt = itemView.findViewById(R.id.psrNameTv);
            coinTxt = itemView.findViewById(R.id.coinTV);
        }
    }


    public interface LeaderBoardListClick {
        void leaderBoardItemClickListener(int position);

    }
}

