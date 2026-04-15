package utils;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtils {

    public static String readJsonFile(String fileName){
        try {
            InputStream is = JSONUtils.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);
            return new String(is.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
