import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ReportOrchestrator {
    private final XlsxReader xlsxReader = new XlsxReader();
    private final ReportService reportService = new ReportService();

    public void run(String[] args) {
        CliParser cliParser = new CliParser();
        try {
            ReportContext reportContext = cliParser.parse(args);

            List<Task> allTasks;
            try {
                allTasks = xlsxReader.readData(reportContext.getPath()).stream().toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Collection<ReportData> reportData;
            switch (reportContext.getReportType()) {
                case "-r1":
                    reportData = reportService.hoursByEmployee(allTasks);
                    break;
                case "-r2":
                    reportData = reportService.hoursByProject(allTasks);
                    break;
                case "-r3":
                    reportData = reportService.employeeProjects(allTasks, reportContext.getEmployeeName());
                    break;
                case "-r4":
                    reportData = reportService.tasksInProject(allTasks, reportContext.getProjectName());
                    break;
                case "-r5":
                    reportData = reportService.employeeTasks(allTasks, reportContext.getEmployeeName());
                default:
                    System.out.println("Invalid report type. Report type "  + reportContext.getReportType() + " does not exist.");
                    return;
            }

            ResultPrinter resultPrinter = new ResultPrinter(reportContext.getReportType(), reportData.stream().toList());
            resultPrinter.print();

            if (reportContext.getExportToXslx() != null) {
                System.out.println("Export to XSLX");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Wrong report params: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
