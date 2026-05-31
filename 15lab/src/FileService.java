import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    private static final String FILE = "input.txt";

    public static Store load() {
        Store store = new Store("Магазин", "м. Суми");
        File f = new File(FILE);
        if (!f.exists()) return store;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] p = line.split("\\|", -1);
                if ("STORE".equals(p[0]) && p.length >= 3) {
                    store.setName(p[1]);
                    store.setAddress(p[2]);
                    continue;
                }
                Clothes c = parseItem(p);
                if (c != null) {
                    int q = 1;
                    try { q = Integer.parseInt(p[p.length - 1]); } catch (Exception ignored) { }
                    store.addNewClothes(c, q);
                }
            }
        } catch (IOException e) {
            System.out.println("Читання: " + e.getMessage());
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignored) { }
        }
        return store;
    }

    public static void save(Store store) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(FILE));
            bw.write("STORE|" + store.getName() + "|" + store.getAddress());
            bw.newLine();
            ArrayList<Clothes> items = store.getItems();
            for (int i = 0; i < items.size(); i++) {
                Clothes c = items.get(i);
                bw.write(toLine(c) + "|" + store.getQuantity(c));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Запис: " + e.getMessage());
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignored) { }
        }
    }

    private static Clothes parseItem(String[] p) {
        try {
            if ("PANTS".equals(p[0]) && p.length >= 8) {
                return new Pants(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        Integer.parseInt(p[6]), Integer.parseInt(p[7]));
            }
            if ("SHIRT".equals(p[0]) && p.length >= 8) {
                return new Shirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M, p[6], p[7]);
            }
            if ("JACKET".equals(p[0]) && p.length >= 8) {
                return new Jacket(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        "так".equalsIgnoreCase(p[6]), p[7]);
            }
            if ("SKIRT".equals(p[0]) && p.length >= 8) {
                return new Skirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        Integer.parseInt(p[6]), Integer.parseInt(p[7]));
            }
        } catch (RuntimeException e) {
            System.out.println("Рядок: " + e.getMessage());
        }
        return null;
    }

    private static String toLine(Clothes c) {
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
            return "JACKET|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + (j.isWaterproof() ? "так" : "ні") + "|" + j.getLining();
        }
        if (c instanceof Skirt) {
            Skirt sk = (Skirt) c;
            return "SKIRT|" + c.getName() + "|" + c.getBrand() + "|" + c.getPrice() + "|"
                    + c.getSize() + "|" + c.getColor() + "|" + sk.getLengthCm() + "|" + sk.getCut();
        }
        return "";
    }
}
