// Nama file: ArticleAdapter.java
package com.example.bloombelly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private final List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Mengaitkan layout item_article_card.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article_card, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.imgThumbnail.setImageResource(article.getImageResId());

        // Menambahkan listener klik (opsional)
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Membuka artikel: " + article.getTitle(), Toast.LENGTH_SHORT).show();
            // Implementasikan Intent untuk membuka Activity/Fragment detail artikel di sini
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    // Class ViewHolder
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView tvTitle;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.img_article_thumbnail);
            tvTitle = itemView.findViewById(R.id.tv_article_title);
        }
    }
}