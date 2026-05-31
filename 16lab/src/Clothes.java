import java.util.UUID;


public abstract class Clothes implements Comparable<Clothes>, Identifiable {
    private final UUID uuid;
    private String name;
    private String brand;
    private double price;
    private String size;
    private String color;
    private SizeCategory category;

    protected Clothes(String name, String brand, double price, String size, String color, SizeCategory category) {
        this.uuid = UUID.randomUUID();
        setName(name);
        setBrand(brand);
        setPrice(price);
        setSize(size);
        setColor(color);
        setCategory(category);
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("РќР°Р·РІР° РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅСЊРѕСЋ");
        }
        this.name = name.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Р‘СЂРµРЅРґ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј");
        }
        this.brand = brand.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Р¦С–РЅР° РјР°С” Р±СѓС‚Рё Р±С–Р»СЊС€Рµ 0");
        }
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Р РѕР·РјС–СЂ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј");
        }
        this.size = size.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("РљРѕР»С–СЂ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј");
        }
        this.color = color.trim();
    }

    public SizeCategory getCategory() {
        return category;
    }

    public void setCategory(SizeCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("РљР°С‚РµРіРѕСЂС–СЏ СЂРѕР·РјС–СЂСѓ РѕР±РѕРІ'СЏР·РєРѕРІР°");
        }
        this.category = category;
    }

    public String shortInfo() {
        return getClass().getSimpleName() + ": " + name + " | UUID: " + uuid;
    }

    @Override
    public int compareTo(Clothes other) {
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{uuid=" + uuid + ", name='" + name + "', brand='" + brand
                + "', price=" + price + ", size='" + size + "', color='" + color
                + "', category=" + category + "}";
    }
}

