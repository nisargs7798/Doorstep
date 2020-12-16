package com.example.doorstep.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doorstep.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ServicesAdapter.onServiceListener{

        RecyclerView serviceList;

    List<String> titles;
    List<Integer> images;

    ServicesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);


        serviceList = root.findViewById(R.id.rv_list_of_services);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Office cleaning");
        titles.add("Home Cleaning");
        titles.add("Plumbing");
        titles.add("Electrical");
        titles.add("Carpenter");
        titles.add("Vehicle cleaning");
        titles.add("Lawn");
        titles.add("Snow Removal");
        titles.add("Home renovation");

        images.add(R.drawable.ic_baseline_delete_sweep_24);
        images.add(R.drawable.ic_baseline_home_24);
        images.add(R.drawable.ic_baseline_home_24);
        images.add(R.drawable.ic_baseline_delete_sweep_24);
        images.add(R.drawable.ic_baseline_room_service_24);
        images.add(R.drawable.ic_baseline_home_24);
        images.add(R.drawable.ic_baseline_delete_sweep_24);
        images.add(R.drawable.ic_baseline_room_service_24);
        images.add(R.drawable.ic_baseline_home_24);

        adapter = new ServicesAdapter(getActivity(), titles, images, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        serviceList.setLayoutManager(gridLayoutManager);
        serviceList.setAdapter(adapter);



        return root;
    }

    @Override
    public void onServiceClicked(int position) {
        titles.get(position);
        Intent intent = new Intent(getActivity(), ServiceDetailsActivity.class);
        startActivity(intent);
    }
}
