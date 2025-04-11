package medicine;

import java.util.*;

public class Medicine {
    private final String name;
    private final List<String> times;

    public Medicine(String name, List<String> times) {
        this.name = name.trim();
        this.times = new ArrayList<>(times);
    }

    public String getName() {
        return name;
    }

    public List<String> getTimes() {
        return Collections.unmodifiableList(times);
    }

    public String toFileString() {
        return name + ";" + String.join(",", times);
    }

    public static Medicine fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length != 2) return null;
        String name = parts[0];
        List<String> times = Arrays.asList(parts[1].split(","));
        return new Medicine(name, times);
    }

    public String toDisplayString() {
        return name + " - Times: " + String.join(", ", times);
    }
}

