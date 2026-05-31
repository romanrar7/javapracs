import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BatchRunner {
    public static void processConfig(PrintWriter writer) {
        File file = new File("config.txt");
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
            if (!scanner.hasNextInt()) {
                writeLine(writer, "config.txt: помилка формату (нема кількості наборів)");
                return;
            }

            int count = scanner.nextInt();
            if (count < 0) {
                writeLine(writer, "config.txt: помилка, кількість наборів не може бути від'ємною");
                return;
            }

            writeLine(writer, "Обробка config.txt:");

            int i = 1;
            while (i <= count) {
                if (!scanner.hasNextInt()) {
                    writeLine(writer, "Набір " + i + ": помилка формату");
                    i++;
                    continue;
                }

                int number = scanner.nextInt();
                String line = buildLine(i, number);
                writeLine(writer, line);
                i++;
            }
        } catch (FileNotFoundException e) {
            writeLine(writer, "config.txt не знайдено");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static String buildLine(int index, int number) {
        if (number < -999 || number > 999) {
            return "Набір " + index + ": " + number + " -> помилка діапазону";
        }
        return "Набір " + index + ": " + number + " -> " + NumberToWords.toWords(number);
    }

    private static void writeLine(PrintWriter writer, String line) {
        System.out.println(line);
        writer.println(line);
    }
}
