package com.example.vkfind.utils;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class NetworkUtils {
    private static final String VK_API_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_TOKEN = "access_token";
    private static final String PARAM_VERSION = "v";

    public static URL generateURL(String userId) {
        Uri builtUri = Uri.parse(VK_API_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_TOKEN, "f0acc815f0acc815f0acc81548f0df5b60ff0acf0acc815afec885e7a8751c77d821cda")
                .appendQueryParameter(PARAM_VERSION, "5.122")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.readLine();
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }

    }
}
