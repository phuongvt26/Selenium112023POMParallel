package POM.Contains;

import CONFIG.Helper.PropertiesHelper;

public class DataConfig {
    public static String URL = PropertiesHelper.getValue("URL");
    public static String EMAIL = PropertiesHelper.getValue("EMAIL");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
}
