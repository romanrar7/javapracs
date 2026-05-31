import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * РљРѕРЅСЃРѕР»СЊРЅРёР№ РґСЂР°Р№РІРµСЂ (Р›Р  18).
 */
public class Main {
  private static Store store;
  private static Scanner scanner;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    System.out.println("=== РњР°РіР°Р·РёРЅ РѕРґСЏРіСѓ. РџСЂР°РєС‚РёС‡РЅР° 18 ===");
    store = FileService.loadStore();
    System.out.println("Р—Р°РІР°РЅС‚Р°Р¶РµРЅРѕ: " + store);

    boolean running = true;
    while (running) {
      printMenu();
      int choice = InputHelper.readInt(scanner, "Р’Р°С€ РІРёР±С–СЂ: ", 0);
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
            System.out.println("РќРµРјР°С” С‚Р°РєРѕРіРѕ РїСѓРЅРєС‚Сѓ.");
        }
      } catch (IllegalArgumentException e) {
        System.out.println("РџРѕРјРёР»РєР° РґР°РЅРёС…: " + e.getMessage());
      } catch (IllegalStateException e) {
        System.out.println("РќРµ Р·РЅР°Р№РґРµРЅРѕ: " + e.getMessage());
      }
    }

    FileService.saveStore(store);
    System.out.println("Р”Р°РЅС– Р·Р±РµСЂРµР¶РµРЅРѕ. Р”Рѕ РїРѕР±Р°С‡РµРЅРЅСЏ!");
    scanner.close();
  }

  private static void printMenu() {
    System.out.println();
    System.out.println("1. РџРѕС€СѓРє РѕР±'С”РєС‚Р°");
    System.out.println("2. РЎС‚РІРѕСЂРёС‚Рё РЅРѕРІРёР№ РѕР±'С”РєС‚");
    System.out.println("3. Р’РёРІРµСЃС‚Рё РІСЃС– РѕР±'С”РєС‚Рё");
    System.out.println("4. Р’РёРІРµСЃС‚Рё РІС–РґСЃРѕСЂС‚РѕРІР°РЅС– (Р»СЏРјР±РґР°-РєРѕРјРїР°СЂР°С‚РѕСЂРё)");
    System.out.println("5. РџРѕС€СѓРє Р·Р° UUID");
    System.out.println("0. Р—Р°РІРµСЂС€РёС‚Рё");
  }

  private static void printAll() {
    ArrayList<Clothes> items = store.getItems();
    if (items.isEmpty()) {
      System.out.println("РЎРїРёСЃРѕРє РїРѕСЂРѕР¶РЅС–Р№.");
      return;
    }
    for (int i = 0; i < items.size(); i++) {
      Clothes c = items.get(i);
      System.out.println((i + 1) + ") " + c + " | Рє-СЃС‚СЊ: " + store.getQuantity(c));
    }
  }

  private static void searchMenu() {
    System.out.println("--- РџРѕС€СѓРє ---");
    System.out.println("1. Р—Р° РЅР°Р·РІРѕСЋ");
    System.out.println("2. Р—Р° Р±СЂРµРЅРґРѕРј");
    System.out.println("3. Р—Р° РґС–Р°РїР°Р·РѕРЅРѕРј С†С–РЅРё");
    System.out.println("0. РќР°Р·Р°Рґ");
    int c = InputHelper.readInt(scanner, "Р’РёР±С–СЂ: ", 0);
    if (c == 0) {
      return;
    }
    List<Clothes> found;
    if (c == 1) {
      String part = InputHelper.readLine(scanner, "Р§Р°СЃС‚РёРЅР° РЅР°Р·РІРё: ");
      found = store.findByName(part);
    } else if (c == 2) {
      String brand = InputHelper.readLine(scanner, "Р‘СЂРµРЅРґ: ");
      found = store.findByBrand(brand);
    } else if (c == 3) {
      double min = InputHelper.readDouble(scanner, "РњС–РЅ. С†С–РЅР°: ", 0);
      double max = InputHelper.readDouble(scanner, "РњР°РєСЃ. С†С–РЅР°: ", min);
      found = store.findByPriceRange(min, max);
    } else {
      System.out.println("РќРµРІС–СЂРЅРёР№ РїСѓРЅРєС‚.");
      return;
    }
    printFound(found);
  }

  private static void printFound(List<Clothes> found) {
    if (found.isEmpty()) {
      System.out.println("РќС–С‡РѕРіРѕ РЅРµ Р·РЅР°Р№РґРµРЅРѕ.");
      return;
    }
    for (int i = 0; i < found.size(); i++) {
      Clothes c = found.get(i);
      System.out.println((i + 1) + ") " + c);
    }
  }

  private static void createObjectMenu() {
    System.out.println("--- РўРёРї РѕРґСЏРіСѓ ---");
    System.out.println("1. РЁС‚Р°РЅРё");
    System.out.println("2. РЎРѕСЂРѕС‡РєР°");
    System.out.println("3. РљСѓСЂС‚РєР°");
    System.out.println("4. РЎРїС–РґРЅРёС†СЏ");
    System.out.println("0. РќР°Р·Р°Рґ");
    int type = InputHelper.readInt(scanner, "Р’РёР±С–СЂ: ", 0);
    if (type == 0) {
      return;
    }
    try {
      Clothes clothes = readClothesByType(type);
      int qty = InputHelper.readInt(scanner, "РљС–Р»СЊРєС–СЃС‚СЊ: ", 1);
      store.addNewClothes(clothes, qty);
      System.out.println("Р”РѕРґР°РЅРѕ: " + clothes.shortInfo());
    } catch (IllegalArgumentException e) {
      System.out.println("РќРµ РІРґР°Р»РѕСЃСЏ СЃС‚РІРѕСЂРёС‚Рё: " + e.getMessage());
    }
  }

  private static Clothes readClothesByType(int type) {
    String name = InputHelper.readLine(scanner, "РќР°Р·РІР°: ");
    String brand = InputHelper.readLine(scanner, "Р‘СЂРµРЅРґ: ");
    double price = InputHelper.readDouble(scanner, "Р¦С–РЅР°: ", 0);
    String size = InputHelper.readLine(scanner, "Р РѕР·РјС–СЂ (S/M/L/XL): ");
    String color = InputHelper.readLine(scanner, "РљРѕР»С–СЂ: ");
    SizeCategory cat = SizeCategory.M;
    try {
      cat = SizeCategory.valueOf(size.toUpperCase());
    } catch (IllegalArgumentException e) {
      cat = SizeCategory.M;
    }

    if (type == 1) {
      int waist = InputHelper.readInt(scanner, "РћР±С…РІР°С‚ С‚Р°Р»С–С—: ", 20);
      int length = InputHelper.readInt(scanner, "Р”РѕРІР¶РёРЅР°: ", 50);
      return new Pants(name, brand, price, size, color, cat, waist, length);
    }
    if (type == 2) {
      String collar = InputHelper.readLine(scanner, "РўРёРї РєРѕРјС–СЂР°: ");
      String sleeve = InputHelper.readLine(scanner, "Р”РѕРІР¶РёРЅР° СЂСѓРєР°РІР°: ");
      return new Shirt(name, brand, price, size, color, cat, collar, sleeve);
    }
    if (type == 3) {
      String wpText = InputHelper.readLine(scanner, "Р’РѕРґРѕРЅРµРїСЂРѕРЅРёРєРЅР° (С‚Р°Рє/РЅС–): ");
      boolean wp = "С‚Р°Рє".equalsIgnoreCase(wpText);
      String lining = InputHelper.readLine(scanner, "РџС–РґРєР»Р°РґРєР°: ");
      return new Jacket(name, brand, price, size, color, cat, wp, lining);
    }
    if (type == 4) {
      int len = InputHelper.readInt(scanner, "Р”РѕРІР¶РёРЅР° (СЃРј): ", 20);
      String cut = InputHelper.readLine(scanner, "Р¤Р°СЃРѕРЅ: ");
      return new Skirt(name, brand, price, size, color, cat, len, cut);
    }
    throw new IllegalArgumentException("РќРµРІС–РґРѕРјРёР№ С‚РёРї");
  }

  private static void sortMenuLambda() {
    System.out.println("--- РЎРѕСЂС‚СѓРІР°РЅРЅСЏ (Р»СЏРјР±РґР°) ---");
    System.out.println("1. Р—Р° С†С–РЅРѕСЋ");
    System.out.println("2. Р—Р° Р±СЂРµРЅРґРѕРј");
    System.out.println("3. Р—Р° РєРѕР»СЊРѕСЂРѕРј");
    System.out.println("0. РќР°Р·Р°Рґ");
    int c = InputHelper.readInt(scanner, "Р’РёР±С–СЂ: ", 0);
    if (c == 1) {
      store.sortByPriceLambda();
    } else if (c == 2) {
      store.sortByBrandLambda();
    } else if (c == 3) {
      store.sortByColorLambda();
    } else if (c != 0) {
      System.out.println("РќРµРІС–СЂРЅРёР№ РїСѓРЅРєС‚.");
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
        System.out.println("РћР±'С”РєС‚ РЅРµ Р·РЅР°Р№РґРµРЅРѕ.");
      } else {
        System.out.println(found);
      }
    } catch (IllegalArgumentException e) {
      System.out.println("РќРµРєРѕСЂРµРєС‚РЅРёР№ С„РѕСЂРјР°С‚ UUID.");
    }
  }

  public static Store getStore() {
    return store;
  }
}

