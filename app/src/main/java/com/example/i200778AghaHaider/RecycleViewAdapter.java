package com.example.i200778AghaHaider;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private Context context;
    private List<Item> itemList;

    public RecycleViewAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false); // Replace with the correct item layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Item item=itemList.get(position);
            holder.ItemName.setText(item.getName());
        holder.Location.setText(item.getCity());
        holder.HourlyRate.setText(String.valueOf(item.getRate()));

        String imageUrl = item.getImageURI();
        Picasso.get().load(imageUrl).into(holder.ItemImage);

    }

    @Override
    public int getItemCount() {
       return  itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ItemName, Location, HourlyRate;
        public ImageView ItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ItemName = itemView.findViewById(R.id.ItemName);
            Location = itemView.findViewById(R.id.Item_Location);
            HourlyRate = itemView.findViewById(R.id.Item_hourRate);

            ItemImage = itemView.findViewById(R.id.itemImage);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Item item = itemList.get(position);

                // Create an intent to start the ItemScreen activity
                Intent intent = new Intent(context, ItemScreen.class);

                // Pass the item data to the ItemScreen activity
                intent.putExtra("Item_Name", item.getName());
                intent.putExtra("Item_Location", item.getCity());
                intent.putExtra("Item_Rate", Double.toString(item.getRate()));
                intent.putExtra("ITEM_IMAGE_URL", item.getImageURI());
                intent.putExtra("Item_Description", item.getDescription());
//                intent.putExtra("UserName", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
//                intent.putExtra("UserContactNo",FirebaseAuth.getInstance().getCurrentUser());
                // Start the activity
                context.startActivity(intent);
            }
        }

    }}


