import java.util.List;

public class ResultPrinter {
    private String reportType;
    private List<ReportData> reportData;

    public ResultPrinter(String reportType, List<ReportData> reportData){
        this.reportData = reportData;
        this.reportType = reportType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<ReportData> getReportData() {
        return reportData;
    }

    public void setReportData(List<ReportData> reportData) {
        this.reportData = reportData;
    }


public void print() {
    switch (reportType) {
        case "-r1" -> System.out.println(getReport_1());
        case "-r2" -> System.out.println(getReport_2());
        case "-r3" -> System.out.println(getReport_3());
        case "-r4" -> System.out.println(getReport_4());
        default -> throw new IllegalArgumentException("Nieznany typ raportu: " + reportType);
    };
}

    //User + Duration
    public String getReport_1(){
        String reportName = "Raport 1";
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getUser(), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }

    //Project + Duration
    public String getReport_2(){
        String reportName = "Raport 2";
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getProject(), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }

    //Project + Duration + Percent
    public String getReport_3(){
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
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s | %-15s |\n".formatted(reportRow.getProject(), reportRow.getDuration() , reportRow.getPercentage());
        }
        report += linia + "\n";
        return report;
    }

    //Task + Duration
    public String getReport_4(){
        String reportName = "Raport 4";
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(reportRow.getTask(), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }
}
