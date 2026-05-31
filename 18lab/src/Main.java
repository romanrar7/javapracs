import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Консольний драйвер (ЛР 18).
 */
public class Main {
  private static Store store;
  private static Scanner scanner;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    System.out.println("=== Магазин одягу. Практична 18 ===");
    store = FileService.loadStore();
    System.out.println("Завантажено: " + store);

    boolean running = true;
    while (running) {
      printMenu();
      int choice = InputHelper.readInt(scanner, "Ваш вибір: ", 0);
      try {
        switch (choice) {
          case 1:
            searchMenu();
            break;
          case 2:
            createObjectMenu();
            break;
          case 3:
            printAll();
            break;
          case 4:
            sortMenuLambda();
            break;
          case 5:
            findByUuidConsole();
            break;
          case 0:
            running = false;
            break;
          default:
            System.out.println("Немає такого пункту.");
        }
      } catch (InvalidFieldValueException e) {
        System.out.println("Помилка даних: " + e.getMessage());
      } catch (ObjectNotFoundException e) {
        System.out.println("Не знайдено: " + e.getMessage());
      }
    }

    FileService.saveStore(store);
    System.out.println("Дані збережено. До побачення!");
    scanner.close();
  }

  private static void printMenu() {
    System.out.println();
    System.out.println("1. Пошук об'єкта");
    System.out.println("2. Створити новий об'єкт");
    System.out.println("3. Вивести всі об'єкти");
    System.out.println("4. Вивести відсортовані (лямбда-компаратори)");
    System.out.println("5. Пошук за UUID");
    System.out.println("0. Завершити");
  }

  private static void printAll() {
    ArrayList<Clothes> items = store.getItems();
    if (items.isEmpty()) {
      System.out.println("Список порожній.");
      return;
    }
    for (int i = 0; i < items.size(); i++) {
      Clothes c = items.get(i);
      System.out.println((i + 1) + ") " + c + " | к-сть: " + store.getQuantity(c));
    }
  }

  private static void searchMenu() {
    System.out.println("--- Пошук ---");
    System.out.println("1. За назвою");
    System.out.println("2. За брендом");
    System.out.println("3. За діапазоном ціни");
    System.out.println("0. Назад");
    int c = InputHelper.readInt(scanner, "Вибір: ", 0);
    if (c == 0) {
      return;
    }
    List<Clothes> found;
    if (c == 1) {
      String part = InputHelper.readLine(scanner, "Частина назви: ");
      found = store.findByName(part);
    } else if (c == 2) {
      String brand = InputHelper.readLine(scanner, "Бренд: ");
      found = store.findByBrand(brand);
    } else if (c == 3) {
      double min = InputHelper.readDouble(scanner, "Мін. ціна: ", 0);
      double max = InputHelper.readDouble(scanner, "Макс. ціна: ", min);
      found = store.findByPriceRange(min, max);
    } else {
      System.out.println("Невірний пункт.");
      return;
    }
    printFound(found);
  }

  private static void printFound(List<Clothes> found) {
    if (found.isEmpty()) {
      System.out.println("Нічого не знайдено.");
      return;
    }
    for (int i = 0; i < found.size(); i++) {
      Clothes c = found.get(i);
      System.out.println((i + 1) + ") " + c);
    }
  }

  private static void createObjectMenu() {
    System.out.println("--- Тип одягу ---");
    System.out.println("1. Штани");
    System.out.println("2. Сорочка");
    System.out.println("3. Куртка");
    System.out.println("4. Спідниця");
    System.out.println("0. Назад");
    int type = InputHelper.readInt(scanner, "Вибір: ", 0);
    if (type == 0) {
      return;
    }
    try {
      Clothes clothes = readClothesByType(type);
      int qty = InputHelper.readInt(scanner, "Кількість: ", 1);
      store.addNewClothes(clothes, qty);
      System.out.println("Додано: " + clothes.shortInfo());
    } catch (InvalidFieldValueException e) {
      System.out.println("Не вдалося створити: " + e.getMessage());
    }
  }

  private static Clothes readClothesByType(int type) {
    String name = InputHelper.readLine(scanner, "Назва: ");
    String brand = InputHelper.readLine(scanner, "Бренд: ");
    double price = InputHelper.readDouble(scanner, "Ціна: ", 0);
    String size = InputHelper.readLine(scanner, "Розмір (S/M/L/XL): ");
    String color = InputHelper.readLine(scanner, "Колір: ");
    SizeCategory cat = SizeCategory.M;
    try {
      cat = SizeCategory.valueOf(size.toUpperCase());
    } catch (IllegalArgumentException e) {
      cat = SizeCategory.M;
    }

    if (type == 1) {
      int waist = InputHelper.readInt(scanner, "Обхват талії: ", 20);
      int length = InputHelper.readInt(scanner, "Довжина: ", 50);
      return new Pants(name, brand, price, size, color, cat, waist, length);
    }
    if (type == 2) {
      String collar = InputHelper.readLine(scanner, "Тип коміра: ");
      String sleeve = InputHelper.readLine(scanner, "Довжина рукава: ");
      return new Shirt(name, brand, price, size, color, cat, collar, sleeve);
    }
    if (type == 3) {
      String wpText = InputHelper.readLine(scanner, "Водонепроникна (так/ні): ");
      boolean wp = "так".equalsIgnoreCase(wpText);
      String lining = InputHelper.readLine(scanner, "Підкладка: ");
      return new Jacket(name, brand, price, size, color, cat, wp, lining);
    }
    if (type == 4) {
      int len = InputHelper.readInt(scanner, "Довжина (см): ", 20);
      String cut = InputHelper.readLine(scanner, "Фасон: ");
      return new Skirt(name, brand, price, size, color, cat, len, cut);
    }
    throw new InvalidFieldValueException("Невідомий тип");
  }

  private static void sortMenuLambda() {
    System.out.println("--- Сортування (лямбда) ---");
    System.out.println("1. За ціною");
    System.out.println("2. За брендом");
    System.out.println("3. За кольором");
    System.out.println("0. Назад");
    int c = InputHelper.readInt(scanner, "Вибір: ", 0);
    if (c == 1) {
      store.sortByPriceLambda();
    } else if (c == 2) {
      store.sortByBrandLambda();
    } else if (c == 3) {
      store.sortByColorLambda();
    } else if (c != 0) {
      System.out.println("Невірний пункт.");
      return;
    } else {
      return;
    }
    printAll();
  }

  private static void findByUuidConsole() {
    String text = InputHelper.readLine(scanner, "UUID: ");
    try {
      UUID uuid = UUID.fromString(text);
      Clothes found = store.findByUuid(uuid);
      if (found == null) {
        System.out.println("Об'єкт не знайдено.");
      } else {
        System.out.println(found);
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Некоректний формат UUID.");
    }
  }

  public static Store getStore() {
    return store;
  }
}
