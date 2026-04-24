package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverManager;
import utils.LocatorReader;

import java.util.List;

public class HtmlTable {

    private String locatorKey;

    // Optional search component!
    public HtmlInput search;

    public HtmlTable(String locatorKey){
        this.locatorKey = locatorKey;
    }

    public int getRowCount(){
        return DriverManager.getDriver()
                .findElements(By.xpath(
                        getTableXpath() + "//tbody/tr"))
                .size();
    }

    public int getColumnCount(){
        return DriverManager.getDriver()
                .findElements(By.xpath(
                        getTableXpath() + "//thead/tr/th"))
                .size();
    }

    public String getCellValue(int row, int col){
        return DriverManager.getDriver()
                .findElement(By.xpath(
                        getTableXpath() +
                                "//tbody/tr[" + row + "]/td[" + col + "]"))
                .getText();
    }

    public List<WebElement> getAllRows(){
        return DriverManager.getDriver()
                .findElements(By.xpath(
                        getTableXpath() + "//tbody/tr"));
    }

    public boolean isDisplayed(){
        return DriverManager.getDriver()
                .findElement(LocatorReader.get(locatorKey))
                .isDisplayed();
    }

    private String getTableXpath(){
        By locator = LocatorReader.get(locatorKey);
        return "//*[@id='" +
                locator.toString().replace("By.id: ", "") + "']";
    }
}