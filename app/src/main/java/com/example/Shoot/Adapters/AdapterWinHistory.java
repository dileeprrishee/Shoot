package com.example.Shoot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Shoot.Pojo.RsponseWinnerHistory;
import com.example.Shoot.R;
import java.util.List;

public class AdapterWinHistory extends RecyclerView.Adapter<AdapterWinHistory.ViewHolder> {
    private Context context;
    private List<RsponseWinnerHistory> winnerHistoryList;


    public AdapterWinHistory(Context context, List<RsponseWinnerHistory> winnerHistoryList) {
        this.context = context;
        this.winnerHistoryList = winnerHistoryList;
    }
    @NonNull
    
    @Override
    public AdapterWinHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_history,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWinHistory.ViewHolder holder, int position) {
        RsponseWinnerHistory history =  winnerHistoryList.get(position);
            holder.tv_amount.setText(history.getAmount());
            holder.tv_number.setText(history.getNumber());
            holder.tv_time.setText(history.getDatePlayed());
            holder.tv_winnum.setText(history.getWonNumber());
            String win = history.getWonOrNot();
            if (win.equals("1")){
                holder.tv_win.setText("Win");
            }
            else if (win.equals("0")){
                holder.tv_win.setText("Not WIn");
            }

    }

    @Override
    public int getItemCount() {
        return winnerHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time,tv_number,tv_amount,tv_win,tv_winnum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time     = itemView.findViewById(R.id.tv_time);
            tv_number   = itemView.findViewById(R.id.tv_number);
            tv_amount   = itemView.findViewById(R.id.tv_amount);
            tv_win      = itemView.findViewById(R.id.tv_win);
            tv_winnum   = itemView.findViewById(R.id.tv_winnum);
        }
    }
}
