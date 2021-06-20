package com.example.holidayapi.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holidayapi.R;
import com.example.holidayapi.data.HolidaysItem;

import java.util.ArrayList;

// membuat kelas adapter sebagai penghubung antara list dan itemnya
public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {
    // deklarasi list yang akan ditampilkan
    private ArrayList<HolidaysItem> holidays = new ArrayList<>();

    // mengisi data list yang akan ditampilkan
    public void setHolidays(ArrayList<HolidaysItem> holidays) {
        if (holidays != null) {
            this.holidays.clear();
            this.holidays.addAll(holidays);
        }
        // update tampilan jika terdapat perubahan data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        // mengkoneksikan layout item kedalam adapter ini
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.ViewHolder holder, int position) {
        // mengirimkan data tiap satuan item ke kelas ViewHolder untuk ditampilkan
        holder.bind(holidays.get(position));
    }

    // mengambil panjang list yang ada
    @Override
    public int getItemCount() {
        return holidays.size();
    }

    // membuat kelas sebagai kontrol item didalam view(holiday_item.xml) agar dapat dimanipulasi
    public class ViewHolder extends RecyclerView.ViewHolder {
        // deklarasi variabel view sesuai dengan layout xml
        private TextView tvName, tvDate, tvCountry;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //koneksikan variabel dengan view berdasarkan id
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvCountry = itemView.findViewById(R.id.tv_item_country);
        }

        public void bind(HolidaysItem holiday) {
            tvName.setText(holiday.getName());
            tvDate.setText(holiday.getDate());
            tvCountry.setText(holiday.getCountry());

//            //melakukan pindah halaman ke halaman detail
//            itemView.setOnClickListener(v -> {
//                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
//                // mengirim data id ke detail dengan key tertentu, yaitu EXTRA_ID
//                intent.putExtra(DetailActivity.EXTRA_ID, holiday.getId());
//                // memulai pindah halaman dengan bantuan context dari itemView
//                itemView.getContext().startActivity(intent);
//            });
        }
    }
}
