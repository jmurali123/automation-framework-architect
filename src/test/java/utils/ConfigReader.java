package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "dev");

        // Load common config first!
        loadFile("config.properties");

        // Load env specific — overrides common!
        loadFile("config-" + env + ".properties");
    }

    private static void loadFile(String fileName){
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}