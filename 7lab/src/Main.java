import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> items = new ArrayList<Clothes>();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("=== Практична 7. Наслідування ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Створити штани");
            System.out.println("2. Створити сорочку");
            System.out.println("3. Вивести всі");
            System.out.println("0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 1) createPants();
            else if (ch == 2) createShirt();
            else if (ch == 3) printAll();
            else if (ch == 0) run = false;
            else System.out.println("Невірно.");
        }
        scanner.close();
    }

    private static void createPants() {
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            int waist = InputHelper.readInt(scanner, "Талія: ", 20);
            int length = InputHelper.readInt(scanner, "Довжина: ", 50);
            items.add(new Pants(name, brand, price, size, color, SizeCategory.M, waist, length));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createShirt() {
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            String collar = InputHelper.readLine(scanner, "Комір: ");
            String sleeve = InputHelper.readLine(scanner, "Рукав: ");
            items.add(new Shirt(name, brand, price, size, color, SizeCategory.M, collar, sleeve));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        if (items.isEmpty()) {
            System.out.println("Порожньо.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
    }
}
