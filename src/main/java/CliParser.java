import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CliParser {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ReportContext parse(String[] args) {

        ReportContext context = new ReportContext();

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

            if (arg.equals("-r1") ||
                    arg.equals("-r2") ||
                    arg.equals("-r3") ||
                    arg.equals("-r4") ||
                    arg.equals("-r5")) {
                if (context.getReportType() == null) {
                    context.setReportType(arg);
                } else {
                    throw new IllegalArgumentException(
                            "Multiple report types detected. You can generate only 1 report at the same time");
                }
            }

            if (arg.equals("-emp")) {
                if (i + 2 < args.length) {
                    context.setEmployeeName(args[i + 1] + " " + args[i + 2]);
                    i += 2;
                } else {
                    throw new IllegalArgumentException("Employee name not specified");
                }
            }

            if (arg.equals("-project")) {
                if (i + 1 < args.length) {
                    context.setProjectName(args[++i]);
                }
            }

            if (arg.equals("-n")) {
                if (i + 1 < args.length) {
                    context.setLimit(Integer.parseInt(args[++i]));
                }
            }

            if (arg.equals("-path")) {
                if (i + 1 < args.length) {
                    context.setPath(args[++i]);
                }
            }

            if (arg.equals("-from")) {
                if (i + 1 < args.length) {
                    try {
                        context.setDateFrom(
                                LocalDate.parse(args[++i], DATE_FORMATTER)
                        );
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException(
                                "Invalid -from date format. Expected dd.MM.yyyy");
                    }
                }
            }

            if (arg.equals("-to")) {
                if (i + 1 < args.length) {
                    try {
                        context.setDateTo(
                                LocalDate.parse(args[++i], DATE_FORMATTER)
                        );
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException(
                                "Invalid -to date format. Expected dd.MM.yyyy");
                    }
                }
            }

            if (arg.equals("-xlsx")) {
                if (i + 1 < args.length) {
                    context.setExportToXslx(args[++i]);
                }
            }
        }

        return context;
    }
}