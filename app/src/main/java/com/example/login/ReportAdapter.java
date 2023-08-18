package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {
    ArrayList<ModelActivity> reportList = new ArrayList<>();

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent, false);
        return new ReportAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder holder, int position) {
        holder.tvHoliday.setText(reportList.get(position).getHolidayName());
        holder.tvDay.setText(reportList.get(position).getHoliday());
        holder.tvDate.setText(reportList.get(position).getDateHoliday());
    }


    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvHoliday;
        TextView tvDay;
        TextView tvDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoliday=(TextView) itemView.findViewById(R.id.tvHoliday);
            tvDay=(TextView) itemView.findViewById(R.id.tvDay);
            tvDate=(TextView) itemView.findViewById(R.id.tvDate);


        }
    }
    public ReportAdapter(ArrayList<ModelActivity>reportList)
    {
        this.reportList = reportList;
    }
}