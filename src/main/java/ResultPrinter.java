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

    public void print(){
        if (reportType=="r1") printReport_1(reportData);
        if (reportType=="r2") printReport_2(reportData);
    }

    public void printReport_1(List<ReportData> reportData){
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "---------------------------------------------";

        System.out.println("Raport 1 - Użytkownicy i czas zadań");
        System.out.println(linia);
        System.out.printf("| %-21s | %-17s |\n", n1, n2);
        System.out.println(linia);
        for(ReportData reportRow : reportData) {
            System.out.printf("| %-21s | %-17s |\n", reportRow.getUser(), reportRow.getDuration());
        }
        System.out.println(linia);
    }

    public void printReport_2(List<ReportData> reportData){
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "---------------------------------------------";

        System.out.println("Raport 2 - Zadania i czas");
        System.out.println(linia);
        System.out.printf("| %-21s | %-17s |\n", n1, n2);
        System.out.println(linia);
        for(ReportData reportRow : reportData) {
            System.out.printf("| %-21s | %-17s |\n", reportRow.getProject(), reportRow.getDuration());
        }
        System.out.println(linia);
    }
}
