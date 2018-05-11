package com.example.angai.diplom.notification.presentation.view.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.notification.business.RouteNotification;

import java.util.ArrayList;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder> {

    private ArrayList<RouteNotification> notifications;
    private LayoutInflater layoutInflater;
    private OnClickListener itemClickListener;
    private Context context;

    public NotificationListAdapter(Context context, ArrayList<RouteNotification> notifications) {
        this.context = context;
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

        holder.timeTv.setText(item.getTimeWhen());
        holder.timeBefore.setText("(с учётом пути до остановки " + item.getTimeBefore() + ")");
        holder.routeTv.setText(item.getRouteName());
        holder.busStopTv.setText(item.getBusStopName());

        holder.activateSwitch.setImageDrawable(getSwitchDrawable(item.isChecked()));

        initClickEvent(holder, item);

        initSwitchClickEvent(holder, item);
    }

    private Drawable getSwitchDrawable(boolean isChecked) {
        int resourceId = isChecked ? R.drawable.ic_notifications_active_green_large :
                R.drawable.ic_notifications_off_black_large;

        return ContextCompat.getDrawable(context, resourceId);
    }

    private void initSwitchClickEvent(@NonNull NotificationViewHolder holder, RouteNotification item) {
        holder.activateSwitch.setOnClickListener(v -> {
            item.setActive(!item.isChecked());

            boolean isEnable = item.isChecked();

            holder.activateSwitch.setImageDrawable(getSwitchDrawable(isEnable));

            holder.timeTv.setEnabled(isEnable);
            holder.routeTv.setEnabled(isEnable);
            holder.busStopTv.setEnabled(isEnable);
            holder.lastTimeTv.setVisibility(isEnable ? View.VISIBLE : View.GONE);
            holder.lastTimeLabelTv.setVisibility(isEnable ? View.VISIBLE : View.GONE);
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
        TextView lastTimeLabelTv;
        TextView timeBefore;
        ImageButton activateSwitch;
        ConstraintLayout constraintLayout;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            timeBefore = itemView.findViewById(R.id.time_before);
            timeTv = itemView.findViewById(R.id.time_tv);
            routeTv = itemView.findViewById(R.id.route_tv);
            busStopTv = itemView.findViewById(R.id.bus_stop_tv);
            lastTimeTv = itemView.findViewById(R.id.last_time_tv);
            lastTimeLabelTv = itemView.findViewById(R.id.last_time_tv_label);
            activateSwitch = itemView.findViewById(R.id.activate_switch);
            constraintLayout = itemView.findViewById(R.id.parent);
        }
    }

    public interface OnClickListener {

        void onClick(RouteNotification item);

    }
}
