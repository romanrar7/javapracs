import java.util.ArrayList;

/**
 * Гардероб — агрегація одягу.
 */
public class Wardrobe {
    private String owner;
    private final ArrayList<Clothes> items;

    public Wardrobe(String owner) {
        this.owner = owner;
        this.items = new ArrayList<Clothes>();
    }

    public void add(Clothes clothes) {
        items.add(clothes);
    }

    public ArrayList<Clothes> getItems() {
        return items;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Wardrobe{owner='" + owner + "', items=" + items.size() + "}";
    }
}
