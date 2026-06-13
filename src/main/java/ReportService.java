import java.util.Collection;
import java.util.stream.Collectors;

public class ReportService {

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
}

