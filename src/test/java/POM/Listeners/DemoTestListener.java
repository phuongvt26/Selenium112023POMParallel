package POM.Listeners;

import POM.DataProvider.DataProviderFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;


@Listeners({TestListener.class})
public class DemoTestListener {
    @Test(dataProvider = "datalogin", dataProviderClass = DataProviderFactory.class)
    public void testloginsucesses(String Email, String Password){
        System.out.println("Email "+ Email);
        System.out.println("Password " + Password);
    }
    @Test(dataProvider = "addnewcustomer", dataProviderClass = DataProviderFactory.class)
    public void testaddnewcustomer(String Name, int age, String city){
        System.out.println("Name "+ Name);
        System.out.println("Tuổi " + age);
        System.out.println("City " + city);
    }
    // Excel
    @Test(dataProvider = "dataLoginFromExcel", dataProviderClass = DataProviderFactory.class)
    public void testLoginFromExelFile(String Email, String Password){
        System.out.println("Email "+ Email);
        System.out.println("Password " + Password);
    }
    @Test(dataProvider = "dataLoginFromExcelMutilbleRow", dataProviderClass = DataProviderFactory.class)
    public void testLoginFromExelFileMutibleRow(Hashtable< String, String > data){
        System.out.println("Email "+ data.get("EMAIL")); //EMAIL: tên cột trong file Excel
        System.out.println("Password " + data.get("PASSWORD")); //PASSWORD: tên cột trong file Excel
    }
    //Demo TH onTestFailedButWithinSuccessPercentage
        @Test(invocationCount = 5, successPercentage = 90)
        public void kiemTraChanLe() {
            int count = 1;
            count++;
            System.out.println("Số lần chạy: " + count);

            if (count % 2 == 0) {
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }
    }


