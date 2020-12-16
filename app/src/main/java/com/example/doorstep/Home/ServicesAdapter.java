package com.example.doorstep.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doorstep.R;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    private onServiceListener mOnserviceListener;

    LayoutInflater inflater;
    public ServicesAdapter(Context ctx, List<String> titles, List<Integer> images, onServiceListener onServiceListener) {
        this.titles = titles;
        this.images = images;
        this.mOnserviceListener = onServiceListener;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_services_layout, parent, false);

        return new ViewHolder(view, mOnserviceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.image.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;
        onServiceListener onServiceListener;

        public ViewHolder(@NonNull View itemView, onServiceListener onServiceListener) {
            super(itemView);
            image = itemView.findViewById(R.id.img_service_img);
            title = itemView.findViewById(R.id.txt_service_title);
            this.onServiceListener = onServiceListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onServiceListener.onServiceClicked(getAdapterPosition());
        }
    }
    public interface onServiceListener{
        void onServiceClicked(int position);
    }
}
