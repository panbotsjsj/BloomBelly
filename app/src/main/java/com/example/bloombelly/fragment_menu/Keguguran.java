package com.example.bloombelly.fragment_menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bloombelly.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Keguguran extends Fragment {

    private Button btnYa, btnTidak;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Keguguran() {
        // Required empty public constructor
    }

    public static Keguguran newInstance(String param1, String param2) {
        Keguguran fragment = new Keguguran();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keguguran, container, false);

        // Initialize buttons
        btnYa = view.findViewById(R.id.btnYa);
        btnTidak = view.findViewById(R.id.btnTidak);

        setupButtonListeners();

        return view;
    }

    private void setupButtonListeners() {
        // Tombol Ya - tampilkan popup dan kembali ke Home
        btnYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDukaCitaDialog();
            }
        });

        // Tombol Tidak - tampilkan popup "Tetap semangat!" yang otomatis hilang
        btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTetapSemangatDialog();
            }
        });
    }

    private void showDukaCitaDialog() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Duka Cita")
                .setMessage("Kami turut berduka cita atas kejadian yang Bunda alami. Semoga Bunda diberikan ketabahan dan kekuatan dalam menghadapi cobaan ini.")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    // Kembali ke HomeFragment melalui bottom navigation
                    navigateToHomeFragment();
                })
                .setCancelable(false)
                .show();
    }

    private void showTetapSemangatDialog() {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Semangat")
                .setMessage("Tetap semangat!")
                .setCancelable(false);

        // Tampilkan dialog
        var dialog = dialogBuilder.show();

        // Otomatis tutup setelah 1 detik
        if (getView() != null) {
            getView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, 1000); // 1000 milidetik = 1 detik
        }
    }

    private void navigateToHomeFragment() {
        // Set bottom navigation ke Home dan load HomeFragment
        com.google.android.material.bottomnavigation.BottomNavigationView bottomNav =
                requireActivity().findViewById(R.id.bottom_navigation);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_home);
        }
    }
}