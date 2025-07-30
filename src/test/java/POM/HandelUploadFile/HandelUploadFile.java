package POM.HandelUploadFile;

import CONFIG.Drivers.DriverManager;
import CONFIG.Keywords.WebUI;
import POM.Base.BaseSetup;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class HandelUploadFile extends BaseSetup {
    @Test
    public void testUploadFileWithSendKeys() throws InterruptedException {
        DriverManager.getDriver().get("https://cgi-lib.berkeley.edu/ex/fup.html");
        WebUI.waitForPageLoaded();

        WebUI.sleep(2);

        By inputFileUpload = By.xpath("//input[@name='upfile']");
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/Login.xlsx";
        WebUI.setText(inputFileUpload,filePath);
        Thread.sleep(4000);
    }
    @Test
    public void testUploadFile2() {
        DriverManager.getDriver().get("https://files.fm/");

        WebUI.sleep(2);

        By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By inputFileUpload = By.xpath("//div[@id='file_select_button']//input[@id='file_upload']");

        String filePath = "src/test/Ảnh chụp màn hình (19).png";

        //Click để mở form upload
        WebUI.clickElement(divFileUpload);
        WebUI.sleep(2);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        WebUI.sleep(2);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.sleep(2);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        WebUI.sleep(2);
        // verify upload thành công

    }
}
