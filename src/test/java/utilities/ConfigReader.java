package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public Properties init_properties(){
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./src/test/resources/config/config.properties");
            properties.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
