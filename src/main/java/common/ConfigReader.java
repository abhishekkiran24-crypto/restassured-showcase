package common;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties p = new Properties();

    static { 
    	// static block executes once when the class is loaded
    	InputStream is = null;
        try
        {
        ClassLoader loader=ConfigReader.class.getClassLoader();
        is = loader.getResourceAsStream("schemas/config.properties"); 
        if (is == null) {
            	is=loader.getResourceAsStream("schemas/config.properties.template");
            }
            if(is==null) {
                throw new RuntimeException("schemas/config.properties not found in classpath");
            }
            p.load(is);
        }
        
        catch (Exception e) 
        {
            throw new RuntimeException("Failed to load schemas/config.properties", e);
        }
    }

    // Returns a non-null value. Allows env var override for github.token
    public static String get(String key) {
        if (key == null) return "";

        // Special case: github.token -> check MY_GH_PAT environment variable
        if ("github.token".equals(key)) {
            String envVal = System.getenv("MY_GH_PAT");
            if (envVal != null && !envVal.isEmpty()) {
                return envVal;
            }
        }

        // fallback to properties file
        String value = p.getProperty(key);
        return value == null ? "" : value;
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        if (v.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
