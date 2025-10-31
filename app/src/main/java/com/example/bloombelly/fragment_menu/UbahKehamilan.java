package com.example.bloombelly.fragment_menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bloombelly.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UbahKehamilan extends Fragment {

    private Button btnSimpan;
    private TextInputEditText etTanggalHTP;

    public UbahKehamilan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ubah_kehamilan, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButtonListeners();
    }

    private void initViews(View view) {
        btnSimpan = view.findViewById(R.id.btnSimpan);
        etTanggalHTP = view.findViewById(R.id.etTanggalHTP);
    }

    private void setupButtonListeners() {

        // Tombol Simpan - validasi dan kembali ke HomeFragment
        btnSimpan.setOnClickListener(v -> {
            simpanDataKehamilan();
        });
    }

    private void simpanDataKehamilan() {
        String tanggalHTP = etTanggalHTP.getText().toString().trim();

        // ✅ VALIDASI DENGAN setError (BUKAN TOAST)
        if (!validasiInput(tanggalHTP)) {
            return;
        }

        // ✅ JIKA BERHASIL, PAKAI MATERIAL DIALOG (BUKAN TOAST)
        tampilkanPopupBerhasil();
    }

    private boolean validasiInput(String tanggalHTP) {
        // Validasi input tidak kosong
        if (tanggalHTP.isEmpty()) {
            etTanggalHTP.setError("Tanggal HTP harus diisi");
            etTanggalHTP.requestFocus();
            return false;
        } else {
            etTanggalHTP.setError(null);
        }

        // Validasi format yyyy-mm-dd
        if (!isValidDateFormat(tanggalHTP)) {
            etTanggalHTP.setError("Format harus yyyy-mm-dd (contoh: 2024-01-15) dengan keterangan 2024 Januari 15");
            etTanggalHTP.requestFocus();
            return false;
        } else {
            etTanggalHTP.setError(null);
        }

        // Validasi urutan tanggal
        if (!isValidDateSequence(tanggalHTP)) {
            etTanggalHTP.setError("Format tanggal tidak valid");
            etTanggalHTP.requestFocus();
            return false;
        } else {
            etTanggalHTP.setError(null);
        }

        // Validasi tanggal tidak di masa depan
        if (isFutureDate(tanggalHTP)) {
            etTanggalHTP.setError("Tanggal tidak boleh di masa depan");
            etTanggalHTP.requestFocus();
            return false;
        } else {
            etTanggalHTP.setError(null);
        }

        return true;
    }

    // ✅ POPUP BERHASIL DENGAN MATERIAL DIALOG
    private void tampilkanPopupBerhasil() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Berhasil")
                .setMessage("Data kehamilan berhasil disimpan")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Setelah user klik OK, navigasi ke HomeFragment
                    navigateToHomeFragment();
                })
                .setCancelable(false)
                .show();
    }

    private boolean isValidDateFormat(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private boolean isValidDateSequence(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isFutureDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date inputDate = sdf.parse(date);
            Date currentDate = new Date();
            return inputDate != null && inputDate.after(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }

    private void navigateToHomeFragment() {
        // Set bottom navigation ke Home dan load HomeFragment
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
    }
}