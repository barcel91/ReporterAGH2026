import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// folder might not include files

// not errors
// all cells in row empty - just continue
// at least one cell empty - info to user

public class XlsxReader {
    private static final DataFormatter DATA_FORMATTER = new DataFormatter();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final String DATE = "Data";
    private static final String TASK = "Zadanie";
    private static final String DURATION = "Czas";
    private static final String EMPTY_ERROR = "empty";
    private static final String INVALID_ERROR = "invalid";

    private final List<Task> tasks = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();
    private Path src;

    public Collection<Task> readData(String s) throws IOException {
        src = Paths.get(s);

        long l = System.currentTimeMillis();

        try (var walk = Files.walk(src)) {
            walk.parallel().filter(Files::isRegularFile).forEach(this::addTask);
//            for (Path path : paths) {
//                Path relativePath = src.relativize(path);
//                if (!path.endsWith(".xlsx")) {
//                    errors.add("Not proceeding with %s file. Files in format other than .xslx are omitted".formatted(relativePath));
//                    continue;
//                }
//
//                FileInputStream file = new FileInputStream(path.toString());
//                Workbook workbook = new XSSFWorkbook(file);
//                String user = path.getFileName().toString().replace("_", " ").replace(".xlsx", "");
//                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//                    Sheet sheet = workbook.getSheetAt(i);
//                    String project = sheet.getSheetName();
//
//                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
//                        Row row = sheet.getRow(j);
//                        if (row == null) {
//                            continue;
//                        }
//                        Cell dateCell = row.getCell(0);
//                        Cell taskCell = row.getCell(1);
//                        Cell durationCell = row.getCell(2);
//
//                        if (isEmpty(dateCell) && isEmpty(taskCell) && isEmpty(durationCell)) {
//                            continue;
//                        }
//
//                        if (isEmpty(dateCell)) {
//                            errors.add(formatError(relativePath, project, row, DATE, EMPTY_ERROR));
//                            continue;
//                        }
//
//                        if (isEmpty(taskCell)) {
//                            errors.add(formatError(relativePath, project, row, TASK, EMPTY_ERROR));
//                            continue;
//                        }
//
//                        if (isEmpty(durationCell)) {
//                            errors.add(formatError(relativePath, project, row, DURATION, EMPTY_ERROR));
//                            continue;
//                        }
//
//                        Task task = new Task();
//                        task.setProject(project);
//                        task.setUser(user);
//                        task.setTask(getCellValue(taskCell));
//                        try {
//                            task.setData(LocalDate.parse(getCellValue(dateCell), DATE_FORMATTER));
//                        } catch (DateTimeException e) {
//                            errors.add(formatError(relativePath, project, row, DATE, INVALID_ERROR));
//                        }
//
//                        try {
//                            task.setDuration(Double.parseDouble(getCellValue(durationCell)));
//                        } catch (NumberFormatException e) {
//                            errors.add(formatError(relativePath, project, row, DURATION, INVALID_ERROR));
//                        }
//
//                        tasks.add(task);
//                    }
//                }
//            }
        }
        errors.forEach(System.err::println);
        System.out.println(System.currentTimeMillis()-l);

        return tasks;
    }

    private void addTask(Path path)  {
        Path relativePath = src.relativize(path);
        if (!path.toString().endsWith(".xlsx")) {
            errors.add("Not proceeding with %s file. Files in format other than .xslx are omitted".formatted(relativePath));
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

        String user = path.getFileName().toString()
                .replace("_", " ")
                .replace(".xlsx", "");

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String project = sheet.getSheetName();

            for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);
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
                    errors.add(formatError(relativePath, project, row, DATE, EMPTY_ERROR));
                    continue;
                }

                if (isEmpty(taskCell)) {
                    errors.add(formatError(relativePath, project, row, TASK, EMPTY_ERROR));
                    continue;
                }

                if (isEmpty(durationCell)) {
                    errors.add(formatError(relativePath, project, row, DURATION, EMPTY_ERROR));
                    continue;
                }

                Task task = new Task();
                task.setProject(project);
                task.setUser(user);
                task.setTask(getCellValue(taskCell));
                try {
                    task.setData(LocalDate.parse(getCellValue(dateCell), DATE_FORMATTER));
                } catch (DateTimeException e) {
                    errors.add(formatError(relativePath, project, row, DATE, INVALID_ERROR));
                    continue;
                }

                try {
                    task.setDuration(Double.parseDouble(getCellValue(durationCell)));
                } catch (NumberFormatException e) {
                    errors.add(formatError(relativePath, project, row, DURATION, INVALID_ERROR));
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

    private static String formatError(Path path, String project, Row row, String col, String errorType) {
        return "%s file has %s %s at row %s in project %s.".formatted(path.toString(), errorType, col, row.getRowNum(), project);
    }

    public static void main(String[] args) throws IOException {
        new XlsxReader().readData("src/main/resources");
    }
}
