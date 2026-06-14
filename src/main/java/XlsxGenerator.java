import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsxGenerator {

    private final String reportType;
    private final List<ReportData> reportData;

    public xlsxGenerator(String reportType, List<ReportData> reportData) {
        this.reportType = reportType;
        this.reportData = reportData;
    }

    public void export() throws IOException {

        Workbook workbook = new XSSFWorkbook();

        if ("r1".equals(reportType)) {
            exportReport1(workbook);
        } else if ("r2".equals(reportType)) {
            exportReport2(workbook);
        } else if ("r3".equals(reportType)) {
//            exportReport3(workbook);
//        } else if ("r4".equals(reportType)) {
//            exportReport4(workbook);
//        } else if ("r5".equals(reportType)) {
//            exportReport5(workbook);
        } else {
            throw new IllegalArgumentException(
                    "Nieznany typ raportu: " + reportType);
        }

        try (FileOutputStream out = new FileOutputStream("output.xlsx")) {
            workbook.write(out);
        }

        workbook.close();
    }

    private void exportReport1(Workbook workbook) {

        Sheet sheet = workbook.createSheet("Raport_1");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nazwa Pracownika");
        header.createCell(1).setCellValue("Czas [godziny]");

        int rowNum = 1;

        for (ReportData rowData : reportData) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(rowData.getUser());

            row.createCell(1)
                    .setCellValue(rowData.getDuration());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void exportReport2(Workbook workbook) {

        Sheet sheet = workbook.createSheet("Raport_2");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nazwa Projektu");
        header.createCell(1).setCellValue("Czas [godziny]");

        int rowNum = 1;

        for (ReportData rowData : reportData) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(rowData.getProject());

            row.createCell(1)
                    .setCellValue(rowData.getDuration());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }
}
