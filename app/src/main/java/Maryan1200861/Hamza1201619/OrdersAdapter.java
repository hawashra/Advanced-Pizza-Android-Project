package Maryan1200861.Hamza1201619;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    private final ArrayList<Order> orders;

    public OrdersAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.orderIdTextView.setText(String.valueOf(order.getOrderId()));
        holder.orderDateTextView.setText(order.getOrderDate());
        holder.totalPriceTextView.setText(String.valueOf(order.getTotalPrice()));
        holder.userEmailTextView.setText(order.getUserEmail());

        // Create and set an adapter for the pizzas RecyclerView
        PizzasAdapter pizzasAdapter = new PizzasAdapter(new ArrayList<>(order.getPizzas().entrySet()));
        holder.pizzasRecyclerView.setLayoutManager(new LinearLayoutManager(holder.pizzasRecyclerView.getContext()));
        holder.pizzasRecyclerView.setAdapter(pizzasAdapter);
    }
    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderIdTextView;
        public TextView orderDateTextView;
        public TextView totalPriceTextView;
        public RecyclerView pizzasRecyclerView;

        public TextView userEmailTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            orderDateTextView = itemView.findViewById(R.id.orderDateTextView);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);
            pizzasRecyclerView = itemView.findViewById(R.id.pizzasRecyclerView);
            userEmailTextView = itemView.findViewById(R.id.textViewUserEmail);
        }
    }
}