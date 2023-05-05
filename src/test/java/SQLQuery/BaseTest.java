package SQLQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/test/java/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
