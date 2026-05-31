import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Store store;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        store = FileService.load();
        System.out.println("=== Практична 11. Магазин Store ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук");
            System.out.println("2. Додати одяг");
            System.out.println("3. Вивести всі");
            System.out.println("0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 1) search();
            else if (ch == 2) addClothes();
            else if (ch == 3) printAll();
            else if (ch == 0) run = false;
        }
        FileService.save(store);
        scanner.close();
    }

    private static void search() {
        System.out.println("1-назва 2-бренд 3-ціна 0-назад");
        int c = InputHelper.readInt(scanner, "Критерій: ", 0);
        if (c == 0) return;
        List<Clothes> res;
        if (c == 1) res = store.findByName(InputHelper.readLine(scanner, "Назва: "));
        else if (c == 2) res = store.findByBrand(InputHelper.readLine(scanner, "Бренд: "));
        else if (c == 3) {
            double min = InputHelper.readDouble(scanner, "Мін: ", 0);
            double max = InputHelper.readDouble(scanner, "Макс: ", min);
            res = store.findByPriceRange(min, max);
        } else return;
        if (res.isEmpty()) System.out.println("Не знайдено.");
        else for (int i = 0; i < res.size(); i++) System.out.println(res.get(i));
    }

    private static void addClothes() {
        System.out.println("1-штани 2-сорочка 3-куртка 4-спідниця");
        int t = InputHelper.readInt(scanner, "Тип: ", 1);
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            int q = InputHelper.readInt(scanner, "Кількість: ", 1);
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
            if (c != null) store.addNewClothes(c, q);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        ArrayList<Clothes> items = store.getItems();
        for (int i = 0; i < items.size(); i++) {
            Clothes c = items.get(i);
            System.out.println((i + 1) + ") " + c + " | qty=" + store.getQuantity(c));
        }
    }
}
