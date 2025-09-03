
package com.example.scraper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonScraper {
    public void scrapeProduct(WebDriver driver, String url) {
        try {
            // Opens the product page
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Extract product title
            String title = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("productTitle"))).getText().trim();

            // Extract price 
            String price = "Price not available";
            try {
                WebElement priceElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.a-price span.a-offscreen"))
                );
                price = priceElement.getText().trim();
            } catch (Exception e) {
                // fallback: sometimes price is inside #corePriceDisplay
                try {
                    WebElement priceElement = driver.findElement(By.cssSelector("#corePriceDisplay_desktop_feature_div span.a-offscreen"));
                    price = priceElement.getText().trim();
                } catch (Exception ignored) {}
            }

            // Extract rating 
            String rating = "Rating not available";
            try {
                WebElement ratingElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("#acrPopover span.a-icon-alt"))
                );
                rating = ratingElement.getText().trim(); // e.g. "4.6 out of 5 stars"
            } catch (Exception e) {
                try {
                    WebElement ratingElement = driver.findElement(By.cssSelector("span[data-asin] i span.a-icon-alt"));
                    rating = ratingElement.getText().trim();
                } catch (Exception ignored) {}
            }

            // Print result to console
            System.out.println("\n--- Product ---");
            System.out.println("URL: " + url);
            System.out.println("Title: " + title);
            System.out.println("Price: " + price);
            System.out.println("Rating: " + rating);

            // Save product into DB
            Database.insertProduct(url, title, price, rating);

        } catch (Exception e) {
            System.out.println("❌ Failed to scrape: " + url);
            e.printStackTrace();
        }
    }
}
