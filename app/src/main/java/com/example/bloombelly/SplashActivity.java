package com.example.bloombelly;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth; // Import untuk Firebase Authentication

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DURATION = 3000; // Durasi splash screen (3 detik)

    // Deklarasi instance Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Inisialisasi Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Gunakan Handler dengan Looper untuk menjalankan penentuan aktivitas setelah SPLASH_DURATION
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                determineNextActivity();
            }
        }, SPLASH_DURATION);
    }

    private void determineNextActivity() {
        Intent intent;

        // --- LOGIKA PENENTUAN AKTIVITAS MENGGUNAKAN FIREBASE AUTH ---

        // Cek apakah ada pengguna yang saat ini login
        if (mAuth.getCurrentUser() != null) {
            // Jika pengguna sudah login, arahkan ke MainActivity (Home Screen)
            // Ganti MainActivity.class sesuai nama aktivitas utama Anda
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            // Jika belum ada pengguna yang login, arahkan ke AuthActivity (Login/Register)
            // Ganti AuthActivity.class sesuai nama aktivitas autentikasi Anda
            intent = new Intent(SplashActivity.this, AuthActivity.class);
        }


        // --- AKHIR LOGIKA PENENTUAN AKTIVITAS ---

        startActivity(intent);
        finish(); // Tutup SplashActivity agar pengguna tidak bisa kembali ke sini
    }
}
