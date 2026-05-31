import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Store store;
    private static Scanner scanner;
    private static DatabaseManager db;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Запуск: java -cp target/classes Main db.properties");
            return;
        }
        try {
            db = new DatabaseManager(args[0]);
        } catch (IOException e) {
            System.out.println("Конфіг не знайдено: " + e.getMessage());
            return;
        }
        scanner = new Scanner(System.in);
        store = FileService.load();
        System.out.println("=== Практична 12. JDBC ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук  2. Додати  3. Список  0. Вихід");
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
        System.out.println("1-назва 2-бренд 3-ціна");
        int c = InputHelper.readInt(scanner, "Критерій: ", 1);
        List<Clothes> res;
        if (c == 1) res = store.findByName(InputHelper.readLine(scanner, "Назва: "));
        else if (c == 2) res = store.findByBrand(InputHelper.readLine(scanner, "Бренд: "));
        else {
            double min = InputHelper.readDouble(scanner, "Мін: ", 0);
            double max = InputHelper.readDouble(scanner, "Макс: ", min);
            res = store.findByPriceRange(min, max);
        }
        for (int i = 0; i < res.size(); i++) System.out.println(res.get(i));
    }

    private static void addClothes() {
        try {
            String name = InputHelper.readLine(scanner, "Назва: ");
            String brand = InputHelper.readLine(scanner, "Бренд: ");
            double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
            String size = InputHelper.readLine(scanner, "Розмір: ");
            String color = InputHelper.readLine(scanner, "Колір: ");
            int q = InputHelper.readInt(scanner, "Кількість: ", 1);
            Pants c = new Pants(name, brand, price, size, color, SizeCategory.M, 32, 100);
            store.addNewClothes(c, q);
            db.insertClothes(c);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printAll() {
        ArrayList<Clothes> items = store.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i) + " | qty=" + store.getQuantity(items.get(i)));
        }
    }
}
