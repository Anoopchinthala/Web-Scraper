
package com.example.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

/**
 * Main entry point.
 * Uses Selenium to scrape Amazon product pages and save results into SQLite DB.
 */
public class Main {
    public static void main(String[] args) {
        // Start Selenium (make sure ChromeDriver is installed)
        WebDriver driver = new ChromeDriver();

        try {
            // Product URLs to scrape
            List<String> urls = Arrays.asList(
                    "https://www.amazon.in/dp/B0F3XGBH79/",
                    "https://www.amazon.in/dp/B0DGHYPFYB/"
            );

            // Scraper instance
            AmazonScraper scraper = new AmazonScraper();

            // Loop through all product URLs
            for (String url : urls) {
                scraper.scrapeProduct(driver, url);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();   // closes browser
            System.exit(0);  // kills leftover threads
        }
    }
}

