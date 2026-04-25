package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "dev");

        // Load common config first!
        loadFile("config.properties");

        // Load env specific — overrides common!
        loadFile("config-" + env + ".properties");
        loadAllFiles("testdata");
    }

    private static void loadAllFiles(String folderName){
        try {
            URL folderUrl = ConfigReader.class
                    .getClassLoader()
                    .getResource(folderName);

            if(folderUrl == null) return;

            File folder = new File(folderUrl.getPath());

            for(File file : folder.listFiles()){
                if(file.getName().endsWith(".properties")){
                    loadFile(folderName + "/" + file.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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