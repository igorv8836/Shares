package com.example.shares;



import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shares.databinding.ActivityElementBinding;

import java.util.List;

public class SharesItemAdapter extends RecyclerView.Adapter<SharesItemAdapter.SharesItemViewHolder> {

    List<SharesItem> data;

    public SharesItemAdapter(List<SharesItem> data){
        this.data = data;
    }

    @NonNull
    @Override
    public SharesItemViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        ActivityElementBinding binding =
                ActivityElementBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                false
        );

        return new SharesItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SharesItemViewHolder holder, int position) {
        holder.name.setText(SharesAPI.NAME[position]);
        holder.full_name.setText(SharesAPI.FULL_NAME[position]);
        holder.price.setText(String.valueOf(data.get(position).c) + "$");
        holder.change_number.setText(String.valueOf(data.get(position).d) + "$");
        holder.change_percent.setText(String.valueOf(data.get(position).dp) + "%");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SharesItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView full_name;
        TextView price;
        TextView change_percent;
        TextView change_number;


        public SharesItemViewHolder(ActivityElementBinding binding) {
            super(binding.getRoot());
            name = binding.name;
            full_name = binding.fullName;
            price = binding.price;
            change_number = binding.changeNumber;
            change_percent = binding.changePercent;
        }
    }
}
