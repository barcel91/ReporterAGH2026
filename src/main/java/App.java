import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {

        String reportType = args[0];

        XlsxReader xlsxReader = new XlsxReader();
        ReportService reportService = new ReportService();

        List<Task> tasks;
        try {
            tasks = xlsxReader.readData("src/main/resources/2012").stream().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<ReportData> reportData = reportService.hoursByEmployee(tasks).stream().toList();
        ResultPrinter resultPrinter = new ResultPrinter(reportType, reportData);
        resultPrinter.print();
    }
}

