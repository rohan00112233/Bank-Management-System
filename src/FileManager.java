import java.io.*;
import java.util.HashMap;

public class FileManager {

    private static final String FILE_NAME = "bank_data.dat";

    public static void saveData(HashMap<String, Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Account> loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            return (HashMap<String, Account>) obj;
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
