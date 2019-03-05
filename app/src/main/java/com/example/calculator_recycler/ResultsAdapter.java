package com.example.calculator_recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {

        private final List<String> data = new ArrayList<>();
        public ResultsAdapter(List<String> data){
            this.data.addAll(data);
        }

        @Override
        public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler,
                    viewGroup, false);
            return new ResultsViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultsViewHolder resultsViewHolder,
                                     int position) {
            resultsViewHolder.tvLogs.setText(data.get(position));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
        static class ResultsViewHolder extends RecyclerView.ViewHolder{
            TextView tvLogs;
            public ResultsViewHolder(@NonNull View itemView) {
                super(itemView);
                tvLogs = itemView.findViewById(R.id.tvLogs);
            }
        }
    }
