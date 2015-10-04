package com.example.jola.zadanie;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Jola on 10/3/2015.
 */
public class DbItem {

    public static final String FULL_URL = "longUrl";
    public static final String SHORT_URL = "shortUrl";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = FULL_URL)
    private String longUrl;
    @DatabaseField(columnName = SHORT_URL)
    private String shortUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public DbItem() {
    }

    public DbItem(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "DbItem{" +
                "longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
