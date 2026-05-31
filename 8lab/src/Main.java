import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> items = new ArrayList<Clothes>();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("=== Практична 8 ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Створити об'єкт");
            System.out.println("2. Вивести всі");
            System.out.println("0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 1) createMenu();
            else if (ch == 2) printAll();
            else if (ch == 0) run = false;
            else System.out.println("Невірно.");
        }
        scanner.close();
    }

    private static void createMenu() {
        System.out.println("Тип: 1-штани 2-сорочка 3-куртка 4-спідниця 0-назад");
        int t = InputHelper.readInt(scanner, "Вибір: ", 0);
        if (t == 0) return;
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            Clothes c = null;
            if (t == 1) {
                int w = InputHelper.readInt(scanner, "Талія: ", 20);
                int l = InputHelper.readInt(scanner, "Довжина: ", 50);
                c = new Pants(name, brand, price, size, color, SizeCategory.M, w, l);
            } else if (t == 2) {
                c = new Shirt(name, brand, price, size, color, SizeCategory.M,
                        InputHelper.readLine(scanner, "Комір: "),
                        InputHelper.readLine(scanner, "Рукав: "));
            } else if (t == 3) {
                String wp = InputHelper.readLine(scanner, "Водонепроникна (так/ні): ");
                c = new Jacket(name, brand, price, size, color, SizeCategory.M,
                        "так".equalsIgnoreCase(wp), InputHelper.readLine(scanner, "Підкладка: "));
            } else if (t == 4) {
                int len = InputHelper.readInt(scanner, "Довжина см: ", 20);
                c = new Skirt(name, brand, price, size, color, SizeCategory.M, len,
                        InputHelper.readLine(scanner, "Фасон: "));
            }
            if (c != null) {
                items.add(c);
                System.out.println("Додано.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        if (items.isEmpty()) {
            System.out.println("Колекція порожня.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
    }
}
