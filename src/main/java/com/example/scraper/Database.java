
package com.example.scraper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Handles database operations for storing Amazon products.
 * Using SQLite for simplicity (local file: scraper.db).
 */
public class Database {
    private static final String DB_URL = "jdbc:sqlite:scraper.db"; // ✅ Local SQLite DB file

    // ✅ Create "products" table once when the class is loaded
    static {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS products (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "url TEXT," +
                             "name TEXT," +
                             "price TEXT," +
                             "rating TEXT" +
                             ")"
             )) {
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ✅ Insert scraped Amazon product into DB
     */
    public static void insertProduct(String url, String name, String price, String rating) {
        String sql = "INSERT INTO products(url, name, price, rating) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, url);
            stmt.setString(2, name);
            stmt.setString(3, price);
            stmt.setString(4, rating);
            stmt.executeUpdate();
            System.out.println("💾 Saved product: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
