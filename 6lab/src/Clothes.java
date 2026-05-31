public class Clothes {
    private static int count = 0;

    private String name;
    private String brand;
    private double price;
    private String size;
    private String color;
    private SizeCategory category;

    public Clothes(String name, String brand, double price, String size, String color, SizeCategory category) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        setSize(size);
        setColor(color);
        setCategory(category);
        count++;
    }

    public Clothes(Clothes other) {
        this(other.name, other.brand, other.price, other.size, other.color, other.category);
    }

    public static int getCount() {
        return count;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Порожня назва");
        this.name = name.trim();
    }
    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Порожній бренд");
        this.brand = brand.trim();
    }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Некоректна ціна");
        this.price = price;
    }
    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) throw new IllegalArgumentException("Порожній розмір");
        this.size = size.trim();
    }
    public String getColor() { return color; }
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) throw new IllegalArgumentException("Порожній колір");
        this.color = color.trim();
    }
    public SizeCategory getCategory() { return category; }
    public void setCategory(SizeCategory category) {
        if (category == null) throw new IllegalArgumentException("Категорія обов'язкова");
        this.category = category;
    }

    @Override
    public String toString() {
        return "Clothes{name='" + name + "', brand='" + brand + "', price=" + price
                + ", size='" + size + "', color='" + color + "', category=" + category + "}";
    }
}
