import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultPrinterTest {
    @Test
    public void testRep1HeaderFooterNoData() {
        List<ReportData> emptyList = new ArrayList<>();
        ResultPrinter resultPrinter = new ResultPrinter("r1",emptyList);
        String reportName = "Raport 1";
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        report += linia + "\n";

        assertEquals(report,resultPrinter.getReport_1());
    }

    @Test
    public void testRep2HeaderFooterNoData() {
        List<ReportData> emptyList = new ArrayList<>();
        ResultPrinter resultPrinter = new ResultPrinter("r2",emptyList);
        String reportName = "Raport 2";
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        report += linia + "\n";

        assertEquals(report,resultPrinter.getReport_2());
    }

    @Test
    public void testRep3HeaderFooterNoData() {
        List<ReportData> emptyList = new ArrayList<>();
        ResultPrinter resultPrinter = new ResultPrinter("r3",emptyList);
        String reportName = "Raport 3";
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String n3 = "Procent [%]";
        String linia = "-".repeat(75);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s | %-15s |\n".formatted(n1, n2, n3);
        report += linia + "\n";
        report += linia + "\n";

        assertEquals(report,resultPrinter.getReport_3());
    }

    @Test
    public void testRep4HeaderFooterNoData() {
        List<ReportData> emptyList = new ArrayList<>();
        ResultPrinter resultPrinter = new ResultPrinter("r4",emptyList);
        String reportName = "Raport 4";
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        report += linia + "\n";

        assertEquals(report,resultPrinter.getReport_4());
    }

    @Test
    public void testRep1Data() {
        ReportData reportData1 = new ReportData();
        reportData1.setUser("user1");
        reportData1.setDuration(11.11);

        ReportData reportData2 = new ReportData();
        reportData2.setUser("user2");
        reportData2.setDuration(22.22);

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Raport 1";
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
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

        ReportData reportData2 = new ReportData();
        reportData2.setProject("project2");
        reportData2.setDuration(22.22);

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Raport 2";
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
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

        ReportData reportData2 = new ReportData();
        reportData2.setProject("project2");
        reportData2.setDuration(22.22);
        reportData2.setPercentage(49.0);

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Raport 3";
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String n3 = "Procent [%]";
        String linia = "-".repeat(75);
        String report = "";

        report += reportName + "\n";
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

        ReportData reportData2 = new ReportData();
        reportData2.setTask("task2");
        reportData2.setDuration(22.22);

        List<ReportData> dataRows = new ArrayList<>();
        dataRows.add(reportData1);
        dataRows.add(reportData2);

        String reportName = "Raport 4";
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
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
