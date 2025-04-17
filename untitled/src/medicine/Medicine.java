package medicine;

import java.util.*;

public class Medicine {
    private final String name;
    private final List<String> times;
    private final Set<String> weekdays;

    public Medicine(String name, List<String> times, Set<String> weekdays) {
        this.name = name.trim();
        this.times = new ArrayList<>(times);
        this.weekdays = new HashSet<>(weekdays);
    }

    public String getName() {
        return name;
    }

    public List<String> getTimes() {
        return Collections.unmodifiableList(times);
    }

    public Set<String> getWeekdays() {
        return Collections.unmodifiableSet(weekdays);
    }

    public String toFileString() {
        return name + ";" + String.join(",", times) + ";" + String.join(",", weekdays);
    }

    public static Medicine fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3) return null;
        String name = parts[0];
        List<String> times = Arrays.asList(parts[1].split(","));
        Set<String> days = new HashSet<>(Arrays.asList(parts[2].split(",")));
        return new Medicine(name, times, days);
    }

    public String toDisplayString() {
        return name + " - Times: " + String.join(", ", times) +
                " | Days: " + String.join(", ", weekdays);
    }
}
