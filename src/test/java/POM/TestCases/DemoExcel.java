package POM.TestCases;

import CONFIG.Helpers.ExcelHelpers;
import CONFIG.Utils.LogUtils;
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
            LogUtils.info(excelHelpers.getCellData("EMAIL", i));
            LogUtils.info(excelHelpers.getCellData("PASSWORD", i));
        }
// set data to excel
        excelHelpers.setCellData("Phuong1","EMAIL", 4);
        excelHelpers.setCellData("TEST", 1,1);
        LogUtils.info("Set cell thành công");

    }
}
