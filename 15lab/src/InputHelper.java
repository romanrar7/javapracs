import java.util.Scanner;

public final class InputHelper {
    private InputHelper() {
    }

    public static int readInt(Scanner scanner, String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min) {
                    return value;
                }
                System.out.println("Число має бути не менше " + min);
            } else {
                System.out.println("Введіть ціле число.");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(Scanner scanner, String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > min) {
                    return value;
                }
                System.out.println("Значення має бути більше " + min);
            } else {
                System.out.println("Введіть число.");
                scanner.nextLine();
            }
        }
    }

    public static String readLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("Поле не може бути порожнім.");
        }
    }
}
