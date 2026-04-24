package pages.components;

import utils.LocatorReader;

import utils.DriverManager;

public class HtmlLabel {
        private String locatorKey;

        public HtmlLabel(String locatorKey){
            this.locatorKey = locatorKey;
        }

        public String getText(){
            return DriverManager.getDriver()
                    .findElement(LocatorReader.get(locatorKey))
                    .getText();
        }

        public boolean isDisplayed(){
            return DriverManager.getDriver()
                    .findElement(LocatorReader.get(locatorKey))
                    .isDisplayed();
        }
}

