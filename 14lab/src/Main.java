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
        System.out.println("=== Практична 14. Comparator ===");
        boolean run = true;
        while (run) {
            System.out.println("1. Пошук  2. Додати  3. Список  4. Сортування  0. Вихід");
            int ch = InputHelper.readInt(scanner, "Вибір: ", 0);
            if (ch == 4) sortMenu();
            else if (ch == 3) printList(store.getItems());
            else if (ch == 0) run = false;
            else System.out.println("Інші пункти як у ЛР13 (спрощено).");
        }
        FileService.save(store);
        scanner.close();
    }

    private static void sortMenu() {
        System.out.println("1. За ціною  2. За брендом  3. За кольором  0. Назад");
        int c = InputHelper.readInt(scanner, "Критерій: ", 0);
        if (c == 0) return;
        ArrayList<Clothes> list = new ArrayList<Clothes>(store.getItems());
        if (c == 1) {
            Comparator<Clothes> cmp = new Comparator<Clothes>() {
                @Override
                public int compare(Clothes o1, Clothes o2) {
                    return Double.compare(o1.getPrice(), o2.getPrice());
                }
            };
            Collections.sort(list, cmp);
        } else if (c == 2) {
            Comparator<Clothes> cmp = new Comparator<Clothes>() {
                @Override
                public int compare(Clothes o1, Clothes o2) {
                    return o1.getBrand().compareToIgnoreCase(o2.getBrand());
                }
            };
            Collections.sort(list, cmp);
        } else if (c == 3) {
            Comparator<Clothes> cmp = new Comparator<Clothes>() {
                @Override
                public int compare(Clothes o1, Clothes o2) {
                    return o1.getColor().compareToIgnoreCase(o2.getColor());
                }
            };
            Collections.sort(list, cmp);
        }
        printList(list);
    }

    private static void printList(ArrayList<Clothes> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
    }
}
