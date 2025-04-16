package Checklist;

public class Task {
    private String description;
    private boolean completed;

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public void toggleStatus() {
        completed = !completed;
    }

    public String toDisplayString() {
        return (completed ? "[✔] " : "[ ] ") + description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
