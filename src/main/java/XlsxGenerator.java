import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsxGenerator {

    private static final String OUTPUT_DIR = "output";
    private static final String OUTPUT_FILE = "output.xlsx";

    public void generate(String reportType,
                         List<ReportData> reportData) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        switch (reportType) {

            case "-r1":
                createReport1(workbook, reportData);
                break;

            case "-r2":
                createReport2(workbook, reportData);
                break;

            case "-r3":
                createReport3(workbook, reportData);
                break;

            case "-r4":
                createReport4(workbook, reportData);
                break;

            case "-r5":
                createReport5(workbook, reportData);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported report type: " + reportType);
        }

        saveWorkbook(workbook);
    }

    private void saveWorkbook(Workbook workbook) throws IOException {

        File directory = new File(OUTPUT_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, OUTPUT_FILE);

        try (FileOutputStream outputStream =
                     new FileOutputStream(file)) {

            workbook.write(outputStream);
        }

        workbook.close();
    }

    private void createReport1(
            Workbook workbook,
            List<ReportData> reportData) {

        Sheet sheet = workbook.createSheet("Report 1");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Employee");
        header.createCell(1).setCellValue("Hours");

        int rowNumber = 1;

        for (ReportData data : reportData) {

            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(data.getUser());
            row.createCell(1).setCellValue(data.getDuration());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void createReport2(
            Workbook workbook,
            List<ReportData> reportData) {

        Sheet sheet = workbook.createSheet("Report 2");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Project");
        header.createCell(1).setCellValue("Hours");

        int rowNumber = 1;

        for (ReportData data : reportData) {

            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(data.getProject());
            row.createCell(1).setCellValue(data.getDuration());
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void createReport3(
            Workbook workbook,
            List<ReportData> reportData) {

        Sheet sheet = workbook.createSheet("Report 3");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Project");
        header.createCell(1).setCellValue("Hours");
        header.createCell(2).setCellValue("Percentage");

        int rowNumber = 1;

        for (ReportData data : reportData) {

            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(data.getProject());
            row.createCell(1).setCellValue(data.getDuration());
            row.createCell(2).setCellValue(data.getPercentage());
        }
    }

    private void createReport4(
            Workbook workbook,
            List<ReportData> reportData) {

        Sheet sheet = workbook.createSheet("Report 4");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Task");
        header.createCell(1).setCellValue("Hours");

        int rowNumber = 1;

        for (ReportData data : reportData) {

            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(data.getTask());
            row.createCell(1).setCellValue(data.getDuration());
        }
    }

    private void createReport5(
            Workbook workbook,
            List<ReportData> reportData) {

        Sheet sheet = workbook.createSheet("Report 5");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Task");
        header.createCell(1).setCellValue("Hours");

        int rowNumber = 1;

        for (ReportData data : reportData) {

            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(data.getTask());
            row.createCell(1).setCellValue(data.getDuration());
        }
    }
}
