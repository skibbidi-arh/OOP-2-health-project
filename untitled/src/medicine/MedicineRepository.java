package medicine;

import java.io.*;
import java.util.*;

public class MedicineRepository {
    private final String fileName = "medicines.txt";

    public MedicineRepository() {}

    public List<Medicine> loadMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return medicines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Medicine med = Medicine.fromFileString(line);
                if (med != null) medicines.add(med);
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
        return medicines;
    }

    public void saveMedicines(List<Medicine> medicines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Medicine med : medicines) {
                writer.println(med.toFileString());
            }
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
}
