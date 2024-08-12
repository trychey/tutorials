package com.baeldung.stream.filter;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Customer {
    private String name;
    private int points;
    private String profilePhotoUrl;

    public Customer(String name, int points) {
        this(name, points, "");
    }

    public Customer(String name, int points, String profilePhotoUrl) {
        this.name = name;
        this.points = points;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public boolean hasOver(int points) {
        return this.points > points;
    }

    public boolean hasOverHundredPoints() {
        return this.points > 100;
    }

    public boolean hasValidProfilePhoto() throws Exception {
        URL url = new URI(this.profilePhotoUrl).toURL();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean hasValidProfilePhotoWithoutCheckedException() {
        try {
            URL url = new URI(this.profilePhotoUrl).toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
