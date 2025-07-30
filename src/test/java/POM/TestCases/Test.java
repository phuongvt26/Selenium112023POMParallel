package POM.TestCases;

import org.openqa.selenium.By;

public class Test {
    public void test(){
        By errorPassRequired1 = By.xpath("//div[contains(text(),'The Password field is required.')]");
        errorPassRequired1.findElement();


    }
}
