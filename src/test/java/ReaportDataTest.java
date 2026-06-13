import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaportDataTest {

    @Test
    void userSetterGetter() {
        ReportData reportData = new ReportData();
        reportData.setUser("Test");
        assertEquals("Test", reportData.getUser());
    }

    @Test
    void durationSetterGetter() {
        ReportData reportData = new ReportData();
        reportData.setDuration(54.87);
        assertEquals(54.87, reportData.getDuration());
    }

    @Test
    void taskSetterGetter() {
        ReportData reportData = new ReportData();
        reportData.setTask("Task");
        assertEquals("Task", reportData.getTask());
    }

    @Test
    void projectSetterGetter() {
        ReportData reportData = new ReportData();
        reportData.setProject("Projekt");
        assertEquals("Projekt", reportData.getProject());
    }

    @Test
    void percentageSetterGetter() {
        ReportData reportData = new ReportData();
        reportData.setPercentage(54.87);
        assertEquals(54.87, reportData.getPercentage());
    }
}
