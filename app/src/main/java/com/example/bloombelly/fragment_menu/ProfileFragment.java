package com.example.bloombelly.fragment_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloombelly.AuthActivity;
import com.example.bloombelly.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private LinearLayout btnUbahPassword, btnUbahKehamilan, btnKeguguran;
    private ImageView btnEditProfile;
    private TextView tvName; // HANYA ADA tvName
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inisialisasi komponen
        btnLogout = view.findViewById(R.id.btnLogout);
        btnUbahPassword = view.findViewById(R.id.btnUbahPassword);
        btnUbahKehamilan = view.findViewById(R.id.btnUbahKehamilan);
        btnKeguguran = view.findViewById(R.id.btnKeguguran);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        tvName = view.findViewById(R.id.tvName); // HANYA tvName
        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = requireContext().getSharedPreferences("user_profile", Context.MODE_PRIVATE);

        loadProfileData(); // Load data nama

        // Logout
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(getActivity(), AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Edit Profile
        btnEditProfile.setOnClickListener(v -> {
            EditProfil editProfilFragment = new EditProfil();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, editProfilFragment)
                    .addToBackStack("profile")
                    .commit();
        });

        // Ubah Password
        btnUbahPassword.setOnClickListener(v -> {
            UbahPassword ubahPasswordFragment = new UbahPassword();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, ubahPasswordFragment)
                    .addToBackStack("profile")
                    .commit();
        });

        // Ubah Kehamilan
        btnUbahKehamilan.setOnClickListener(v -> {
            UbahKehamilan ubahKehamilanFragment = new UbahKehamilan();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, ubahKehamilanFragment)
                    .addToBackStack("profile")
                    .commit();
        });

        // Keguguran
        btnKeguguran.setOnClickListener(v -> {
            Keguguran keguguranFragment = new Keguguran();
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, keguguranFragment)
                    .addToBackStack("profile")
                    .commit();
        });

        return view;
    }

    private void loadProfileData() {
        // Load data nama dari SharedPreferences
        String nama = sharedPreferences.getString("nama", "Rania Hapsari");
        tvName.setText(nama);

        // Email tidak ditampilkan di ProfileFragment, jadi tidak perlu load
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProfileData(); // Load data setiap kali fragment aktif
    }
}