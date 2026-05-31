import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> list = new ArrayList<Clothes>();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("=== Практична 5. Меню одягу ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Створити новий об'єкт");
            System.out.println("2. Вивести всі об'єкти");
            System.out.println("0. Завершити");
            int ch = readInt("Вибір: ", 0);
            if (ch == 1) {
                createClothes();
            } else if (ch == 2) {
                printAll();
            } else if (ch == 0) {
                run = false;
            } else {
                System.out.println("Невірний пункт.");
            }
        }
        scanner.close();
    }

    private static void createClothes() {
        try {
            System.out.print("Назва: ");
            String name = scanner.nextLine().trim();
            System.out.print("Бренд: ");
            String brand = scanner.nextLine().trim();
            double price = readDouble("Ціна: ", 0);
            System.out.print("Розмір: ");
            String size = scanner.nextLine().trim();
            System.out.print("Колір: ");
            String color = scanner.nextLine().trim();
            list.add(new Clothes(name, brand, price, size, color));
            System.out.println("Додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        if (list.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
    }

    private static int readInt(String p, int min) {
        while (true) {
            System.out.print(p);
            if (scanner.hasNextInt()) {
                int v = scanner.nextInt();
                scanner.nextLine();
                if (v >= min) return v;
            } else scanner.nextLine();
            System.out.println("Введіть число.");
        }
    }

    private static double readDouble(String p, double min) {
        while (true) {
            System.out.print(p);
            if (scanner.hasNextDouble()) {
                double v = scanner.nextDouble();
                scanner.nextLine();
                if (v > min) return v;
            } else scanner.nextLine();
            System.out.println("Введіть коректне число.");
        }
    }
}
