import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.txt");
            processInput(writer);
            writer.println();
            System.out.println();
            BatchRunner.processConfig(writer);
        } catch (FileNotFoundException e) {
            System.out.println("Не вдалося створити output.txt");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static void processInput(PrintWriter writer) {
        File inputFile = new File("input.txt");
        Scanner scanner = null;

        try {
            scanner = new Scanner(inputFile);
            if (!scanner.hasNextInt()) {
                writeLine(writer, "input.txt: помилка формату");
                return;
            }

            int number = scanner.nextInt();
            if (number < -999 || number > 999) {
                writeLine(writer, "input.txt: число поза діапазоном");
                return;
            }

            String result = NumberToWords.toWords(number);
            writeLine(writer, "input.txt: " + number + " -> " + result);
        } catch (FileNotFoundException e) {
            writeLine(writer, "input.txt не знайдено");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void writeLine(PrintWriter writer, String line) {
        System.out.println(line);
        writer.println(line);
    }
}
