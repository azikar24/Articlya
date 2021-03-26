package com.azikar24.articlya.Models;

import java.io.Serializable;

public class Article implements Serializable {
    private String title, by, date, category, abstractt, imageUrl, url;

    public Article(String title, String by, String date, String category, String abstractt, String imageUrl, String url) {
        this.title = title;
        this.by = by;
        this.date = date;
        this.category = category;
        this.abstractt = abstractt;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public Article() {
        this.title = "";
        this.by = "";
        this.date = "";
        this.category = "";
        this.abstractt = "";
        this.imageUrl = "";
        this.url = "";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAbstractt() {
        if(abstractt.length() == 0){
            abstractt = "No abstract!";
        }
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public String getImageUrl() {
        if(imageUrl == null || imageUrl.equals("")){
            imageUrl = "https://scontent.fkwi11-1.fna.fbcdn.net/v/t1.6435-9/90135300_10152236945329999_4062638693754601472_n.jpg?_nc_cat=103&ccb=1-3&_nc_sid=6e5ad9&_nc_ohc=ulrq8hk4EBkAX9_j6nz&_nc_ht=scontent.fkwi11-1.fna&oh=2a4ca9ff763c867823b8a44f00426255&oe=60800CD9";
        }
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", by='" + by + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", abstractt='" + abstractt + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

