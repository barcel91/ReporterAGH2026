import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class XlsxReader {
    private static final DataFormatter DATA_FORMATTER = new DataFormatter();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final String DATE = "Data";
    private static final String TASK = "Zadanie";
    private static final String DURATION = "Czas";
    private static final String EMPTY_ERROR = "empty";
    private static final String INVALID_ERROR = "invalid";

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final TreeMap<String, ArrayList<String>> errorsTree = new TreeMap<>();
    private Path src;

    public ArrayList<Task> readData(String s) throws IOException {
        src = Paths.get(s);
        try (var walk = Files.walk(src)) {
            walk.filter(Files::isRegularFile).forEach(this::addTask);
        }
        errorsTree.forEach((k,v) -> {
            System.err.println(k);
            v.forEach(System.err::println);
        });

        return tasks;
    }

    private void addTask(Path path) {
        String dateFile = src.relativize(path).getParent().toString();
        String fileName = path.getFileName().toString();
        if (!fileName.endsWith(".xlsx")) {
            putError(dateFile, "Omitting %s file. Only .xslx files will be processed".formatted(fileName));
            return;
        }

        FileInputStream file;
        Workbook workbook;
        try {
            file = new FileInputStream(path.toString());
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String user = fileName.replaceAll("_", " ").replace(".xlsx", "");
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String project = sheet.getSheetName();

            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);
                ArrayList<String> localErrors = new ArrayList<>();
                if (row == null) {
                    continue;
                }
                Cell dateCell = row.getCell(0);
                Cell taskCell = row.getCell(1);
                Cell durationCell = row.getCell(2);

                if (isEmpty(dateCell) && isEmpty(taskCell) && isEmpty(durationCell)) {
                    continue;
                }

                if (isEmpty(dateCell)) {
                    localErrors.add(formatError(fileName, project, row, DATE, EMPTY_ERROR));
                }

                if (isEmpty(taskCell)) {
                    localErrors.add(formatError(fileName, project, row, TASK, EMPTY_ERROR));
                }

                if (isEmpty(durationCell)) {
                    localErrors.add(formatError(fileName, project, row, DURATION, EMPTY_ERROR));
                }

                if (!localErrors.isEmpty()) {
                    putError(dateFile, localErrors);
                    continue;
                }

                Task task = new Task();
                task.setProject(project);
                task.setUser(user);
                task.setTask(getCellValue(taskCell));
                try {
                    task.setData(LocalDate.parse(getCellValue(dateCell), DATE_FORMATTER));
                } catch (DateTimeException e) {
                    putError(dateFile, formatError(dateFile, project, row, DATE, INVALID_ERROR));
                    continue;
                }

                try {
                    task.setDuration(Double.parseDouble(getCellValue(durationCell)));
                } catch (NumberFormatException e) {
                    putError(dateFile, formatError(dateFile, project, row, DURATION, INVALID_ERROR));
                    continue;
                }
                tasks.add(task);
            }
        }
    }

    private static boolean isEmpty(Cell cell) {
        return cell == null || cell.getCellType() == CellType.BLANK || cell.toString().trim().isEmpty();
    }

    private static String getCellValue(Cell cell) {
        return DATA_FORMATTER.formatCellValue(cell);
    }

    private void putError(String key, String val) {
        putError(key, new ArrayList<>(List.of(val)));
    }

    private void putError(String key, ArrayList<String> val) {
        errorsTree.merge(key, val, (oldVal, newVal) -> {
                    oldVal.addAll(newVal);
                    return oldVal;
                }
        );
    }

    private static String formatError(String path, String project, Row row, String col, String errorType) {
        return "%s file has %s %s at row %s in project %s.".formatted(path, errorType, col, row.getRowNum(), project);
    }

    public static void main(String[] args) throws IOException {
        new XlsxReader().readData("src/main/resources");
    }
}
