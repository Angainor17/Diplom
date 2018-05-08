package com.example.angai.diplom.notification.presentation.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.notification.business.RouteNotification;

import java.util.ArrayList;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder> {

    private ArrayList<RouteNotification> notifications;
    private LayoutInflater layoutInflater;
    private OnClickListener itemClickListener;

    public NotificationListAdapter(Context context, ArrayList<RouteNotification> notifications) {
        this.notifications = notifications;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setItemClickListener(OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.notification_list_item, parent, false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        RouteNotification item = notifications.get(position);

        holder.timeTv.setText(item.getTime());
        holder.routeTv.setText(item.getRouteName());
        holder.busStopTv.setText(item.getBusStopName());
        holder.activateSwitch.setChecked(item.isCheched());

        initClickEvent(holder, item);

        initSwitchClickEvent(holder, item);
    }

    private void initSwitchClickEvent(@NonNull NotificationViewHolder holder, RouteNotification item) {
        holder.activateSwitch.setOnClickListener(v -> {
            boolean isEnable = holder.activateSwitch.isChecked();
            holder.timeTv.setEnabled(isEnable);
            holder.routeTv.setEnabled(isEnable);
            holder.busStopTv.setEnabled(isEnable);
            holder.lastTimeTv.setVisibility(isEnable ? View.VISIBLE : View.GONE);
            if (isEnable) {
                holder.lastTimeTv.setText(item.getRemainingTime());
            }
        });
    }

    private void initClickEvent(@NonNull NotificationViewHolder holder, RouteNotification item) {
        View.OnClickListener onClickListener = v -> {
            if (itemClickListener != null) {
                itemClickListener.onClick(item);
            }
        };

        holder.timeTv.setOnClickListener(onClickListener);
        holder.routeTv.setOnClickListener(onClickListener);
        holder.busStopTv.setOnClickListener(onClickListener);
        holder.lastTimeTv.setOnClickListener(onClickListener);
        holder.constraintLayout.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView timeTv;
        TextView routeTv;
        TextView busStopTv;
        TextView lastTimeTv;
        Switch activateSwitch;
        ConstraintLayout constraintLayout;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.time_tv);
            routeTv = itemView.findViewById(R.id.route_tv);
            busStopTv = itemView.findViewById(R.id.bus_stop_tv);
            lastTimeTv = itemView.findViewById(R.id.last_time_tv);
            activateSwitch = itemView.findViewById(R.id.activate_switch);
            constraintLayout = itemView.findViewById(R.id.parent);
        }
    }

    public interface OnClickListener {

        void onClick(RouteNotification item);

    }
}
