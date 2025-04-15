package com.example.finance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        holder.descriptionTextView.setText(transaction.getDescription());
        holder.accountTextView.setText(transaction.getAccountName());
        holder.categoryTextView.setText(transaction.getCategory());
        holder.dateTextView.setText(dateFormat.format(transaction.getDate()));

        // Format amount with sign based on transaction type
        String amountPrefix = transaction.getType() == Transaction.TransactionType.EXPENSE ? "-$" : "+$";
        String amountText = amountPrefix + String.format("%.2f", Math.abs(transaction.getAmount()));
        holder.amountTextView.setText(amountText);

        // Set color based on transaction type
        int color = transaction.getType() == Transaction.TransactionType.EXPENSE
                ? holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark)
                : holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark);
        holder.amountTextView.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionTextView;
        public TextView amountTextView;
        public TextView categoryTextView;
        public TextView accountTextView;
        public TextView dateTextView;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.transaction_description);
            amountTextView = itemView.findViewById(R.id.transaction_amount);
            categoryTextView = itemView.findViewById(R.id.transaction_category);
            accountTextView = itemView.findViewById(R.id.transaction_account);
            dateTextView = itemView.findViewById(R.id.transaction_date);
        }
    }
}