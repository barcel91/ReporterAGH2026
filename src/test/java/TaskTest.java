import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void userSetterGetter() {
        Task task = new Task();
        task.setUser("Test");
        assertEquals("Test", task.getUser());
    }

    @Test
    void projectSetterGetter() {
        Task task = new Task();
        task.setProject("Projekt");
        assertEquals("Projekt", task.getProject());
    }

    @Test
    void taskSetterGetter() {
        Task task = new Task();
        task.setTask("Task");
        assertEquals("Task", task.getTask());
    }

    @Test
    void durationSetterGetter() {
        Task task = new Task();
        task.setDuration(54.87);
        assertEquals(54.87, task.getDuration());
    }

    @Test
    void dataSetterGetter() {
        Task task = new Task();
        task.setData(LocalDate.of(2026, 6, 14));
        assertEquals(LocalDate.of(2026, 6, 14), task.getData());
    }

}


