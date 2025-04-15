package com.example.finance;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText descriptionEditText;
    private EditText amountEditText;
    private TextView dateTextView;
    private Spinner categorySpinner;
    private Spinner accountSpinner;
    private Spinner typeSpinner;
    private Button saveButton;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        // Log lifecycle event
        Log.d("LifecycleEvents", "AddTransactionActivity: onCreate called");

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Transaction");

        // Initialize views
        descriptionEditText = findViewById(R.id.edit_description);
        amountEditText = findViewById(R.id.edit_amount);
        dateTextView = findViewById(R.id.text_date);
        categorySpinner = findViewById(R.id.spinner_category);
        accountSpinner = findViewById(R.id.spinner_account);
        typeSpinner = findViewById(R.id.spinner_type);
        saveButton = findViewById(R.id.button_save);

        // Set up date picker
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        updateDateLabel();

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Restore state if available
        if (savedInstanceState != null) {
            descriptionEditText.setText(savedInstanceState.getString("description", ""));
            amountEditText.setText(savedInstanceState.getString("amount", ""));
            calendar.setTimeInMillis(savedInstanceState.getLong("date", calendar.getTimeInMillis()));
            updateDateLabel();
        }

        // Set up spinners
        setupSpinners();

        // Set up save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    saveTransaction();
                    finish();
                }
            }
        });
    }

    private void setupSpinners() {
        // Categories
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Accounts
        ArrayAdapter<CharSequence> accountAdapter = ArrayAdapter.createFromResource(
                this, R.array.accounts, android.R.layout.simple_spinner_item);
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(accountAdapter);

        // Transaction types
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.transaction_types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateDateLabel();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDateLabel() {
        dateTextView.setText(dateFormat.format(calendar.getTime()));
    }

    private boolean validateInputs() {
        if (descriptionEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (amountEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double amount = Double.parseDouble(amountEditText.getText().toString());
            if (amount <= 0) {
                Toast.makeText(this, "Please enter a positive amount", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveTransaction() {
        // In a real app, you would save this to a database
        String description = descriptionEditText.getText().toString();
        double amount = Double.parseDouble(amountEditText.getText().toString());
        String category = categorySpinner.getSelectedItem().toString();
        String account = accountSpinner.getSelectedItem().toString();
        String type = typeSpinner.getSelectedItem().toString();

        // Adjust amount sign based on transaction type
        if (type.equals("Expense")) {
            amount = -amount;
        }

        // Create transaction object
        Transaction transaction = new Transaction(
                System.currentTimeMillis(), // Generate ID
                description,
                amount,
                calendar.getTime(),
                category,
                account,
                type.equals("Expense") ? Transaction.TransactionType.EXPENSE : Transaction.TransactionType.INCOME
        );

        // Show success message
        Toast.makeText(this, "Transaction saved", Toast.LENGTH_SHORT).show();

        // In a real app, you'd add this to your repository/database
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("description", descriptionEditText.getText().toString());
        outState.putString("amount", amountEditText.getText().toString());
        outState.putLong("date", calendar.getTimeInMillis());
        Log.d("LifecycleEvents", "AddTransactionActivity: onSaveInstanceState called");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LifecycleEvents", "AddTransactionActivity: onRestoreInstanceState called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifecycleEvents", "AddTransactionActivity: onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifecycleEvents", "AddTransactionActivity: onPause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifecycleEvents", "AddTransactionActivity: onDestroy called");
    }
}