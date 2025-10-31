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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloombelly.AuthActivity;
import com.example.bloombelly.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText etForgotEmail;
    private Button btnResetPassword;
    private TextView tvBackToLogin;

    public ForgotPasswordFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        mAuth = FirebaseAuth.getInstance();
        etForgotEmail = view.findViewById(R.id.etForgotEmail);
        btnResetPassword = view.findViewById(R.id.btnResetPassword);
        tvBackToLogin = view.findViewById(R.id.tvBackToLogin);

        btnResetPassword.setOnClickListener(v -> {
            String email = etForgotEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(getContext(), "Masukkan email terlebih dahulu.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getContext(), "Format email tidak valid.", Toast.LENGTH_SHORT).show();
                return;
            }

            btnResetPassword.setEnabled(false);

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        btnResetPassword.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(),
                                    "Tautan reset kata sandi telah dikirim ke email kamu.",
                                    Toast.LENGTH_LONG).show();

                            if (getActivity() instanceof AuthActivity) {
                                ((AuthActivity) getActivity()).showLoginView();
                            }
                        } else {
                            Toast.makeText(getContext(),
                                    "Gagal mengirim tautan: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        tvBackToLogin.setOnClickListener(v -> {
            if (getActivity() instanceof AuthActivity) {
                ((AuthActivity) getActivity()).showLoginView();
            }
        });

        return view;
    }
}
