package com.example.holidayapi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holidayapi.R;
import com.example.holidayapi.entity.DataPersonalHoliday;
import com.example.holidayapi.ui.AddContact;

import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.viewHolder> {
    Context context;
    List<DataPersonalHoliday> list;
    AddContact.view aView;

    public AddAdapter(Context context, List<DataPersonalHoliday> list, AddContact.view aView){
        this.context = context;
        this.list = list;
        this.aView = aView;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.add_item,parent,false);
        return new viewHolder(view);
    }

    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataPersonalHoliday item = list.get(position);
        holder.tvPersonalHoliday.setText(item.getName());
        holder.tvPersonalDate.setText(item.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                aView.deleteData(item);
                return true;
            }
        });
    }

    public int getItemCount() {
        return  list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvPersonalHoliday, tvPersonalDate;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvPersonalHoliday = itemView.findViewById(R.id.tvPersonalHoliday);
            tvPersonalDate = itemView.findViewById(R.id.tvPersonalDate);
            cardView = itemView.findViewById(R.id.cv_item);
        }

    }
}


