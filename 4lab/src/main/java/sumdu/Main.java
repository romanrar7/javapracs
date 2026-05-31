package sumdu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Практична робота 4. Одяг ===");

        int count = readPositiveInt(scanner, "Скільки одиниць одягу додати? ");
        Clothes[] items = new Clothes[count];

        for (int i = 0; i < count; i++) {
            System.out.println("--- Одяг №" + (i + 1) + " ---");
            System.out.print("Назва: ");
            String name = scanner.nextLine().trim();
            System.out.print("Бренд: ");
            String brand = scanner.nextLine().trim();
            double price = readPositiveDouble(scanner, "Ціна: ");
            System.out.print("Розмір: ");
            String size = scanner.nextLine().trim();
            items[i] = new Clothes(name, brand, price, size);
        }

        System.out.println("\nСписок одягу:");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ") " + items[i]);
        }
        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                }
                System.out.println("Потрібне число більше 0.");
            } else {
                System.out.println("Введіть ціле число.");
                scanner.nextLine();
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                }
                System.out.println("Ціна має бути більше 0.");
            } else {
                System.out.println("Введіть число.");
                scanner.nextLine();
            }
        }
    }
}
