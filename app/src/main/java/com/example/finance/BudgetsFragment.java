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

public class BudgetsFragment extends Fragment {

    private RecyclerView budgetsRecyclerView;
    private BudgetAdapter budgetAdapter;
    private List<Budget> budgetList;
    private FloatingActionButton fabAddBudget;

    public BudgetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budgets, container, false);

        budgetsRecyclerView = view.findViewById(R.id.budgets_recycler_view);
        budgetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data (replace with your actual data loading)
        budgetList = new ArrayList<>();
        budgetList.add(new Budget("Monthly Budget"));
        budgetList.add(new Budget("Add budget")); // Representing the "Add budget" item

        budgetAdapter = new BudgetAdapter(budgetList);
        budgetsRecyclerView.setAdapter(budgetAdapter);

        fabAddBudget = view.findViewById(R.id.fab_add_budget);
        fabAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click to add a new budget
                // You'll likely want to open a new activity or dialog here
            }
        });

        return view;
    }
}
