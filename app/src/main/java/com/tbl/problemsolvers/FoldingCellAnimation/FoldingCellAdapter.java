package com.tbl.problemsolvers.FoldingCellAnimation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;
import com.tbl.problemsolvers.R;

import java.util.HashSet;
import java.util.List;


public class FoldingCellAdapter extends RecyclerView.Adapter<FoldingCellAdapter.randomHolder> {
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    Activity activity;
    private List<Item> list;
    MainListClick mainListClick;
    DetailsClickListener detailsClickListener;

    public FoldingCellAdapter(Activity activity, List<Item> list, MainListClick mainListClick, DetailsClickListener detailsClickListener) {
        this.activity = activity;
        this.list = list;
        this.mainListClick = mainListClick;
        this.detailsClickListener = detailsClickListener;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    @NonNull
    @Override
    public randomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new randomHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull randomHolder holder, int position) {
// get item for selected view
        Item item = list.get(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) holder.itemView;
        if (cell == null) {
            cell.setTag(holder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
        }
        holder.price.setText("" + item.getPrice());
        holder.time.setText("" + item.getTime());
        holder.date.setText("" + item.getDate());
        holder.fromAddress.setText("" + item.getFromAddress());
        holder.toAddress.setText("" + item.getToAddress());
        holder.requestsCount.setText("" + String.valueOf(item.getRequestsCount()));
        holder.pledgePrice.setText("" + item.getPledgePrice());

        holder.mainLayoutID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainListClick.onMainListClick(position);
            }
        });

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsClickListener.detailsLayoutClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class randomHolder extends RecyclerView.ViewHolder {

        TextView price;
        TextView contentRequestBtn;
        TextView pledgePrice;
        TextView fromAddress;
        TextView toAddress;
        TextView requestsCount;
        TextView date;
        TextView time;

        LinearLayout mainLayoutID;
        LinearLayout detailLayout;

        public randomHolder(@NonNull View itemView) {
            super(itemView);
            mainLayoutID = itemView.findViewById(R.id.mainLayoutID);
            detailLayout = itemView.findViewById(R.id.detailsID);
            price = itemView.findViewById(R.id.title_price);
            time = itemView.findViewById(R.id.title_time_label);
            date = itemView.findViewById(R.id.title_date_label);
            fromAddress = itemView.findViewById(R.id.title_from_address);
            toAddress = itemView.findViewById(R.id.title_to_address);
            requestsCount = itemView.findViewById(R.id.title_requests_count);
            pledgePrice = itemView.findViewById(R.id.title_pledge);
            contentRequestBtn = itemView.findViewById(R.id.content_request_btn);
        }
    }

    public interface DetailsClickListener {
        void detailsLayoutClick(int position, View view);
    }

    public interface MainListClick {
        void onMainListClick(int position);
    }
}

