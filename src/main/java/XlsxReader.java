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
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

// folder might not include files

// not errors
// all cells in row empty - just continue
// at least one cell empty - info to user

public class XlsxReader {
    private static final  DataFormatter DATA_FORMATTER = new DataFormatter();
    private static final  DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final  String DATE = "Data";
    private static final  String TASK = "Zadanie";
    private static final  String DURATION = "Czas";
    private static final  String EMPTY_ERROR = "empty";
    private static final  String INVALID_ERROR = "invalid";

    public Collection<Task> readData(String s) {
        Path path = Paths.get(s);
        List<Task> tasks = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(path)) {
            List<Path> list = walk.filter(f -> f.getFileName().toString().endsWith(".xlsx")).toList();

            for (Path location : list) {
                String user = location.getFileName().toString().replace(".xlsx", "");
                FileInputStream file = new FileInputStream(location.toString());
                Workbook workbook = new XSSFWorkbook(file);

                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    String project = sheet.getSheetName();

                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        Row row = sheet.getRow(j);
                        Cell date = row.getCell(0);
                        Cell taskCell = row.getCell(1);
                        Cell durationCell = row.getCell(2);

                        if (date == null && taskCell == null && durationCell == null) {
                            continue;
                        }

                        if (date == null) {
                            errors.add(formatError(user, project, row, DATE, EMPTY_ERROR));
                            continue;
                        }

                        if (taskCell == null) {
                            errors.add(formatError(user, project, row, TASK, EMPTY_ERROR));
                            continue;
                        }

                        if (durationCell == null) {
                            errors.add(formatError(user, project, row, DURATION, EMPTY_ERROR));
                            continue;
                        }

                        Task task = new Task();
                        task.setProject(project);
                        task.setUser(user);
                        task.setTask(getCellValue(taskCell));
                        try {
                            task.setData(LocalDate.parse(getCellValue(date), DATE_FORMATTER));
                        } catch (DateTimeException e) {
                            throw new RuntimeException();
                        }

                        try {
                            task.setDuration(Double.parseDouble(getCellValue(durationCell)));
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }

                        tasks.add(task);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        errors.forEach(System.err::println);
        return tasks;
    }

    private String getCellValue(Cell cell) {
        return DATA_FORMATTER.formatCellValue(cell);
    }

    private String formatError(String file, String project, Row row, String col, String errorType) {
        return "%s file has %s %s at row %s in project %s.".formatted(file, errorType, col, row.getRowNum(), project);
    }
}
