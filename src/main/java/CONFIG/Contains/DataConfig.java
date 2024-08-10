package CONFIG.Contains;

import CONFIG.Helpers.PropertiesHelper;

public class DataConfig {
    public static String URL = PropertiesHelper.getValue("URL");
    public static String EMAIL = PropertiesHelper.getValue("EMAIL");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String SCREENSHOT_PATH = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
}
