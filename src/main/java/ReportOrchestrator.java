import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReportOrchestrator {
    private final XlsxReader xlsxReader = new XlsxReader();
    private final ReportService reportService = new ReportService();
    private final XlsxGenerator xlsxGenerator = new XlsxGenerator();

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

            LocalDate fromDate = reportContext.getDateFrom();
            LocalDate toDate = reportContext.getDateTo();

            if(fromDate==null){
                fromDate = allTasks.stream().map(Task::getData).min(LocalDate::compareTo).orElse(null);
                toDate = allTasks.stream().map(Task::getData).max(LocalDate::compareTo).orElse(null);
            }

            List<Task> filteredListOfTasks = filterTaskByDate(allTasks, fromDate, toDate);

            Collection<ReportData> reportData;

            switch (reportContext.getReportType()) {
                case "-r1":
                    if (reportContext.getLimit() != null) {
                        reportData = reportService.hoursByEmployee(filteredListOfTasks, reportContext.getLimit());
                    } else
                        reportData = reportService.hoursByEmployee(filteredListOfTasks);
                    if (reportContext.getExportToXslx() != null) {
                        System.out.println("Export to XSLX");
                        xlsxGenerator.generate(reportContext.getReportType(), reportData.stream().toList());
                    }
                    break;
                case "-r2":
                    if (reportContext.getLimit() != null) {
                        reportData = reportService.hoursByProject(filteredListOfTasks, reportContext.getLimit());
                    } else
                        reportData = reportService.hoursByProject(filteredListOfTasks);
                    if (reportContext.getExportToXslx() != null) {
                        System.out.println("Export to XSLX");
                        xlsxGenerator.generate(reportContext.getReportType(), reportData.stream().toList());
                    }
                    break;
                case "-r3":
                    if (reportContext.getLimit() != null) {
                        reportData = reportService.employeeProjects(filteredListOfTasks, reportContext.getEmployeeName(), reportContext.getLimit());
                    } else
                        reportData = reportService.employeeProjects(filteredListOfTasks, reportContext.getEmployeeName());
                    if (reportContext.getExportToXslx() != null) {
                        System.out.println("Export to XSLX");
                        xlsxGenerator.generate(reportContext.getReportType(), reportData.stream().toList());
                    }
                    break;
                case "-r4":
                    if (reportContext.getLimit() != null) {
                        reportData = reportService.tasksInProject(filteredListOfTasks, reportContext.getProjectName(), reportContext.getLimit());
                    } else
                        reportData = reportService.tasksInProject(filteredListOfTasks, reportContext.getProjectName());
                    if (reportContext.getExportToXslx() != null) {
                        System.out.println("Export to XSLX");
                        xlsxGenerator.generate(reportContext.getReportType(), reportData.stream().toList());
                    }
                    break;
                case "-r5":
                    if (reportContext.getLimit() != null) {
                        reportData = reportService.employeeTasks(filteredListOfTasks, reportContext.getEmployeeName(), reportContext.getLimit());
                    } else
                        reportData = reportService.employeeTasks(filteredListOfTasks, reportContext.getEmployeeName());
                    if (reportContext.getExportToXslx() != null) {
                        System.out.println("Export to XSLX");
                        xlsxGenerator.generate(reportContext.getReportType(), reportData.stream().toList());
                    }
                    break;
                default:
                    System.out.println("Invalid report type. Report type "  + reportContext.getReportType() + " does not exist.");
                    return;
            }

            // System.out.println("Zestawienie czasu pracy w projektach za okres:" + fromDate + " - " + toDate);
            ResultPrinter resultPrinter = new ResultPrinter(
                    reportContext.getReportType(),
                    reportData.stream().toList(),
                    fromDate,
                    toDate
            );
            resultPrinter.print();

        } catch (IllegalArgumentException e) {
            System.err.println("Wrong report params: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    private List<Task> filterTaskByDate(Collection<Task> tasks, LocalDate fromDate, LocalDate toDate) {
        return tasks.stream()
                .filter( task-> {
                    LocalDate taskDate = task.getData();
                    if (taskDate == null)
                        return false;
                    if (fromDate != null && task.getData().isBefore(fromDate))
                        return false;
                    if (toDate != null && task.getData().isAfter(toDate))
                        return false;
                    return true;
                })
                .collect(Collectors.toList());
    }
}
