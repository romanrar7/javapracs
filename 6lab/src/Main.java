import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Практична 6 ===");
        Wardrobe wardrobe = new Wardrobe("Студент");

        Clothes original = new Clothes("Футболка", "Nike", 399, "M", "білий", SizeCategory.M);
        wardrobe.add(original);
        Clothes copy = new Clothes(original);
        wardrobe.add(copy);

        System.out.println("Створено об'єктів: " + Clothes.getCount());
        System.out.println(wardrobe);
        for (int i = 0; i < wardrobe.getItems().size(); i++) {
            System.out.println((i + 1) + ") " + wardrobe.getItems().get(i));
        }
        sc.close();
    }
}
