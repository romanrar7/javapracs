import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> items = new ArrayList<Clothes>();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        DataFile.load(items);
        System.out.println("=== Практична 10. Пошук ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук");
            System.out.println("2. Створити");
            System.out.println("3. Вивести всі");
            System.out.println("0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 1) searchMenu();
            else if (ch == 2) createOne();
            else if (ch == 3) printAll();
            else if (ch == 0) {
                DataFile.save(items);
                run = false;
            }
        }
        scanner.close();
    }

    private static void searchMenu() {
        System.out.println("1-назва 2-бренд 3-ціна 0-назад");
        int c = InputHelper.readInt(scanner, "Критерій: ", 0);
        if (c == 0) return;
        List<Clothes> res;
        if (c == 1) res = findByName(InputHelper.readLine(scanner, "Частина назви: "));
        else if (c == 2) res = findByBrand(InputHelper.readLine(scanner, "Бренд: "));
        else if (c == 3) {
            double min = InputHelper.readDouble(scanner, "Мін: ", 0);
            double max = InputHelper.readDouble(scanner, "Макс: ", min);
            res = findByPrice(min, max);
        } else {
            System.out.println("Невірно.");
            return;
        }
        printFound(res);
    }

    private static List<Clothes> findByName(String part) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        String k = part.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().contains(k)) r.add(items.get(i));
        }
        return r;
    }

    private static List<Clothes> findByBrand(String brand) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        String k = brand.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBrand().toLowerCase().equals(k)) r.add(items.get(i));
        }
        return r;
    }

    private static List<Clothes> findByPrice(double min, double max) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        for (int i = 0; i < items.size(); i++) {
            double p = items.get(i).getPrice();
            if (p >= min && p <= max) r.add(items.get(i));
        }
        return r;
    }

    private static void printFound(List<Clothes> res) {
        if (res.isEmpty()) {
            System.out.println("Нічого не знайдено.");
            return;
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.println((i + 1) + ") " + res.get(i));
        }
    }

    private static void createOne() {
        System.out.println("1-штани 2-сорочка 3-куртка 4-спідниця 0-назад");
        int t = InputHelper.readInt(scanner, "Тип: ", 0);
        if (t == 0) return;
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            Clothes c = null;
            if (t == 1) {
                c = new Pants(name, brand, price, size, color, SizeCategory.M,
                        InputHelper.readInt(scanner, "Талія: ", 20),
                        InputHelper.readInt(scanner, "Довжина: ", 50));
            } else if (t == 2) {
                c = new Shirt(name, brand, price, size, color, SizeCategory.M,
                        InputHelper.readLine(scanner, "Комір: "),
                        InputHelper.readLine(scanner, "Рукав: "));
            } else if (t == 3) {
                String wp = InputHelper.readLine(scanner, "Водонепроникна (так/ні): ");
                c = new Jacket(name, brand, price, size, color, SizeCategory.M,
                        "так".equalsIgnoreCase(wp), InputHelper.readLine(scanner, "Підкладка: "));
            } else if (t == 4) {
                c = new Skirt(name, brand, price, size, color, SizeCategory.M,
                        InputHelper.readInt(scanner, "Довжина: ", 20),
                        InputHelper.readLine(scanner, "Фасон: "));
            }
            if (c != null) items.add(c);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
    }
}
