import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Store store;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        store = FileService.load();
        System.out.println("=== Практична 13. Comparable ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук  2. Додати  3. Список  4. Відсортований список  0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 1) search();
            else if (ch == 2) addSimple();
            else if (ch == 3) printAll();
            else if (ch == 4) printSorted();
            else if (ch == 0) run = false;
        }
        FileService.save(store);
        scanner.close();
    }

    private static void printSorted() {
        ArrayList<Clothes> copy = new ArrayList<Clothes>(store.getItems());
        Collections.sort(copy);
        if (copy.isEmpty()) {
            System.out.println("Порожньо.");
            return;
        }
        for (int i = 0; i < copy.size(); i++) {
            System.out.println((i + 1) + ") " + copy.get(i));
        }
    }

    private static void search() {
        List<Clothes> res = store.findByName(InputHelper.readLine(scanner, "Назва: "));
        if (res.isEmpty()) System.out.println("Не знайдено.");
        else for (int i = 0; i < res.size(); i++) System.out.println(res.get(i));
    }

    private static void addSimple() {
        try {
            Pants c = new Pants(InputHelper.readLine(scanner, "Назва: "),
                    InputHelper.readLine(scanner, "Бренд: "),
                    InputHelper.readDouble(scanner, "Ціна: ", 0),
                    InputHelper.readLine(scanner, "Розмір: "),
                    InputHelper.readLine(scanner, "Колір: "),
                    SizeCategory.M, 32, 100);
            store.addNewClothes(c, InputHelper.readInt(scanner, "Кількість: ", 1));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAll() {
        ArrayList<Clothes> items = store.getItems();
        for (int i = 0; i < items.size(); i++) System.out.println(items.get(i));
    }
}
