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
        case "-r5" -> System.out.println(getReport_5());
        default -> throw new IllegalArgumentException("Nieznany typ raportu: " + reportType);
    };
}


    public String cutText(int maxLength, String text){
        if (text == null) return text;
        if (text.length() > maxLength){
            return (text.substring(0, maxLength-3)+"...");
        }
        else return text;
    }

    //User + Duration
    public String getReport_1(){
        if (reportData == null || reportData.isEmpty())
            return "Zestawienie czasu pracy pracowników - Brak Danych !";

        String reportName = "Zestawienie czasu pracy pracowników za okres: ";
        String fromDate = reportData.getFirst().getFromDate();
        String toDate = reportData.getFirst().getToDate();
        String n1 = "Nazwa Pracownika";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(cutText(35,reportRow.getUser()), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }

    //Project + Duration
    public String getReport_2(){
        if (reportData == null || reportData.isEmpty())
            return "Zestawienie czasu pracy w projektach - Brak Danych !";

        String reportName = "Zestawienie czasu pracy w projektach za okres:";
        String fromDate = reportData.getFirst().getFromDate();
        String toDate = reportData.getFirst().getToDate();
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(cutText(35,reportRow.getProject()), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }

    //Project + Duration + Percent
    public String getReport_3(){
        if (reportData == null || reportData.isEmpty())
            return "Zestawienie projektów dla pracownika - Brak Danych !";

        String reportName = "Zestawienie projektów dla pracownika za okres: ";
        String fromDate = reportData.getFirst().getFromDate();
        String toDate = reportData.getFirst().getToDate();
        String n1 = "Nazwa Projektu";
        String n2 = "Czas [godziny]";
        String n3 = "Procent [%]";
        String linia = "-".repeat(75);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s | %-15s |\n".formatted(n1, n2, n3);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s | %-15s |\n".formatted(cutText(35,reportRow.getProject()), reportRow.getDuration() , reportRow.getPercentage());
        }
        report += linia + "\n";
        return report;
    }

    //Task + Duration - zadania w prpjekcie za okres
    public String getReport_4(){
        if (reportData == null || reportData.isEmpty())
            return "Zestawienie zadań w projekcie - Brak Danych !";

        String reportName = "Zestawienie zadań w projekcie za okres: ";
        String fromDate = reportData.getFirst().getFromDate();
        String toDate = reportData.getFirst().getToDate();
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(cutText(35,reportRow.getTask()), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }

    //Task + Duration - zadania dla pracownika
    public String getReport_5(){
        if (reportData == null || reportData.isEmpty())
            return "Zestawienie zadań dla pracownika - Brak Danych !";

        String reportName = "Zestawienie zadań dla pracownika za okres: ";
        String fromDate = reportData.getFirst().getFromDate();
        String toDate = reportData.getFirst().getToDate();
        String n1 = "Nazwa Zadania";
        String n2 = "Czas [godziny]";
        String linia = "-".repeat(57);
        String report = "";

        report += reportName + fromDate + " - " + toDate + "\n";
        report += linia + "\n";
        report += "| %-35s | %-15s |\n".formatted(n1, n2);
        report += linia + "\n";
        for(ReportData reportRow : reportData) {
            report += "| %-35s | %-15s |\n".formatted(cutText(35,reportRow.getTask()), reportRow.getDuration());
        }
        report += linia + "\n";
        return report;
    }
}
