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
                createTask("Piotr", "ProjectA", "",2.5),
                createTask("Mateusz", "ProjectB", "",1.5),
                createTask("Maciek", "ProjectA", "",4.0),
                createTask("Piotr", "ProjectB", "",2.5)
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
                createTask("Piotr", "ProjectA", "", 2.5),
                createTask("Maciek", "ProjectA", "",1.5),
                createTask("Piotr", "ProjectB", "",3.0)
        );


        Collection<ReportData> result = reportService.hoursByProject(tasks);


        assertEquals(2, result.size());

        Map<String, Double> hoursByProject = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getProject,
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

    @Test
    void shouldReturnTopTasksInProject() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 2.0),
                createTask("Mateusz", "ProjectA", "Task1", 3.0),
                createTask("Maciek", "ProjectA", "Task2", 4.0),
                createTask("Piotr", "ProjectB", "Task3", 10.0)
        );

        Collection<ReportData> result =
                reportService.tasksInProject(tasks, "ProjectA", 10);

        assertEquals(2, result.size());

        Map<String, Double> hoursByTask = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getTask,
                        ReportData::getDuration
                ));

        assertEquals(5.0, hoursByTask.get("Task1"));
        assertEquals(4.0, hoursByTask.get("Task2"));
    }

    @Test
    void shouldRespectLimitInTasksInProject() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 5.0),
                createTask("Piotr", "ProjectA", "Task2", 3.0),
                createTask("Piotr", "ProjectA", "Task3", 1.0)
        );

        Collection<ReportData> result =
                reportService.tasksInProject(tasks, "ProjectA", 2);

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyResultForTasksInProject() {

        Collection<ReportData> result =
                reportService.tasksInProject(List.of(), "ProjectA", 10);

        assertEquals(0, result.size());
    }

    @Test
    void shouldAggregateEmployeeProjectsAndCalculatePercentages() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 2.0),
                createTask("Piotr", "ProjectA", "Task2", 3.0),
                createTask("Piotr", "ProjectB", "Task3", 5.0),
                createTask("Maciek", "ProjectA", "Task4", 10.0)
        );

        Collection<ReportData> result =
                reportService.employeeProjects(tasks, "Piotr", 10);

        assertEquals(2, result.size());

        Map<String, ReportData> projects = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getProject,
                        report -> report
                ));

        assertEquals(5.0, projects.get("ProjectA").getDuration());
        assertEquals(5.0, projects.get("ProjectB").getDuration());

        assertEquals(50.0, projects.get("ProjectA").getPercentage(), 0.01);
        assertEquals(50.0, projects.get("ProjectB").getPercentage(), 0.01);
    }

    @Test
    void shouldRespectLimitInEmployeeProjects() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 5.0),
                createTask("Piotr", "ProjectB", "Task2", 3.0),
                createTask("Piotr", "ProjectC", "Task3", 1.0)
        );

        Collection<ReportData> result =
                reportService.employeeProjects(tasks, "Piotr", 2);

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyResultForEmployeeProjects() {

        Collection<ReportData> result =
                reportService.employeeProjects(List.of(), "Piotr", 10);

        assertEquals(0, result.size());
    }

    @Test
    void shouldAggregateEmployeeTasks() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 2.0),
                createTask("Piotr", "ProjectB", "Task1", 3.0),
                createTask("Piotr", "ProjectA", "Task2", 4.0),
                createTask("Maciek", "ProjectA", "Task1", 10.0)
        );

        Collection<ReportData> result =
                reportService.employeeTasks(tasks, "Piotr", 10);

        assertEquals(2, result.size());

        Map<String, Double> hoursByTask = result.stream()
                .collect(Collectors.toMap(
                        ReportData::getTask,
                        ReportData::getDuration
                ));

        assertEquals(5.0, hoursByTask.get("Task1"));
        assertEquals(4.0, hoursByTask.get("Task2"));
    }

    @Test
    void shouldRespectLimitInEmployeeTasks() {

        List<Task> tasks = List.of(
                createTask("Piotr", "ProjectA", "Task1", 5.0),
                createTask("Piotr", "ProjectA", "Task2", 3.0),
                createTask("Piotr", "ProjectA", "Task3", 1.0)
        );

        Collection<ReportData> result =
                reportService.employeeTasks(tasks, "Piotr", 2);

        assertEquals(2, result.size());
    }

    private Task createTask(
            String user,
            String project,
            String taskName,
            double duration) {

        Task task = new Task();
        task.setUser(user);
        task.setProject(project);
        task.setTask(taskName);
        task.setDuration(duration);
        return task;
    }
}