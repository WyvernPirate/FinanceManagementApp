package com.example.finance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false); // You'll likely need a custom layout
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.nameTextView.setText(account.getName());
        holder.balanceTextView.setText("$" + String.format("%.2f", account.getBalance()));
        // You'll need to handle the "Add account" item differently in your layout and logic
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView balanceTextView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
            balanceTextView = itemView.findViewById(android.R.id.text2);
        }
    }
}
