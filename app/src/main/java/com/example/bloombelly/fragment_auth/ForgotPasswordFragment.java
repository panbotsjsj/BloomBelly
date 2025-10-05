package com.example.bloombelly.fragment_auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bloombelly.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText etForgotEmail;
    private Button btnResetPassword;
    private TextView tvBackToLogin;

    public ForgotPasswordFragment() {
        // Konstruktor kosong wajib
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mAuth = FirebaseAuth.getInstance();
        etForgotEmail = view.findViewById(R.id.etForgotEmail);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        tvBackToLogin = view.findViewById(R.id.tvBackToLogin);

        // Tombol Kirim Tautan Reset
        btnResetPassword.setOnClickListener(v -> {
            String email = etForgotEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(getContext(), "Masukkan email terlebih dahulu.", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),
                                    "Tautan reset kata sandi telah dikirim ke email kamu.",
                                    Toast.LENGTH_LONG).show();

                            // Opsional: kembali otomatis ke login setelah sukses
                            getParentFragmentManager().popBackStack();
                        } else {
                            Toast.makeText(getContext(),
                                    "Gagal mengirim tautan: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Tombol kembali ke Login
        tvBackToLogin.setOnClickListener(v -> {
            FragmentManager fm = getParentFragmentManager();
            fm.popBackStack(); // kembali ke fragment login sebelumnya
        });

        return view;
    }
}
