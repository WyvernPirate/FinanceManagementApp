package com.example.finance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TextView balanceTextView;
    private TextView budgetTextView;
    private FloatingActionButton fabAddTransaction;

    // Shared preferences keys
    private static final String PREFS_NAME = "FinancePrefs";
    private static final String PREF_CURRENCY = "currency";
    private static final String PREF_BUDGET_LIMIT = "budgetLimit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Log lifecycle event
        Log.d("LifecycleEvents", "DashboardActivity: onCreate called");

        // Set up toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        balanceTextView = findViewById(R.id.balance_text);
        budgetTextView = findViewById(R.id.budget_text);
        fabAddTransaction = findViewById(R.id.fab_add_transaction);

        // Set up tabs
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new AccountsFragment(), "Accounts");
        pagerAdapter.addFragment(new BudgetsFragment(), "Budgets");
        pagerAdapter.addFragment(new TransactionsFragment(), "Transactions");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // Set up FAB
        fabAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        // Load user preferences
        loadUserPreferences();

        // Update dashboard with latest data
        updateDashboardData();
    }

    private void loadUserPreferences() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String currency = prefs.getString(PREF_CURRENCY, "$"); // Default currency is $
        double budgetLimit = prefs.getFloat(PREF_BUDGET_LIMIT, 1000.0f); // Default budget is 1000

        // Display budget with warning if needed
        updateBudgetDisplay(budgetLimit, currency);
    }

    private void updateBudgetDisplay(double budgetLimit, String currency) {
        // Get total spent (this would come from your transaction repository)
        double totalSpent = 750.00; // Placeholder, replace with actual calculation

        // Format and display budget text
        String budgetText = String.format("Budget: %s%.2f / %s%.2f",
                currency, totalSpent, currency, budgetLimit);
        budgetTextView.setText(budgetText);

        // Add warning if over 80% of budget
        if (totalSpent > budgetLimit * 0.8) {
            budgetTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            budgetTextView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    private void updateDashboardData() {
        // Update balance (this would come from your accounts repository)
        double totalBalance = 26000.00; // Placeholder, replace with actual calculation

        // Get currency from preferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String currency = prefs.getString(PREF_CURRENCY, "$");

        // Format and display balance
        String balanceText = String.format("Total Balance: %s%.2f", currency, totalBalance);
        balanceTextView.setText(balanceText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_transaction) {
            Intent intent = new Intent(this, AddTransactionActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_reports) {
            Intent intent = new Intent(this, ReportsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifecycleEvents", "DashboardActivity: onResume called");

        // Refresh data when returning to this activity
        updateDashboardData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifecycleEvents", "DashboardActivity: onPause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifecycleEvents", "DashboardActivity: onDestroy called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("LifecycleEvents", "DashboardActivity: onSaveInstanceState called");
    }
}