package POM.TestCases;

import CONFIG.Utils.LogUtils;
import POM.DataProvider.DataProviderFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DemoDataProvider {

//    //Khai báo nơi cung cấp dữ liệu: Dùng dataProvider
//    @DataProvider(name = "datalogin", parallel = true)
//    public Object [][] datalogin(){
//        return new Object[][] {
//                {"admin1@example.com","123456"},
//                {"admin2@example.com","1234567"},
//        };
//    }
//    @DataProvider(name = "addnewcustomer", parallel = true)
//    public Object [][] addnewcustomer(){
//        return new Object[][] {
//                {"hung",25,"Ha Noi"},
//                {"phuong",25,"HCM"},
//                {"Dieu",25,"Đà Nẵng"},
//        };
//    }
    // Truyền nơi cung cấp dữ liệu vào trong testcase
    @Test(dataProvider = "datalogin", dataProviderClass = DataProviderFactory.class)
    public void testloginsucesses(String Email, String Password){
        LogUtils.info("Email "+ Email);
        LogUtils.info("Password " + Password);
    }
    @Test(dataProvider = "addnewcustomer", dataProviderClass = DataProviderFactory.class)
    public void testaddnewcustomer(String Name, int age, String city){
        LogUtils.info("Name "+ Name);
        LogUtils.info("Tuổi " + age);
        LogUtils.info("City " + city);
    }
    // Excel
    @Test(dataProvider = "dataLoginFromExcel", dataProviderClass = DataProviderFactory.class)
    public void testLoginFromExelFile(String Email, String Password){
        LogUtils.info("Email "+ Email);
        LogUtils.info("Password " + Password);
    }
    @Test(dataProvider = "dataLoginFromExcelMutilbleRow", dataProviderClass = DataProviderFactory.class)
    public void testLoginFromExelFileMutibleRow(Hashtable< String, String > data){
        LogUtils.info("Email "+ data.get("EMAIL")); //EMAIL: tên cột trong file Excel
        LogUtils.info("Password " + data.get("PASSWORD")); //PASSWORD: tên cột trong file Excel
    }

}
