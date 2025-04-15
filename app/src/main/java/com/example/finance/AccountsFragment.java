package com.example.finance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class AccountsFragment extends Fragment {

    private RecyclerView accountsRecyclerView;
    private AccountAdapter accountAdapter;
    private List<Account> accountList;
    private FloatingActionButton fabAddAccount;

    public AccountsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        accountsRecyclerView = view.findViewById(R.id.accounts_recycler_view);
        accountsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data (replace with your actual data loading)
        accountList = new ArrayList<>();
        accountList.add(new Account("Cash on Hand", 1000.00));
        accountList.add(new Account("FNB", 25000.00));
        accountList.add(new Account("Add account", 0.00)); // Representing the "Add account" item

        accountAdapter = new AccountAdapter(accountList);
        accountsRecyclerView.setAdapter(accountAdapter);

        fabAddAccount = view.findViewById(R.id.fab_add_account);
        fabAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click to add a new account
                // You'll likely want to open a new activity or dialog here
            }
        });

        return view;
    }
}
