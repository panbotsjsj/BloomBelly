package com.example.bloombelly;

public class Article {
    private String title;
    private int imageResId; // Resource ID untuk gambar (misal: R.drawable.img_article_1)

    public Article(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}