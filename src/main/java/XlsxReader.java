import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

// folder might not include files
// row can be empty

public class XlsxReader implements DataReader {
    @Override
    public Collection<Task> readData() {
        Path path = Paths.get("src/main/resources");
        try (Stream<Path> walk = Files.walk(path)) {
            List<Path> list = walk.filter(f -> f.getFileName().toString().endsWith(".xlsx")).toList();
            for (Path location : list) {
                FileInputStream file = new FileInputStream(location.toString());
                Workbook workbook = new XSSFWorkbook(file);
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                        Row row = sheet.getRow(j);
                        Cell firstCellNum = row.getCell(0);
                        System.out.printf("%s %s %s \n", row.getCell(0), row.getCell(1), row.getCell(2) );
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return List.of();
    }

    public static void main(String[] args) {
        new XlsxReader().readData();

    }
}
