package com.example.pantaucuan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvPengeluaran, tvPemasukan;
    private RecyclerView rvTransactions;
    private FloatingActionButton fabAdd;
    private TransactionAdapter adapter;
    private NumberFormat currencyFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        tvPengeluaran = findViewById(R.id.tv_pengeluaran);
        tvPemasukan = findViewById(R.id.tv_pemasukan);
        rvTransactions = findViewById(R.id.rv_transactions);
        fabAdd = findViewById(R.id.fab_add);

        // Initialize currency formatter
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Setup RecyclerView
        setupRecyclerView();

        // Setup FAB
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        adapter = new TransactionAdapter(this, new ArrayList<>());
        rvTransactions.setLayoutManager(new LinearLayoutManager(this));
        rvTransactions.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTransactions();
    }

    private void loadTransactions() {
        // TODO: Load transactions from database
        // For now, using dummy data
        List<Transaction> transactions = getDummyTransactions();
        adapter.updateData(transactions);
        updateSummary(transactions);
    }

    private void updateSummary(List<Transaction> transactions) {
        double totalPemasukan = 0;
        double totalPengeluaran = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getTipe().equals("pemasukan")) {
                totalPemasukan += transaction.getJumlah();
            } else {
                totalPengeluaran += transaction.getJumlah();
            }
        }

        tvPemasukan.setText(currencyFormat.format(totalPemasukan));
        tvPengeluaran.setText(currencyFormat.format(totalPengeluaran));
    }

    // Dummy data for testing
    private List<Transaction> getDummyTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Gaji", "01-11-2024", 5000000, "pemasukan"));
        transactions.add(new Transaction("Belanja", "02-11-2024", 100000, "pengeluaran"));
        return transactions;
    }
}