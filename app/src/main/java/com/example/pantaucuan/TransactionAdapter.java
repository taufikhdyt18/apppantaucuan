package com.example.pantaucuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactions;
    private Context context;
    private NumberFormat currencyFormat;

    public TransactionAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        holder.tvKeterangan.setText(transaction.getKeterangan());
        holder.tvTanggal.setText(transaction.getTanggal());

        String amount = currencyFormat.format(transaction.getJumlah());
        if (transaction.getTipe().equals("pengeluaran")) {
            amount = "- " + amount;
            holder.tvJumlah.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        } else {
            amount = "+ " + amount;
            holder.tvJumlah.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }
        holder.tvJumlah.setText(amount);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void updateData(List<Transaction> newTransactions) {
        this.transactions = newTransactions;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKeterangan, tvTanggal, tvJumlah;

        ViewHolder(View itemView) {
            super(itemView);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvJumlah = itemView.findViewById(R.id.tv_jumlah);
        }
    }
}