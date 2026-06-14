public class CliParser {
    public ReportContext parse(String[] args) {
        ReportContext context = new ReportContext();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.contains("-r")) {
                if (context.getReportType() == null) {
                    context.setReportType(arg);
                }
                else  {
                    throw new IllegalArgumentException("Multiple report types detected. You can generate only 1 report at the same time");
                }
            }
            if (arg.equals("-emp")) {
                if (args.length > 3) {
                    context.setEmployeeName(args[i + 1] + " " + args[i + 2]);
                }
                else  {
                    throw new IllegalArgumentException("Employee name not specified");
                }
            }
            if (arg.equals("-project")) {
                context.setProjectName(args[++i]);
            }
            if (arg.equals("-n")) {
                context.setLimit(Integer.valueOf(args[++i]));
            }
            if (arg.equals("-path")) {
                context.setPath(args[++i]);
            }
            if (arg.equals("-from")) {
                context.setDateFrom(args[++i]);
            }
            if (arg.equals("-to")) {
                context.setDateTo(args[++i]);
            }
            if (arg.equals("-xlsx")) {
                context.setExportToXslx(args[++i]);
            }
        }

        return context;

    }
}
