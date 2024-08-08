package POM.DataProvider;

import CONFIG.Helper.ExcelHelpers;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {
    //Khai báo nơi cung cấp dữ liệu: Dùng dataProvider
    @DataProvider(name = "datalogin", parallel = true)
    public Object [][] datalogin(){
        return new Object[][] {
                {"admin@example.com","123456"},
//                {"admin@example.com","123456"},
        };
    }
    @DataProvider(name = "addnewcustomer", parallel = true)
    public Object [][] addnewcustomer(){
        return new Object[][] {
                {"hung",25,"Ha Noi"},
                {"phuong",25,"HCM"},
                {"Dieu",25,"Đà Nẵng"},
        };
    }
    // Data from excel
    @DataProvider(name = "dataLoginFromExcel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelpers excelHelper = new ExcelHelpers();
        Object[][] data = excelHelper.getExcelData("src/test/resources/testdata/Login.xlsx", "Sheet3");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }
    @DataProvider(name = "dataLoginFromExcelMutilbleRow")
    public Object[][] dataLoginFromExcelMutilbleRow() {
        ExcelHelpers excelHelper = new ExcelHelpers();
        Object[][] data = excelHelper.getDataHashTable("src/test/resources/testdata/Login.xlsx", "Sheet3", 2, 4);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }
}
