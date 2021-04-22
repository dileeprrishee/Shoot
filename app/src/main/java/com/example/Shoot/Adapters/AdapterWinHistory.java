package com.example.Shoot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Shoot.Pojo.RsponseWinnerHistory;
import com.example.Shoot.Pojo.WinnerDetails;
import com.example.Shoot.R;

import java.util.List;

public class AdapterWinHistory extends RecyclerView.Adapter<AdapterWinHistory.ViewHolder> {
    private Context context;
    private List<RsponseWinnerHistory> winnerHistoryList;
    private boolean isopen = false;


    public AdapterWinHistory(Context context, List<RsponseWinnerHistory> winnerHistoryList) {
        this.context = context;
        this.winnerHistoryList = winnerHistoryList;
    }

    @NonNull

    @Override
    public AdapterWinHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterWinHistory.ViewHolder holder, int position) {
        RsponseWinnerHistory history = winnerHistoryList.get(position);
        List<WinnerDetails> details = history.getWinnerDetails();
        holder.tv_matchid.setText("Order Id : " + history.getMatchId());
        holder.tv_number.setText("Winning Number : " + history.getNumberWon());
        for (int i = 0; i < details.size(); i++) {
            setRcv(holder, details);
        }

        holder.lay_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isopen) {
                    holder.lay_hide.setVisibility(View.GONE);
                    isopen = false;
                } else if (!isopen) {
                    holder.lay_hide.setVisibility(View.VISIBLE);
                    isopen = true;

                }
            }
        });

    }

    private void setRcv(ViewHolder holder, List<WinnerDetails> details) {
        holder.rcv_details.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.rcv_details.setAdapter(new AdapterWinHistoryDetails(context, details));
    }

    @Override
    public int getItemCount() {
        return winnerHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time, tv_number, tv_amount, tv_win, tv_winnum, tv_matchid;
        private RecyclerView rcv_details;
        private ConstraintLayout lay_hide, lay_head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_win = itemView.findViewById(R.id.tv_win);
            tv_winnum = itemView.findViewById(R.id.tv_winnum);
            rcv_details = itemView.findViewById(R.id.rcv_details);
            lay_hide = itemView.findViewById(R.id.lay_hide);
            lay_head = itemView.findViewById(R.id.lay_head);

            tv_matchid = itemView.findViewById(R.id.tv_matchid);
        }
    }

    private class AdapterWinHistoryDetails extends RecyclerView.Adapter<AdapterWinHistoryDetails.Holder> {
        private Context mCntxt;
        private List<WinnerDetails> mList;

        public AdapterWinHistoryDetails(Context context, List<WinnerDetails> details) {
            this.mCntxt = context;
            this.mList = details;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_historydetails, parent, false);
            Holder viewHolder = new Holder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            WinnerDetails details = mList.get(position);
            holder.tv_time.setText(details.getDatePlayed());
            holder.tv_number.setText(details.getNumberReserved());
            holder.tv_amount.setText(details.getAmount() + "");

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            private TextView tv_time, tv_number, tv_amount, tv_win, tv_winnum;

            public Holder(@NonNull View itemView) {
                super(itemView);
                tv_time = itemView.findViewById(R.id.tv_time);
                tv_number = itemView.findViewById(R.id.tv_number);
                tv_amount = itemView.findViewById(R.id.tv_amount);
                tv_win = itemView.findViewById(R.id.tv_win);
                tv_winnum = itemView.findViewById(R.id.tv_winnum);
            }
        }
    }
}
