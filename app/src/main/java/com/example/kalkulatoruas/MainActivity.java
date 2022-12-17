package com.example.kalkulatoruas;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ItemList> exampleList;
    private RecyclerView recyclerView;
    private SharedPreferenceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    RadioGroup operasiGroup;
    RadioButton tambahRadio, kurangRadio, kaliRadio, bagiRadio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setHitungButton();

       /* Button buttonHitung = findViewById(R.id.Hitung);
        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHitungButton();
            }
        }); */

        Button buttonHapus = findViewById(R.id.Hapus);
        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        operasiGroup = findViewById(R.id.operasiGroup);
        tambahRadio = findViewById(R.id.tambahRadio);
        kurangRadio = findViewById(R.id.kurangRadio);
        kaliRadio = findViewById(R.id.kaliRadio);
        bagiRadio = findViewById(R.id.bagiRadio);

        operasiGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tambahRadio.isChecked()) {
                    Toast.makeText(MainActivity.this, "Tambah", Toast.LENGTH_SHORT).show();
                } else if (kurangRadio.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kurang", Toast.LENGTH_SHORT).show();
                } else if (kaliRadio.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kali", Toast.LENGTH_SHORT).show();
                } else if (bagiRadio.isChecked()) {
                    Toast.makeText(MainActivity.this, "Bagi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        exampleList.clear();
        adapter.notifyDataSetChanged();
    }

   // private void saveData() { }

    private void setHitungButton() {

        Button buttonHitung = findViewById(R.id.Hitung);
        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText angka1 = findViewById(R.id.editText1);
                EditText angka2 = findViewById(R.id.editText2);
                insertItem(angka1.getText().toString(), angka2.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(exampleList);
                editor.putString("task list", json);
                editor.apply();
            }
        });
    }

    private void  insertItem(String angka1, String angka2) {
        int hasil = 0;
        if (tambahRadio.isChecked()) {
            hasil = Integer.parseInt(angka1) + Integer.parseInt(angka2);
        } else if (kurangRadio.isChecked()) {
            hasil = Integer.parseInt(angka1) - Integer.parseInt(angka2);
        } else if (kaliRadio.isChecked()) {
            hasil = Integer.parseInt(angka1) * Integer.parseInt(angka2);
        } else if (bagiRadio.isChecked()) {
            hasil = Integer.parseInt(angka1) / Integer.parseInt(angka2);
        }

        String operasi = "";
        if (tambahRadio.isChecked()) {
            operasi = "+";
        } else if (kurangRadio.isChecked()) {
            operasi = "-";
        } else if (kaliRadio.isChecked()) {
            operasi = "x";
        } else if (bagiRadio.isChecked()) {
            operasi = ":";
        }

        exampleList.add(new ItemList(angka1, operasi, angka2, String.valueOf(hasil)));
        adapter.notifyItemInserted(exampleList.size());
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SharedPreferenceAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type  type = new TypeToken<ArrayList<ItemList>>() {}.getType();
        exampleList = gson.fromJson(json, type);

        if (exampleList == null) {
            exampleList = new ArrayList<>();
        }
    }
}