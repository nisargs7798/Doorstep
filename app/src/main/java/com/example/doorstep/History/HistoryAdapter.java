package com.example.doorstep.History;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doorstep.Home.ServicesAdapter;
import com.example.doorstep.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<String> titles;
    List<String> dates;
    List<String> startTime;
    List<String> endTime;
    List<String> price;
    LayoutInflater inflater;
    public HistoryAdapter(Context ctx, List<String> titles,List<String> dates,List<String> startTime, List<String> endTime,List<String> price ) {
        this.titles = titles;
        this.dates = dates;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_history_layout, parent, false);

        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.date.setText(dates.get(position));
        holder.startTime.setText(startTime.get(position));
        holder.endTime.setText(endTime.get(position));
        holder.price.setText(price.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView title, date, startTime, endTime, price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_service_title);
            date = itemView.findViewById(R.id.tv_date);
            startTime = itemView.findViewById(R.id.tv_start_time);
            endTime = itemView.findViewById(R.id.tv_end_time);
            price = itemView.findViewById(R.id.tv_price);
        }
    }
}
