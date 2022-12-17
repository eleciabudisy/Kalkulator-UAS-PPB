package com.example.kalkulatoruas;

public class ItemList {
    private final String Angka1;
    private final String Operasi;
    private final String Angka2;
    private final String Hasil;

    public ItemList(String angka1, String operasi, String angka2, String hasil) {
        Angka1 = angka1;
        Angka2 = angka2;
        Operasi = operasi;
        Hasil = hasil;
    }

    public String getAngka1() {
        return Angka1;
    }

    public String getOperasi() {
        return Operasi;
    }

    public String getAngka2() {
        return Angka2;
    }

    public String getHasil() {
        return Hasil;
    }
}
