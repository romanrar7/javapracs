import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Зчитування та запис даних у файл input.txt.
 */
public class FileService {
    private static final String FILE_NAME = "input.txt";

    public static Store loadStore() {
        Store store = new Store("Магазин", "Адреса не задана");
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return store;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                parseLine(store, line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу");
                }
            }
        }
        return store;
    }

    public static void saveStore(Store store) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write("STORE|" + store.getName() + "|" + store.getAddress());
            writer.newLine();
            ArrayList<Clothes> items = store.getItems();
            for (int i = 0; i < items.size(); i++) {
                Clothes c = items.get(i);
                int q = store.getQuantity(c);
                writer.write(serialize(c) + "|" + q);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу");
                }
            }
        }
    }

    private static void parseLine(Store store, String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 2) {
            System.out.println("Пропущено некоректний рядок: " + line);
            return;
        }
        String type = p[0];
        try {
            if ("STORE".equals(type) && p.length >= 3) {
                store.setName(p[1]);
                store.setAddress(p[2]);
                return;
            }
            int qty = 1;
            if (p.length >= 2) {
                try {
                    qty = Integer.parseInt(p[p.length - 1]);
                } catch (NumberFormatException ex) {
                    qty = 1;
                }
            }
            Clothes clothes = deserialize(p);
            if (clothes != null) {
                store.addNewClothes(clothes, qty);
            }
        } catch (RuntimeException e) {
            System.out.println("Помилка рядка '" + line + "': " + e.getMessage());
        }
    }

    private static SizeCategory mapSize(String sizeText) {
        try {
            return SizeCategory.valueOf(sizeText.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return SizeCategory.M;
        }
    }

    private static Clothes deserialize(String[] p) {
        String type = p[0];
        if ("PANTS".equals(type) && p.length >= 9) {
            SizeCategory cat = mapSize(p[4]);
            return new Pants(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], cat,
                    Integer.parseInt(p[6]), Integer.parseInt(p[7]));
        }
        if ("SHIRT".equals(type) && p.length >= 9) {
            SizeCategory cat = mapSize(p[4]);
            return new Shirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], cat, p[6], p[7]);
        }
        if ("JACKET".equals(type) && p.length >= 9) {
            SizeCategory cat = mapSize(p[4]);
            boolean wp = "так".equalsIgnoreCase(p[6]) || "true".equalsIgnoreCase(p[6]);
            return new Jacket(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], cat, wp, p[7]);
        }
        if ("SKIRT".equals(type) && p.length >= 9) {
            SizeCategory cat = mapSize(p[4]);
            return new Skirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], cat,
                    Integer.parseInt(p[6]), Integer.parseInt(p[7]));
        }
        System.out.println("Невідомий або короткий рядок типу: " + type);
        return null;
    }

    private static String serialize(Clothes c) {
        if (c instanceof Pants) {
            Pants p = (Pants) c;
            return "PANTS|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + p.getWaist() + "|" + p.getLength();
        }
        if (c instanceof Shirt) {
            Shirt s = (Shirt) c;
            return "SHIRT|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + s.getCollarType() + "|" + s.getSleeveLength();
        }
        if (c instanceof Jacket) {
            Jacket j = (Jacket) c;
            String wp = j.isWaterproof() ? "так" : "ні";
            return "JACKET|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + wp + "|" + j.getLining();
        }
        if (c instanceof Skirt) {
            Skirt sk = (Skirt) c;
            return "SKIRT|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + sk.getLengthCm() + "|" + sk.getCut();
        }
        return "";
    }
}
