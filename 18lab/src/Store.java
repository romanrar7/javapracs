import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Магазин одягу з колекцією та кількістю товарів.
 */
public class Store {
    private String name;
    private String address;
    private final ArrayList<Clothes> items;
    private final Map<UUID, Integer> quantities;

    public Store(String name, String address) {
        setName(name);
        setAddress(address);
        this.items = new ArrayList<Clothes>();
        this.quantities = new HashMap<UUID, Integer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidFieldValueException("Назва магазину не може бути порожньою");
        }
        this.name = name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidFieldValueException("Адреса не може бути порожньою");
        }
        this.address = address.trim();
    }

    public ArrayList<Clothes> getItems() {
        return items;
    }

    public int getQuantity(Clothes clothes) {
        Integer q = quantities.get(clothes.getUuid());
        if (q == null) {
            return 0;
        }
        return q.intValue();
    }

    public void addNewClothes(Clothes clothes, int quantity) {
        if (clothes == null) {
            throw new InvalidFieldValueException("Об'єкт одягу не може бути null");
        }
        if (quantity <= 0) {
            throw new InvalidFieldValueException("Кількість має бути більше 0");
        }
        for (int i = 0; i < items.size(); i++) {
            Clothes existing = items.get(i);
            if (isSameProduct(existing, clothes)) {
                UUID id = existing.getUuid();
                int oldQ = getQuantity(existing);
                quantities.put(id, Integer.valueOf(oldQ + quantity));
                return;
            }
        }
        items.add(clothes);
        quantities.put(clothes.getUuid(), Integer.valueOf(quantity));
    }

    public void delete(Clothes clothes) {
        if (clothes == null) {
            throw new InvalidFieldValueException("Об'єкт для видалення не задано");
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getUuid().equals(clothes.getUuid())) {
                quantities.remove(clothes.getUuid());
                items.remove(i);
                return;
            }
        }
        throw new ObjectNotFoundException("Товар не знайдено в магазині");
    }

    public void updateQuantity(Clothes clothes, int newQuantity) {
        if (!quantities.containsKey(clothes.getUuid())) {
            throw new ObjectNotFoundException("Товар не знайдено для оновлення");
        }
        if (newQuantity <= 0) {
            throw new InvalidFieldValueException("Кількість має бути більше 0");
        }
        quantities.put(clothes.getUuid(), Integer.valueOf(newQuantity));
    }

    public List<Clothes> findByName(String part) {
        ArrayList<Clothes> result = new ArrayList<Clothes>();
        String key = part.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            Clothes c = items.get(i);
            if (c.getName().toLowerCase().contains(key)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Clothes> findByBrand(String brand) {
        ArrayList<Clothes> result = new ArrayList<Clothes>();
        String key = brand.toLowerCase();
        for (int i = 0; i < items.size(); i++) {
            Clothes c = items.get(i);
            if (c.getBrand().toLowerCase().equals(key)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Clothes> findByPriceRange(double min, double max) {
        ArrayList<Clothes> result = new ArrayList<Clothes>();
        for (int i = 0; i < items.size(); i++) {
            Clothes c = items.get(i);
            if (c.getPrice() >= min && c.getPrice() <= max) {
                result.add(c);
            }
        }
        return result;
    }

    public Clothes findByUuid(UUID uuid) {
        for (int i = 0; i < items.size(); i++) {
            Clothes c = items.get(i);
            if (c.getUuid().equals(uuid)) {
                return c;
            }
        }
        return null;
    }

    public void sortByName() {
        Collections.sort(items);
    }

    public void sortByPrice() {
        Collections.sort(items, new Comparator<Clothes>() {
            @Override
            public int compare(Clothes o1, Clothes o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
    }

    public void sortByBrand() {
        Collections.sort(items, new Comparator<Clothes>() {
            @Override
            public int compare(Clothes o1, Clothes o2) {
                return o1.getBrand().compareToIgnoreCase(o2.getBrand());
            }
        });
    }

    public void sortByPriceLambda() {
        items.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
    }

    public void sortByBrandLambda() {
        items.sort((o1, o2) -> o1.getBrand().compareToIgnoreCase(o2.getBrand()));
    }

    public void sortByColorLambda() {
        items.sort((o1, o2) -> o1.getColor().compareToIgnoreCase(o2.getColor()));
    }

    private boolean isSameProduct(Clothes a, Clothes b) {
        return a.getClass() == b.getClass()
                && a.getName().equalsIgnoreCase(b.getName())
                && a.getBrand().equalsIgnoreCase(b.getBrand())
                && a.getSize().equalsIgnoreCase(b.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Store other = (Store) obj;
        return name.equals(other.name) && address.equals(other.address);
    }

    @Override
    public String toString() {
        return "Store{name='" + name + "', address='" + address + "', items=" + items.size() + "}";
    }
}
