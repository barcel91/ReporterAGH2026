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
    private final DataFormatter DATA_FORMATTER = new DataFormatter();
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private final String excelDate = "Data";
    private final String excelTask = "Zadanie";
    private final String excelDuration = "Czas";
    private final String emptyError = "empty";
    private final String invalidError = "invalid";

    public Collection<Task> readData(String s) {
        Path path = Paths.get(s);
        List<Task> tasks;

        try (Stream<Path> walk = Files.walk(path)) {
            List<Path> list = walk.filter(f -> f.getFileName().toString().endsWith(".xlsx")).toList();
            tasks = new ArrayList<>();

            for (Path location : list) {
                String user = location.getFileName().toString().replace(".xlsx", "");
                FileInputStream file = new FileInputStream(location.toString());
                Workbook workbook = new XSSFWorkbook(file);

                List<String> errors = new ArrayList<>();

                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    String project = sheet.getSheetName();

                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        Row row = sheet.getRow(j);
                        Cell dateCell = row.getCell(0);
                        Cell taskCell = row.getCell(1);
                        Cell durationCell = row.getCell(2);

                        if (dateCell == null && taskCell == null && durationCell == null) {
                            continue;
                        }

                        if (dateCell == null) {
                            errors.add(formatError(user, project, row, excelDate, emptyError));
                            continue;
                        }

                        if (taskCell == null) {
                            errors.add(formatError(user, project, row, excelTask, emptyError));
                            continue;
                        }

                        if (durationCell == null) {
                            errors.add(formatError(user, project, row, excelDuration, emptyError));
                            continue;
                        }

                        Task task = new Task();
                        task.setProject(project);
                        task.setUser(user);
                        task.setTask(getCellValue(taskCell));
                        try {
                            task.setData(LocalDate.parse(getCellValue(dateCell), DATE_FORMATTER));
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

        return tasks;
    }

    private String getCellValue(Cell cell) {
        return DATA_FORMATTER.formatCellValue(cell);
    }

    private String formatError(String file, String project, Row row, String col, String errorType) {
        return "%s file has %s %s at row %s in project %s.".formatted(file, errorType, col, row.getRowNum(), project);
    }

    public static void main(String[] args) {
        Collection<Task> tasks = new XlsxReader().readData("src/main/resources");
        tasks.forEach(System.out::println);
    }
}
