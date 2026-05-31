import java.util.Scanner;

public final class InputHelper {
    private InputHelper() {
    }

    public static int readInt(Scanner scanner, String prompt, int min) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min) {
                    return value;
                }
                System.out.println("Р§РёСЃР»Рѕ РјР°С” Р±СѓС‚Рё РЅРµ РјРµРЅС€Рµ " + min);
            } else {
                System.out.println("Р’РІРµРґС–С‚СЊ С†С–Р»Рµ С‡РёСЃР»Рѕ.");
                scanner.nextLine();
            }
        }
    }

    public static double readDouble(Scanner scanner, String prompt, double min) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > min) {
                    return value;
                }
                System.out.println("Р—РЅР°С‡РµРЅРЅСЏ РјР°С” Р±СѓС‚Рё Р±С–Р»СЊС€Рµ " + min);
            } else {
                System.out.println("Р’РІРµРґС–С‚СЊ С‡РёСЃР»Рѕ.");
                scanner.nextLine();
            }
        }
    }

    public static String readLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("РџРѕР»Рµ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј.");
        }
    }
}

