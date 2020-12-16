package com.example.doorstep.History;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doorstep.Home.ServicesAdapter;
import com.example.doorstep.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    RecyclerView rv_history;

    List<String> titles;
    List<String> dates;
    List<String> startTime;
    List<String> endTime;
    List<String> price;
    HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_history, container, false);
        rv_history = root.findViewById(R.id.rv_history_list);
        titles = new ArrayList<>();
        dates = new ArrayList<>();
        startTime = new ArrayList<>();
        endTime = new ArrayList<>();
        price = new ArrayList<>();
        titles.add("Office cleaning");
        titles.add("Home Cleaning");
        titles.add("Plumbing");
        titles.add("Electrical");

        dates.add("16/12/2020");
        dates.add("15/1/2018");
        dates.add("1/2/2020");
        dates.add("7/5/2008");

        startTime.add("08:57 AM");
        startTime.add("09:52 AM");
        startTime.add("10:41 AM");
        startTime.add("09:44 AM");

        endTime.add("05:00 PM");
        endTime.add("05:10 PM");
        endTime.add("05:15 PM");
        endTime.add("05:20 PM");

        price.add("100");
        price.add("200");
        price.add("300");
        price.add("400");

        adapter = new HistoryAdapter(getActivity(), titles,dates,startTime,endTime,price);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        rv_history.setLayoutManager(gridLayoutManager);
        rv_history.setAdapter(adapter);

        return root;
    }
}
