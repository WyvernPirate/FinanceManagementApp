package com.example.finance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionsFragment extends Fragment {

    private RecyclerView transactionsRecyclerView;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> transactionList;

    public TransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        transactionsRecyclerView = view.findViewById(R.id.transactions_recycler_view);
        transactionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data (replace with your actual data loading)
        transactionList = new ArrayList<>();
        transactionList.add(new Transaction(1, "Groceries", -120.50, new Date(),
                "Food", "Cash on Hand", Transaction.TransactionType.EXPENSE));
        transactionList.add(new Transaction(2, "Salary", 5000.00, new Date(),
                "Income", "FNB", Transaction.TransactionType.INCOME));
        transactionList.add(new Transaction(3, "Restaurant", -85.75, new Date(),
                "Food", "Cash on Hand", Transaction.TransactionType.EXPENSE));

        transactionAdapter = new TransactionAdapter(transactionList);
        transactionsRecyclerView.setAdapter(transactionAdapter);

        return view;
    }
}