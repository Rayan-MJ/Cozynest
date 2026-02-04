package com.cozynest.cozynest.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.cozynest.cozynest.model.Book;

public class OpenLibrary {
    private OpenLibrary() {
        /* This utility class should not be instantiated */
    }

    public static List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(
                    "https://openlibrary.org/search.json?q=%s",
                    URLEncoder.encode(query, StandardCharsets.UTF_8));
            String response = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(response);
            JSONArray docs = json.getJSONArray("docs");

            for (int i = 0; i < Math.min(10, docs.length()); i++) { // get top 10 results
                JSONObject item = docs.getJSONObject(i);

                int id = i + 1;
                String title = item.optString("title", "No title");
                String author = item.has("author_name") ? item.getJSONArray("author_name").getString(0)
                        : "Unknown author";
                String isbn = item.has("isbn") ? item.getJSONArray("isbn").getString(0)
                        : getIsbn(item.getJSONArray("ia"));

                String coverUrl = isbn != null
                        ? "https://covers.openlibrary.org/b/isbn/" + isbn + "-M.jpg"
                        : "https://via.placeholder.com/120x180?text=No+Cover";
                String summary = "No summary available";
                double ratingValue = Math.round(Math.random() * 5 * 100.0) / 100.0;
                int count = ThreadLocalRandom.current().nextInt(0, 1000);

                Book book = new Book(id, title, author, coverUrl, isbn, summary,ratingValue,count,"fantasy" );
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    private static String getIsbn(JSONArray j1) {
        String isbn = "";
        for (int i = 0; i < j1.length(); i++) {
            String value = j1.getString(i);
            if (value.startsWith("isbn_")) {
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{13}").matcher(value);
                if (matcher.find()) {
                    isbn = matcher.group(); // first 13-digit ISBN
                    return isbn; // return immediately
                }
            }
        }
        // fallback if no isbn found
        return null;
    }
}
