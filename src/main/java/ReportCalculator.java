import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportCalculator  {

    public Map<String, Double> hoursByEmployee(Collection<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getUser,
                        Collectors.summingDouble(Task::getDuration)
                ));
    }

    public Map<String, Double> hoursByProject(Collection<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getProject,
                        Collectors.summingDouble(Task::getDuration)
                ));
    }
}
