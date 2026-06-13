import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportServiceTest {

    private final ReportService reportService = new ReportService();

    @Test
    void shouldAggregateHoursByEmployee() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", 2.5),
                createTask("Mateusz", "ProjectB", 1.5),
                createTask("Maciek", "ProjectA", 4.0),
                createTask("Piotr", "ProjectB", 2.5)
        );


        Collection<ReportData> result = reportService.hoursByEmployee(tasks);


        assertEquals(3, result.size());

        Map<String, Double> hoursByUser = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getUser,
                        ReportData::getDuration
                ));

        assertEquals(5.0, hoursByUser.get("Piotr"));
        assertEquals(4.0, hoursByUser.get("Maciek"));
    }

    @Test
    void shouldAggregateHoursByProject() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", 2.5),
                createTask("Maciek", "ProjectA", 1.5),
                createTask("Piotr", "ProjectB", 3.0)
        );


        Collection<ReportData> result = reportService.hoursByProject(tasks);


        assertEquals(2, result.size());

        Map<String, Double> hoursByProject = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getTask,
                        ReportData::getDuration
                ));

        assertEquals(4.0, hoursByProject.get("ProjectA"));
        assertEquals(3.0, hoursByProject.get("ProjectB"));
    }

    @Test
    void shouldReturnEmptyResultForHoursByEmployee() {
        Collection<ReportData> result = reportService.hoursByEmployee(List.of());
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnEmptyResultForHoursByProject() {
        Collection<ReportData> result = reportService.hoursByProject(List.of());
        assertEquals(0, result.size());
    }

    private Task createTask(String user, String project, double duration) {
        Task task = new Task();
        task.setUser(user);
        task.setProject(project);
        task.setDuration(duration);
        return task;
    }
}