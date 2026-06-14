public class App {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide params to generate report");
            return;
        }

        ReportOrchestrator reportOrchestrator = new ReportOrchestrator();
        reportOrchestrator.run(args);
    }
}

