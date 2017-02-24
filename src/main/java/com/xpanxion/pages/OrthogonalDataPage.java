package com.xpanxion.pages;

import com.xpanxion.base.WebPageBase;
import com.xpanxion.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrthogonalDataPage extends WebPageBase {

    By table = By.id("example");
    By nextBtn = By.id("example_next");

    public List<WebElement> getRows() {
        return waitForElement(table).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
    }

    public List<WebElement> getColumns(WebElement rowElement) {
        return waitForElementsBy(rowElement,By.tagName("td"));
    }

    public OrthogonalDataPage clickNext() {
        WebElement nextBtnElement = waitForElement(nextBtn);
        getJavaScriptExecutor().executeScript("window.scrollBy(0,600);");
        if (nextBtnElement.isEnabled()) {
            nextBtnElement.click();
        }
        getJavaScriptExecutor().executeScript("window.scrollBy(0,-600);");
        return this;
    }

    public boolean isNextDisabled() {
        String classAttrib = waitForElement(nextBtn).getAttribute("class");
        return classAttrib.contains("disabled");
    }
}
