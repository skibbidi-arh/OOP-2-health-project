package medicine;

import java.util.*;

public class MedicineService {
    private final List<Medicine> medicines;
    private final MedicineRepository repository;

    public MedicineService(MedicineRepository repository) {
        this.repository = repository;
        this.medicines = repository.loadMedicines();
    }

    public List<Medicine> getAllMedicines() {
        return new ArrayList<>(medicines);
    }

    public boolean addMedicine(String name, List<String> times) {
        if (findMedicineByName(name) != null) return false;
        medicines.add(new Medicine(name, times));
        repository.saveMedicines(medicines);
        return true;
    }

    public boolean removeMedicine(int index) {
        if (index < 0 || index >= medicines.size()) return false;
        medicines.remove(index);
        repository.saveMedicines(medicines);
        return true;
    }

    public Medicine findMedicineByName(String name) {
        return medicines.stream()
                .filter(med -> med.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
