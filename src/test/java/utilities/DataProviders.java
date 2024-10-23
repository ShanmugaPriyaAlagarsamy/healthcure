package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "appointmentBookingData")
    public String[][] getAppointmentBookingData() throws IOException {

        Excelutility excel = new Excelutility(
                System.getProperty("user.dir") + "//testdata//AppointmentBookingData.xlsx");
        int rowcount = excel.getRowCount("Sheet1");
        int cellcount = excel.getCellCount("Sheet1", 1);

        String[][] data = new String[rowcount][cellcount];

        for (int i = 1; i <= rowcount; i++) {
            for (int j = 0; j < cellcount; j++) {
                data[i - 1][j] = excel.getCellData("Sheet1", i, j);
            }
        }
        return data;

    }

    @DataProvider(name = "loginData")
    public String[][] getLoginData() {
        String[][] data = new String[1][2];

        data[0][0] = "John Doe";
        data[0][1] = "ThisIsNotAPassword";

        return data;

    }

}
