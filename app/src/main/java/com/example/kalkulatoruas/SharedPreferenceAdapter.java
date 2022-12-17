package com.example.kalkulatoruas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SharedPreferenceAdapter extends RecyclerView.Adapter<SharedPreferenceAdapter.RiwayatViewHolder> {
    private final ArrayList<ItemList> listriwayat;

    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewNilai1;
        public TextView TextViewOperasi;
        public TextView TextViewNilai2;
        public TextView TextViewHasil;

        @SuppressLint("CutPasteId")
        public RiwayatViewHolder(View itemView) {
            super(itemView);
            TextViewNilai1 = itemView.findViewById(R.id.angka1);
            TextViewOperasi = itemView.findViewById(R.id.operasi);
            TextViewNilai2 = itemView.findViewById(R.id.angka2);
            TextViewHasil = itemView.findViewById(R.id.hasil);
        }
    }

    public SharedPreferenceAdapter(ArrayList<ItemList> rwytList) {
        listriwayat = rwytList;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        RiwayatViewHolder holder= new RiwayatViewHolder(inflater.inflate(R.layout.act_item_list, parent, false));

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RiwayatViewHolder holder, int position) {
        ItemList currentItem = listriwayat.get(position);

        holder.TextViewNilai1.setText(currentItem.getAngka1());
        holder.TextViewNilai2.setText(currentItem.getAngka2());
        holder.TextViewOperasi.setText(currentItem.getOperasi());
        holder.TextViewHasil.setText(currentItem.getHasil());
    }

    @Override
    public int getItemCount() {
        return listriwayat.size();
    }
}