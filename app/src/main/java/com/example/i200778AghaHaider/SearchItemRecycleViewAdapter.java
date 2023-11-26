package com.example.i200778AghaHaider;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SearchItemRecycleViewAdapter extends RecyclerView.Adapter<SearchItemRecycleViewAdapter.ViewHolder> {


    Context context;
    List<Item> FilterList;

    public SearchItemRecycleViewAdapter(Context context, List<Item> filterList) {
        this.context = context;
        FilterList = filterList;
    }

    @NonNull
    @Override
    public SearchItemRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false); // Replace with the correct item layout
        return new SearchItemRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemRecycleViewAdapter.ViewHolder holder, int position) {
        Item item = FilterList.get(position);
        holder.ItemName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return FilterList.size();
    }

    public void setDataList(ArrayList<Item> filterSearchList) {
        this.FilterList=filterSearchList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button ItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ItemName = itemView.findViewById(R.id.SearchedItem);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Item item = FilterList.get(position);

                // Create an intent to start the ItemScreen activity
                Intent intent = new Intent(context, ItemScreen.class);

                // Pass the item data to the ItemScreen activity
                intent.putExtra("Item_Name", item.getName());
                intent.putExtra("Item_Location", item.getCity());
                intent.putExtra("Item_Rate", Double.toString(item.getRate()));
                intent.putExtra("ITEM_IMAGE_URL", item.getImageURI());
                intent.putExtra("Item_Description", item.getDescription());
                intent.putExtra("UserName", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                intent.putExtra("UserContactNo", FirebaseAuth.getInstance().getCurrentUser());
                // Start the activity
                context.startActivity(intent);
            }
        }


    }
}