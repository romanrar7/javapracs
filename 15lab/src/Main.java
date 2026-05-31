import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static Store store;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        store = FileService.load();
        System.out.println("=== Практична 15. Лямбда ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук  2. Додати  3. Список  4. Сортування  0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 4) sortMenuLambda();
            else if (ch == 3) printList(store.getItems());
            else if (ch == 0) run = false;
            else System.out.println("Див. ЛР13-14.");
        }
        FileService.save(store);
        scanner.close();
    }

    private static void sortMenuLambda() {
        System.out.println("1. Ціна  2. Бренд  3. Колір  0. Назад");
        int c = InputHelper.readInt(scanner, "Критерій: ", 0);
        if (c == 0) return;
        ArrayList<Clothes> list = new ArrayList<Clothes>(store.getItems());
        Comparator<Clothes> cmp;
        if (c == 1) {
            cmp = (o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice());
        } else if (c == 2) {
            cmp = (o1, o2) -> o1.getBrand().compareToIgnoreCase(o2.getBrand());
        } else if (c == 3) {
            cmp = (o1, o2) -> o1.getColor().compareToIgnoreCase(o2.getColor());
        } else {
            return;
        }
        Collections.sort(list, cmp);
        printList(list);
    }

    private static void printList(ArrayList<Clothes> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
    }
}
