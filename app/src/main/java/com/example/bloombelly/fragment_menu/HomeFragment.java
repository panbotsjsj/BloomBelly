package com.example.bloombelly.fragment_menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloombelly.R;
import com.example.bloombelly.Article; // Import Article model
import com.example.bloombelly.ArticleAdapter; // Import ArticleAdapter

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvArticles;
    // ... (parameter dan constructor lainnya tetap sama)

    public HomeFragment() {
        // Required empty public constructor
    }

    // ... (onCreate, newInstance, dll. tetap sama)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvArticles = view.findViewById(R.id.rv_popular_articles);

        // Setup RecyclerView: Horizontal scroll
        rvArticles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // --- Contoh Data Dummy Artikel Populer ---
        List<Article> articleList = new ArrayList<>();
        // Asumsi Anda memiliki drawable placeholder/gambar artikel dengan nama "placeholder_article_1", "placeholder_article_2", dll.
        // Jika tidak, ganti dengan R.drawable.img_article_placeholder untuk semua.
        articleList.add(new Article("Mengenal Karakteristik Gen Alpha dan Tantangan Pola Asuh Orang Tuanya", R.drawable.article));
        articleList.add(new Article("10 Makanan Wajib Bumil di Trimester Ketiga", R.drawable.article));
        articleList.add(new Article("Tanda-Tanda Kontraksi Palsu vs Asli, Jangan Panik Dulu!", R.drawable.article));
        articleList.add(new Article("Cara Mudah Kelola Stres Menjelang Persalinan", R.drawable.article));

        // Set Adapter
        ArticleAdapter articleAdapter = new ArticleAdapter(articleList);
        rvArticles.setAdapter(articleAdapter);

        return view;
    }
}