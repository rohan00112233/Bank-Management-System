import java.util.Random;

public class Utils {

    public static String generateAccountNumber() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return "AC" + number;
    }

    public static boolean isValidPin(int pin) {
        return pin >= 1000 && pin <= 9999;
    }
}
