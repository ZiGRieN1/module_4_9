package org.example.module4_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CustomWait {
    private WebDriverWait wait;
    private final int secondsToWait;
    private final Logger LOG = LoggerFactory.getLogger(CustomWait.class);

    public static CustomWait customWait(WebDriver driver, int seconds) {
        return new CustomWait(driver, seconds);
    }

    public CustomWait(WebDriver driver, int seconds) {
        secondsToWait = seconds;
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }


    public WebElement clickable(By loc) {
        LOG.info("Ждём " + secondsToWait + " секунд и элемент станет кликабельным: " + loc);
        return wait.until(ExpectedConditions.elementToBeClickable(loc));
    }

    public WebElement visible(By loc) {
        LOG.info("Ждём " + secondsToWait + " секунд и элемент станет видимым: " + loc);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
    }
}
