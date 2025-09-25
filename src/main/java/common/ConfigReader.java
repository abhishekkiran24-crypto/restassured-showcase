package common;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties p = new Properties();

    static {  // static block executes once when the class is loaded
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("schemas/config.properties")) {
            if (is == null) {
                throw new RuntimeException("schemas/config.properties not found in classpath");
            }
            p.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to schemas/load config.properties", e);
        }
    }

    //returns a non-null value. Checks OS env var overide first
    public static String get(String key) {
    	if(key==null) return "";
    	
        String value= p.getProperty(key);
        return value==null ? "" : value;
    }
    public static int getInt(String key, int defaultValue)
    {
    	String v=get(key);
    	if(v.isEmpty()) return defaultValue;
    	try
    	{
    		return Integer.parseInt(v);
    	}
    	catch (NumberFormatException e) {
    		return defaultValue;
    	}
    }
}
