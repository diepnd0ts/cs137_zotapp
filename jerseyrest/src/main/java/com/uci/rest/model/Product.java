package com.uci.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

public class Product {
    private int id;
    private String title;
    private String developer;
    private double price;
    private String genre;
    private int downloads;
    private String downloadSize;
    private String contentRating;
    private double appRating;
    private String icon;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(String downloadSize) {
        this.downloadSize = downloadSize;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public double getAppRating() {
        return appRating;
    }

    public void setAppRating(double appRating) {
        this.appRating = appRating;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

}
