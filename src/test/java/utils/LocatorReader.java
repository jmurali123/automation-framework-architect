package utils;

import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class LocatorReader {
    private static Properties properties = new Properties();
    static{
        loadAllFiles("locators");
    }

    private static void loadAllFiles(String folderName){
        try {
            // Get all files in locators folder!
            URL folderUrl = LocatorReader.class
                    .getClassLoader()
                    .getResource(folderName);

            File folder = new File(folderUrl.getPath());

            // Load each properties file found!
            for(File file : folder.listFiles()){
                if(file.getName().endsWith(".properties")){
                    loadFile(folderName + "/" + file.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFile(String fileName) {
        InputStream is= LocatorReader.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Existing method — no params
    public static By get(String key){
        String value = properties.getProperty(key);
        return parseLocator(value);
    }

    // New method — with dynamic params!
    public static By get(String key, String... params){
        String value = properties.getProperty(key);

        // Replace placeholders with actual values!
        for(int i = 0; i < params.length; i++){
            value = value.replace("{" + i + "}", params[i]);
        }

        return parseLocator(value);
    }

    private static By parseLocator(String value){
        String[] parts = value.split(":", 2);
        String type = parts[0].trim();
        String locator = parts[1].trim();

        switch(type.toLowerCase()){
            case "id":      return By.id(locator);
            case "css":     return By.cssSelector(locator);
            case "xpath":   return By.xpath(locator);
            case "name":    return By.name(locator);
            case "class":   return By.className(locator);
            default: throw new IllegalArgumentException(
                    "Unknown locator type: " + type);
        }
    }
}
