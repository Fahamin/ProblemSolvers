package com.tbl.problemsolvers.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tbl.problemsolvers.R;

import java.util.List;
import java.util.zip.Inflater;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.randomHolder> {

    Activity activity;
    List<String> list;

    public RandomAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
        Log.e("list", "" + list.size());
    }

    @NonNull
    @Override
    public randomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new randomHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.random_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull randomHolder holder, int position) {
        String s = list.get(position);

        holder.randTxt.setText(s);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class randomHolder extends RecyclerView.ViewHolder {

        TextView randTxt;
        LinearLayout container;

        public randomHolder(@NonNull View itemView) {
            super(itemView);
            randTxt = itemView.findViewById(R.id.randomID);
            container = itemView.findViewById(R.id.containerRec);

        }
    }

}
