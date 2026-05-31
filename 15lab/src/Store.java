import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private String name;
    private String address;
    private final ArrayList<Clothes> items;
    private final Map<Clothes, Integer> quantities;

    public Store(String name, String address) {
        setName(name);
        setAddress(address);
        this.items = new ArrayList<Clothes>();
        this.quantities = new HashMap<Clothes, Integer>();
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Порожня назва");
        this.name = name.trim();
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) throw new IllegalArgumentException("Порожня адреса");
        this.address = address.trim();
    }

    public ArrayList<Clothes> getItems() { return items; }

    public int getQuantity(Clothes c) {
        Integer q = quantities.get(c);
        return q == null ? 0 : q.intValue();
    }

    public void addNewClothes(Clothes clothes, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Кількість > 0");
        for (int i = 0; i < items.size(); i++) {
            Clothes ex = items.get(i);
            if (sameProduct(ex, clothes)) {
                quantities.put(ex, Integer.valueOf(getQuantity(ex) + quantity));
                return;
            }
        }
        items.add(clothes);
        quantities.put(clothes, Integer.valueOf(quantity));
    }

    private boolean sameProduct(Clothes a, Clothes b) {
        return a.getClass() == b.getClass()
                && a.getName().equalsIgnoreCase(b.getName())
                && a.getBrand().equalsIgnoreCase(b.getBrand())
                && a.getSize().equalsIgnoreCase(b.getSize());
    }

    public List<Clothes> findByName(String part) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        String k = part.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().contains(k)) r.add(items.get(i));
        }
        return r;
    }

    public List<Clothes> findByBrand(String brand) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBrand().equalsIgnoreCase(brand)) r.add(items.get(i));
        }
        return r;
    }

    public List<Clothes> findByPriceRange(double min, double max) {
        ArrayList<Clothes> r = new ArrayList<Clothes>();
        for (int i = 0; i < items.size(); i++) {
            double p = items.get(i).getPrice();
            if (p >= min && p <= max) r.add(items.get(i));
        }
        return r;
    }
}
