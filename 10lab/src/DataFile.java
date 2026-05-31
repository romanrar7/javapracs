import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataFile {
    private static final String FILE = "input.txt";

    public static void load(ArrayList<Clothes> items) {
        File f = new File(FILE);
        if (!f.exists()) return;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                Clothes c = parse(line);
                if (c != null) items.add(c);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        } finally {
            if (br != null) try { br.close(); } catch (IOException ignored) { }
        }
    }

    public static void save(ArrayList<Clothes> items) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(FILE));
            for (int i = 0; i < items.size(); i++) {
                bw.write(serialize(items.get(i)));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        } finally {
            if (bw != null) try { bw.close(); } catch (IOException ignored) { }
        }
    }

    private static Clothes parse(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 8) return null;
        try {
            if ("PANTS".equals(p[0])) {
                return new Pants(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        Integer.parseInt(p[6]), Integer.parseInt(p[7]));
            }
            if ("SHIRT".equals(p[0])) {
                return new Shirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M, p[6], p[7]);
            }
            if ("JACKET".equals(p[0])) {
                return new Jacket(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        "так".equalsIgnoreCase(p[6]), p[7]);
            }
            if ("SKIRT".equals(p[0])) {
                return new Skirt(p[1], p[2], Double.parseDouble(p[3]), p[4], p[5], SizeCategory.M,
                        Integer.parseInt(p[6]), p[7]);
            }
        } catch (RuntimeException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
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
