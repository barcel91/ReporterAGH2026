import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class XlsxReaderTest {
    private XlsxReader xlsxReader;
    private Path src = Path.of("src/test/resources");
    private String standardFiles = src.resolve("standardFiles").toString();
    private String emptyFolder = src.resolve("emptyFolder").toString();
    private String emptyCells = src.resolve("emptyCells").toString();
    private String nonStandardFiles = src.resolve("nonStandardFiles").toString();
    private String invalidFolderStructure = src.resolve("invalidFolderStructure").toString();


    @BeforeEach
    void setUp() {
        xlsxReader = new XlsxReader();
    }

    @Test
    public void shouldReturnTaskForStandardFiles() throws IOException {
        ArrayList<Task> tasks = xlsxReader.readData(standardFiles);

        assertAll(
                () -> assertFalse(tasks.isEmpty()),
                () -> assertEquals(11, tasks.size())
        );
    }

}