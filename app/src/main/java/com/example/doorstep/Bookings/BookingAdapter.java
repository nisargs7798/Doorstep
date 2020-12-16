package com.example.doorstep.Bookings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doorstep.R;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    List<String> titles;
    List<String> dates;
    List<String> startTime;
    List<String> endTime;
    List<String> price;
    List<String> status;
    LayoutInflater inflater;
    public BookingAdapter(Context ctx, List<String> titles, List<String> dates, List<String> startTime, List<String> endTime, List<String> price,List<String> status ) {
        this.titles = titles;
        this.dates = dates;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.status = status;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_bookings_layout, parent, false);

        return new BookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.date.setText(dates.get(position));
        holder.startTime.setText(startTime.get(position));
        holder.endTime.setText(endTime.get(position));
        holder.price.setText(price.get(position));
        holder.status.setText(status.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView title, date, startTime, endTime, price, status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_book_service_title);
            date = itemView.findViewById(R.id.tv_book_date);
            startTime = itemView.findViewById(R.id.tv_book_start_time);
            endTime = itemView.findViewById(R.id.tv_book_end_time);
            price = itemView.findViewById(R.id.tv_book_price);
            status = itemView.findViewById(R.id.tv_book_status);
        }
    }
}
