package com.example.angai.diplom.transport.presentation.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.angai.diplom.R;
import com.example.angai.diplom.transport.business.Route;

public class DiscreteScrollViewAdapter extends RecyclerView.Adapter<DiscreteScrollViewAdapter.RoutesViewHolder> {

    private Route[] routes;
    private LayoutInflater layoutInflater;
    private RecyclerView parentRecycler;

    public DiscreteScrollViewAdapter(Context context, Route[] routes) {
        this.routes = routes;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.parentRecycler = recyclerView;
    }

    @NonNull
    @Override
    public RoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoutesViewHolder(
                layoutInflater.inflate(R.layout.route_adapter_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RoutesViewHolder holder, int position) {
        holder.routeNumber.setText(routes[position].getName());
    }

    @Override
    public int getItemCount() {
        return routes.length;
    }

    public class RoutesViewHolder extends RecyclerView.ViewHolder {

        TextView routeNumber;

        public RoutesViewHolder(View itemView) {
            super(itemView);
            routeNumber = itemView.findViewById(R.id.bus_stop_number);
            itemView.setOnClickListener(
                    v -> parentRecycler.smoothScrollToPosition(getAdapterPosition())
            );
        }

    }
}
