package Props;

import javax.naming.NameNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLinker {
    private static final String CONFIG_PATH = "./config.properties";

    public static String getProperty(String propertyName) throws NameNotFoundException, FileNotFoundException {

       InputStream propsFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("./");

        try {
            Properties props = new Properties();
            props.load(propsFile);
            return props.getProperty(propertyName);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new NameNotFoundException();
        }
    }
}
