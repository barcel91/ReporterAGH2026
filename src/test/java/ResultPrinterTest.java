import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultPrinterTest {

    @Test
    public void testCutText(){
        ResultPrinter rp = new ResultPrinter("test",null);
        String testText = "Testowy";
        String result1 = rp.cutText(6,testText);
        assertEquals("Tes...",result1);
    }


    @Test
    public void testRep1Data() {
        ReportData reportData1 = new ReportData();
        reportData1.setUser("user1");
        reportData1.setDuration(11.11);
        reportData1.setFromDate("2025.12.23");
        reportData1.setToDate("2026.05.12");

        ReportData reportData2 = new ReportData();
        reportData2.setUser("user2");
        reportData2.setDuration(22.22);
        reportData2.setFromDate("2025.12.23");
        reportData2.setToDate("2026.05.12");

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Zestawienie czasu pracy pracowników za okres: ";
        String fromDate = dataRows.getFirst().getFromDate();
        String toDate = dataRows.getFirst().getToDate();
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : dataRows) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getUser(), reportRow.getDuration());
        }
        report += linia + "\n";

        ResultPrinter resultPrinter = new ResultPrinter("r1",dataRows);

        assertEquals(report,resultPrinter.getReport_1());
    }

    @Test
    public void testRep2Data() {
        ReportData reportData1 = new ReportData();
        reportData1.setProject("project1");
        reportData1.setDuration(11.11);
        reportData1.setFromDate("2025.12.23");
        reportData1.setToDate("2026.05.12");

        ReportData reportData2 = new ReportData();
        reportData2.setProject("project2");
        reportData2.setDuration(22.22);
        reportData2.setFromDate("2025.12.23");
        reportData2.setToDate("2026.05.12");

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Zestawienie czasu pracy w projektach za okres:";
        String fromDate = dataRows.getFirst().getFromDate();
        String toDate = dataRows.getFirst().getToDate();
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : dataRows) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getProject(), reportRow.getDuration());
        }
        report += linia + "\n";

        ResultPrinter resultPrinter = new ResultPrinter("r2",dataRows);

        assertEquals(report,resultPrinter.getReport_2());
    }

    @Test
    public void testRep3Data() {
        ReportData reportData1 = new ReportData();
        reportData1.setProject("project1");
        reportData1.setDuration(11.11);
        reportData1.setPercentage(51.0);
        reportData1.setFromDate("2025.12.23");
        reportData1.setToDate("2026.05.12");


        ReportData reportData2 = new ReportData();
        reportData2.setProject("project2");
        reportData2.setDuration(22.22);
        reportData2.setFromDate("2025.12.23");
        reportData2.setToDate("2026.05.12");

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Zestawienie projektów dla pracownika za okres: ";
        String fromDate = dataRows.getFirst().getFromDate();
        String toDate = dataRows.getFirst().getToDate();
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String n3 = "Procent [%]";
        String linia = "-".repeat(75);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s | %-15s |\n".formatted(n1, n2, n3);
        report += linia + "\n";
        for(ReportData reportRow : dataRows) {
            report += "| %-35s | %-15s | %-15s |\n".formatted(reportRow.getProject(), reportRow.getDuration() , reportRow.getPercentage());
        }
        report += linia + "\n";

        ResultPrinter resultPrinter = new ResultPrinter("r3",dataRows);

        assertEquals(report,resultPrinter.getReport_3());
    }

    @Test
    public void testRep4Data() {
        ReportData reportData1 = new ReportData();
        reportData1.setTask("task1");
        reportData1.setDuration(11.11);
        reportData1.setFromDate("2025.12.23");
        reportData1.setToDate("2026.05.12");

        ReportData reportData2 = new ReportData();
        reportData2.setTask("task2");
        reportData2.setDuration(22.22);
        reportData2.setFromDate("2025.12.23");
        reportData2.setToDate("2026.05.12");

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Zestawienie zadań w projekcie za okres: ";
        String fromDate = dataRows.getFirst().getFromDate();
        String toDate = dataRows.getFirst().getToDate();
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : dataRows) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getTask(), reportRow.getDuration());
        }
        report += linia + "\n";

        ResultPrinter resultPrinter = new ResultPrinter("r4",dataRows);

        assertEquals(report,resultPrinter.getReport_4());
    }

}
