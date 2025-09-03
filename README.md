# Amazon Product Scraper (Java + Selenium + SQLite)

A Java-based web scraper that extracts **Amazon product details** (title, price, rating) using **Selenium WebDriver** and stores the results in a local **SQLite database**.

---

##  Features
- Scrapes Amazon product pages using **Selenium** (dynamic page rendering supported).  
- Extracts:
  - ✅ Product Title  
  - ✅ Price (fallback if primary selector fails)  
  - ✅ Rating  
- Saves results into a **SQLite database (`scraper.db`)**.  
- Handles missing/optional fields gracefully.  
- Includes **Maven build support** for easy setup and packaging.  

---

## Tech Stack
- **Java 17**  
- **Maven** (dependency + build management)  
- **Selenium 4** (browser automation)  
- **WebDriverManager** (auto-manages ChromeDriver/GeckoDriver)  
- **SQLite** (local DB storage)  
- **Picocli** (CLI support, optional for extensions)  

---

## How to Run

1. Clone the Repo
    ```bash
    git clone https://github.com/Anoopchinthala/Web-Scraper.git

2. Run 
    ```bash
    mvn clean compile
    mvn exec:java

## Output

--- Product ---  <br>
URL: https://www.amazon.in/dp/B0F3XGBH79/ <br>
Title: Samsung Galaxy S23 5G <br>
Price: ₹47,999 <br>
Rating: 4.6 out of 5 stars <br>
Saved product: Samsung Galaxy S23 5G <br>

