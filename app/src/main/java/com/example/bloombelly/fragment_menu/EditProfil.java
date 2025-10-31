package com.example.bloombelly.fragment_menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bloombelly.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class EditProfil extends Fragment {

    private EditText etNama, etUsername, etEmail;
    private Button btnSimpan;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_profile";
    private String initialNama, initialUsername, initialEmail;

    public EditProfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profil, container, false);

        // Initialize views
        etNama = view.findViewById(R.id.etNama);
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        btnSimpan = view.findViewById(R.id.btnSimpan);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        setupButtonListeners();
        loadCurrentProfileData();
    }

    private void setupButtonListeners() {
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanDataProfil();
            }
        });
    }

    private void loadCurrentProfileData() {
        // Load data dari SharedPreferences
        initialNama = sharedPreferences.getString("nama", "Rania Hapsari");
        initialUsername = sharedPreferences.getString("username", "Rania");
        initialEmail = sharedPreferences.getString("email", "rania1231@gmail.com");

        etNama.setText(initialNama);
        etUsername.setText(initialUsername);
        etEmail.setText(initialEmail);
    }

    private void simpanDataProfil() {
        String nama = etNama.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (nama.isEmpty() || username.isEmpty() || email.isEmpty()) {
            showErrorDialog("Semua field harus diisi", false);
            setErrorForEmptyFields(nama, username, email);
            return;
        }

        // Validasi data tidak sama dengan data awal
        if (nama.equals(initialNama) && username.equals(initialUsername) && email.equals(initialEmail)) {
            showErrorDialog("Data harus berbeda dengan data awal", false);
            return;
        }

        // Validasi format email
        if (!isValidEmail(email)) {
            etEmail.setError("Format email tidak valid");
            return;
        }

        // Validasi username (minimal 3 karakter, maksimal 10 karakter)
        if (username.length() < 3) {
            etUsername.setError("Username minimal 3 karakter");
            return;
        }

        if (username.length() > 10) {
            etUsername.setError("Username maksimal 10 karakter");
            return;
        }

        saveProfileData(nama, username, email);
    }

    private void setErrorForEmptyFields(String nama, String username, String email) {
        if (nama.isEmpty()) {
            etNama.setError("Nama harus diisi");
        } else {
            etNama.setError(null);
        }

        if (username.isEmpty()) {
            etUsername.setError("Username harus diisi");
        } else {
            etUsername.setError(null);
        }

        if (email.isEmpty()) {
            etEmail.setError("Email harus diisi");
        } else {
            etEmail.setError(null);
        }
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return email.matches(emailPattern);
    }

    private void saveProfileData(String nama, String username, String email) {
        try {
            // SIMPAN DATA KE SHAREDPREFERENCES
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nama", nama);
            editor.putString("username", username);
            editor.putString("email", email);
            editor.apply();

            // TAMPILKAN DIALOG SUKSES dan kembali ke ProfileFragment
            showSuccessDialog();

        } catch (Exception e) {
            showErrorDialog("Gagal menyimpan data", false);
        }
    }

    private void showSuccessDialog() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Berhasil")
                .setMessage("Data profil berhasil diedit")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    // KEMBALI KE ProfileFragment
                    requireActivity().getSupportFragmentManager().popBackStack();
                })
                .setCancelable(false)
                .show();
    }

    private void showErrorDialog(String message, boolean backToProfile) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    if (backToProfile) {
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                })
                .setCancelable(false)
                .show();
    }
}