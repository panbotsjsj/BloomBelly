package com.example.bloombelly.fragment_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.bloombelly.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class UbahPassword extends Fragment {

    private MaterialButton btnSimpan;
    private TextInputEditText etPasswordBaru, etKonfirmasiPassword;

    public UbahPassword() {
        // Required empty public constructor
    }

    public static UbahPassword newInstance(String param1, String param2) {
        UbahPassword fragment = new UbahPassword();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ubah_password, container, false);

        // Inisialisasi komponen
        initViews(view);

        // Setup click listeners
        setupClickListeners();

        return view;
    }

    private void initViews(View view) {
        btnSimpan = view.findViewById(R.id.btnSimpan);
        etPasswordBaru = view.findViewById(R.id.etPasswordBaru);
        etKonfirmasiPassword = view.findViewById(R.id.etKonfirmasiPassword);
    }

    private void setupClickListeners() {
        // Button Simpan
        btnSimpan.setOnClickListener(v -> {
            ubahPassword();
        });
    }

    private void ubahPassword() {
        String passwordBaru = etPasswordBaru.getText().toString().trim();
        String konfirmasiPassword = etKonfirmasiPassword.getText().toString().trim();

        // Validasi input
        if (!validasiInput(passwordBaru, konfirmasiPassword)) {
            return;
        }

        // Jika validasi sukses, tampilkan popup konfirmasi
        tampilkanPopupBerhasil();
    }

    private boolean validasiInput(String passwordBaru, String konfirmasiPassword) {
        // Validasi kosong
        if (passwordBaru.isEmpty()) {
            etPasswordBaru.setError("Password baru tidak boleh kosong");
            etPasswordBaru.requestFocus();
            return false;
        } else {
            etPasswordBaru.setError(null);
        }

        if (konfirmasiPassword.isEmpty()) {
            etKonfirmasiPassword.setError("Konfirmasi password tidak boleh kosong");
            etKonfirmasiPassword.requestFocus();
            return false;
        } else {
            etKonfirmasiPassword.setError(null);
        }

        // Validasi panjang password (minimal 6 karakter)
        if (passwordBaru.length() < 6) {
            etPasswordBaru.setError("Password minimal 6 karakter");
            etPasswordBaru.requestFocus();
            return false;
        } else {
            etPasswordBaru.setError(null);
        }

        // Validasi password sama
        if (!passwordBaru.equals(konfirmasiPassword)) {
            etKonfirmasiPassword.setError("Password tidak sama");
            etKonfirmasiPassword.requestFocus();
            return false;
        } else {
            etKonfirmasiPassword.setError(null);
        }

        return true;
    }

    private void tampilkanPopupBerhasil() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Berhasil")
                .setMessage("Password berhasil diubah!")
                .setPositiveButton("OK", (dialog, which) -> {
                    // ✅ LANGSUNG KEMBALI KE PROFILE FRAGMENT
                    kembaliKeProfile();
                })
                .setCancelable(false)
                .show();
    }

    private void resetForm() {
        // Kosongkan input fields
        etPasswordBaru.setText("");
        etKonfirmasiPassword.setText("");

        // Hapus error messages
        etPasswordBaru.setError(null);
        etKonfirmasiPassword.setError(null);

        // Fokus ke input pertama
        etPasswordBaru.requestFocus();
    }

    private void kembaliKeProfile() {
        // Kembali ke fragment sebelumnya (ProfileFragment)
        if (getParentFragmentManager().getBackStackEntryCount() > 0) {
            getParentFragmentManager().popBackStack();
        }
    }
}