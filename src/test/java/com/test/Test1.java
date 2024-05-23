package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utility.BaseTest;

public class Test1 extends BaseTest {

	@Test
	public static void main1() throws InterruptedException {

		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Best Washing Machine");
		searchBox.submit();
		Thread.sleep(2000);

		List<WebElement> searchResults = driver.findElements(By.cssSelector("div.yuRUbf a"));

		int amazonCount = 0;
		int flipkartCount = 0;

		for (WebElement result : searchResults) {

			String href = result.getAttribute("href");
			if (href.contains("amazon.in")) {
				result.click();
				Thread.sleep(2000);
				driver.getTitle();
				List<WebElement> items = driver.findElements(
						By.xpath("//div[@class='bxc-grid__content   bxc-grid__content--light  celwidget']"));
				int itemsCount = items.size();
				String title = driver.getTitle();
				if (title != null && !title.isEmpty()) {
					System.out.println("Title is displayed: " + title);
					System.out.println("total items amazon on page: " + itemsCount);
				} else {
					System.out.println("Title is not displayed");
				}
				driver.navigate().back();
				amazonCount++;
			} else if (href.contains("flipkart.com")) {
				result.click();
				String title = driver.getTitle();
				Thread.sleep(2000);
				List<WebElement> items = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
				int itemsCount = items.size();
				if (title != null && !title.isEmpty()) {
					System.out.println("Title is displayed: " + title);
					System.out.println("total items on flipkart page: " + itemsCount);
				} else {
					System.out.println("Title is not displayed");
				}
				driver.navigate().back();
				flipkartCount++;
			}
		}

		System.out.println("Amazon links found: " + amazonCount);
		System.out.println("Flipkart links found: " + flipkartCount);

	}

}
