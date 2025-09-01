
package com.example.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonScraper {

    public void scrapeProduct(WebDriver driver, String url) {
        try {
            // ✅ Open the product page
            driver.get(url);
            Thread.sleep(3000); // basic wait (can later replace with WebDriverWait)

            // ✅ Extract product title
            String title = driver.findElement(By.id("productTitle")).getText().trim();

            // ✅ Extract price (try multiple selectors)
            String price;
            try {
                WebElement priceElement = driver.findElement(By.cssSelector("span.a-price span.a-offscreen"));
                price = priceElement.getText().trim();
            } catch (Exception e1) {
                try {
                    WebElement dealPrice = driver.findElement(By.id("priceblock_dealprice"));
                    price = dealPrice.getText().trim();
                } catch (Exception e2) {
                    try {
                        WebElement ourPrice = driver.findElement(By.id("priceblock_ourprice"));
                        price = ourPrice.getText().trim();
                    } catch (Exception e3) {
                        price = "Price not available";
                    }
                }
            }

            // ✅ Extract rating (try multiple selectors)
            String rating;
            try {
                WebElement ratingElement = driver.findElement(By.cssSelector("span[data-asin] i span.a-icon-alt"));
                rating = ratingElement.getText().trim();
            } catch (Exception e1) {
                try {
                    WebElement ratingElement = driver.findElement(By.cssSelector("#acrPopover span.a-icon-alt"));
                    rating = ratingElement.getText().trim();
                } catch (Exception e2) {
                    try {
                        WebElement ratingElement = driver.findElement(By.cssSelector("span[data-asin] span.a-icon-alt"));
                        rating = ratingElement.getText().trim();
                    } catch (Exception e3) {
                        rating = "Rating not available";
                    }
                }
            }

            // ✅ Print result to console
            System.out.println("\n--- Product ---");
            System.out.println("URL: " + url);
            System.out.println("Title: " + title);
            System.out.println("Price: " + price);
            System.out.println("Rating: " + rating);

            // ✅ Save product into DB
            Database.insertProduct(url, title, price, rating);

        } catch (Exception e) {
            System.out.println("❌ Failed to scrape: " + url);
            e.printStackTrace();
        }
    }
}
