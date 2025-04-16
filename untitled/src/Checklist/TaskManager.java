package Checklist;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private final TaskPersistence persistence;

    public TaskManager(TaskPersistence persistence) {
        this.persistence = persistence;
        this.tasks = new ArrayList<>();
        this.tasks.addAll(persistence.loadTasks());
    }

    public void addTask(String description) {
        tasks.add(new Task(description, false));
        persistence.saveTasks(tasks);
    }

    public void removeTask(int index) {
        tasks.remove(index);
        persistence.saveTasks(tasks);
    }

    public void toggleTask(int index) {
        tasks.get(index).toggleStatus();
        persistence.saveTasks(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
