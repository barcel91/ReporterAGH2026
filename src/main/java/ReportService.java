import java.util.Collection;
import java.util.stream.Collectors;

public class ReportService {

    public Collection<ReportData> hoursByEmployee(Collection<Task> tasks, int limit) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getUser,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Collection<ReportData> hoursByEmployee(Collection<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getUser,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .collect(Collectors.toList());
    }

    public Collection<ReportData> hoursByProject(Collection<Task> tasks, int limit) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getProject,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setProject(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Collection<ReportData> hoursByProject(Collection<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getProject,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setProject(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .collect(Collectors.toList());
    }

    public Collection<ReportData> employeeProjects(
            Collection<Task> tasks,
            String user,
            int limit) {

        double totalHours = tasks.stream()
                .filter(task -> user.equals(task.getUser()))
                .mapToDouble(Task::getDuration)
                .sum();

        return tasks.stream()
                .filter(task -> user.equals(task.getUser()))
                .collect(Collectors.groupingBy(
                        Task::getProject,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(user);
                    data.setProject(entry.getKey());
                    data.setDuration(entry.getValue());

                    if (totalHours > 0) {
                        data.setPercentage(
                                entry.getValue() * 100.0 / totalHours
                        );
                    }

                    return data;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Collection<ReportData> employeeProjects(
            Collection<Task> tasks,
            String user) {

        double totalHours = tasks.stream()
                .filter(task -> user.equals(task.getUser().toLowerCase()))
                .mapToDouble(Task::getDuration)
                .sum();

        return tasks.stream()
                .filter(task -> user.equals(task.getUser()))
                .collect(Collectors.groupingBy(
                        Task::getProject,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(user);
                    data.setProject(entry.getKey());
                    data.setDuration(entry.getValue());

                    if (totalHours > 0) {
                        data.setPercentage(
                                entry.getValue() * 100.0 / totalHours
                        );
                    }

                    return data;
                })
                .collect(Collectors.toList());
    }

    public Collection<ReportData> tasksInProject(
            Collection<Task> tasks,
            String project,
            int limit) {

        return tasks.stream()
                .filter(task -> project.equals(task.getProject()))
                .collect(Collectors.groupingBy(
                        Task::getTask,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setProject(project);
                    data.setTask(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .sorted((a, b) ->
                        Double.compare(
                                b.getDuration(),
                                a.getDuration()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Collection<ReportData> tasksInProject(
            Collection<Task> tasks,
            String project) {

        return tasks.stream()
                .filter(task -> project.equals(task.getProject()))
                .collect(Collectors.groupingBy(
                        Task::getTask,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setProject(project);
                    data.setTask(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .sorted((a, b) ->
                        Double.compare(
                                b.getDuration(),
                                a.getDuration()))
                .collect(Collectors.toList());
    }

    public Collection<ReportData> employeeTasks(
            Collection<Task> tasks,
            String user,
            int limit) {

        return tasks.stream()
                .filter(task -> user.equals(task.getUser()))
                .collect(Collectors.groupingBy(
                        Task::getTask,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(user);
                    data.setTask(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .sorted((a, b) ->
                        Double.compare(
                                b.getDuration(),
                                a.getDuration()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Collection<ReportData> employeeTasks(
            Collection<Task> tasks,
            String user) {

        return tasks.stream()
                .filter(task -> user.equals(task.getUser()))
                .collect(Collectors.groupingBy(
                        Task::getTask,
                        Collectors.summingDouble(Task::getDuration)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    ReportData data = new ReportData();
                    data.setUser(user);
                    data.setTask(entry.getKey());
                    data.setDuration(entry.getValue());
                    return data;
                })
                .sorted((a, b) ->
                        Double.compare(
                                b.getDuration(),
                                a.getDuration()))
                .collect(Collectors.toList());
    }
}

