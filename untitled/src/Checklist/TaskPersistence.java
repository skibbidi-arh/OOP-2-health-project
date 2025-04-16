package Checklist;
import java.util.List;

public interface TaskPersistence {
    List<Task> loadTasks();
    void saveTasks(List<Task> tasks);
}
