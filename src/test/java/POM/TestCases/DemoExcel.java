package POM.TestCases;

import CONFIG.Helpers.ExcelHelpers;
import org.testng.annotations.Test;

public class DemoExcel {
    @Test
    public void getDataFormExcel() {
        ExcelHelpers excelHelpers= new ExcelHelpers(); //khởi tạo đối tượng class do hàm class Excelhelpers khai báo các hàm k phải hàm public
        excelHelpers.setExcelFile("src/test/resources/testdata/Login.xlsx","Sheet1");
//        System.out.println(excelHelpers.getCellData("EMAIL", 1));
//        System.out.println(excelHelpers.getCellData("EMAIL", 2));
//        System.out.println(excelHelpers.getCellData("PASSWORD", 1));
//        System.out.println(excelHelpers.getCellData("PASSWORD", 2));
        for(int i=0;i<=3;i++){
            System.out.println(excelHelpers.getCellData("EMAIL", i));
            System.out.println(excelHelpers.getCellData("PASSWORD", i));
        }
// set data to excel
        excelHelpers.setCellData("Phuong1","EMAIL", 4);
        excelHelpers.setCellData("TEST", 1,1);
        System.out.println("Set cell thành công");

    }
}
