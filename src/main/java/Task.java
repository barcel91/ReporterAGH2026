import java.time.LocalDate;

public class Task {
    private String user;
    private String project;
    private LocalDate data;
    private String task;
    private double duration;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Task{" +
                "user='" + user + '\'' +
                ", project='" + project + '\'' +
                ", data=" + data +
                ", task='" + task + '\'' +
                ", duration=" + duration +
                '}';
    }
}
