import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class XlsxReaderTest {
    private XlsxReader xlsxReader;
    private final String src = "src/test/resources";
    private final String standardFiles = pathToString("standardFiles");
    private final String emptyFolder = pathToString("emptyFolder");
    private final String emptyCells = pathToString("emptyCells");
    private final String nonStandardFiles = pathToString("nonStandardFiles");
    private final String invalidFolderStructure = pathToString("invalidFolderStructure");
    private final String invalidCells = pathToString("invalidCells");

    private String pathToString(String path) {
        return Path.of(src, path).toString();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void assertTasksAreNotEmpty(String path, int length) throws IOException {
        ArrayList<Task> tasks = xlsxReader.readData(path);
        assertAll(
                () -> assertNotNull(tasks),
                () -> assertFalse(tasks.isEmpty()),
                () -> assertEquals(length, tasks.size()),
                () -> assertTrue(tasks.stream().noneMatch(Objects::isNull)),
                () -> assertFalse(tasks.stream().anyMatch(t ->
                        isEmpty(t.getUser()) || isEmpty(t.getProject()) || t.getData() == null ||
                                isEmpty(t.getTask()) || t.getDuration() <= 0))
        );
    }

    private void assertTasksAreEmpty(String path) throws IOException {
        ArrayList<Task> tasks = xlsxReader.readData(path);
        assertAll(
                () -> assertNotNull(tasks),
                () -> assertTrue(tasks.isEmpty())
        );
    }

    @BeforeEach
    void setUp() {
        xlsxReader = new XlsxReader();
    }

    @Test
    public void shouldReturnTaskForStandardFiles() throws IOException {
        assertTasksAreNotEmpty(standardFiles, 10);
    }

    @Test
    public void shouldReturnTasksForNonStandardFiles() throws IOException {
        assertTasksAreNotEmpty(nonStandardFiles, 6);
    }

    @Test
    public void shouldReturnTasksForInvalidFolderStructure() throws IOException {
        assertTasksAreNotEmpty(invalidFolderStructure, 10);
    }

    @Test
    public void shouldNotReturnTasksForEmptyFolder() throws IOException {
        assertTasksAreEmpty(emptyFolder);
    }

    @Test
    public void shouldNotReturnTasksForEmptyCells() throws IOException {
        assertTasksAreEmpty(emptyCells);
    }

    @Test
    public void shouldNotReturnTasksForInvalidCells() throws IOException {
        assertTasksAreEmpty(invalidCells);
    }

}