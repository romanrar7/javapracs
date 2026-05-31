/**
 * Клас одягу (розширена версія для ЛР5).
 */
public class Clothes {
    private String name;
    private String brand;
    private double price;
    private String size;
    private String color;

    public Clothes(String name, String brand, double price, String size, String color) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        setSize(size);
        setColor(color);
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва не може бути порожньою");
        }
        this.name = name.trim();
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Бренд не може бути порожнім");
        }
        this.brand = brand.trim();
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна має бути більше 0");
        }
        this.price = price;
    }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Розмір не може бути порожнім");
        }
        this.size = size.trim();
    }

    public String getColor() { return color; }
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Колір не може бути порожнім");
        }
        this.color = color.trim();
    }

    @Override
    public String toString() {
        return "Clothes{name='" + name + "', brand='" + brand + "', price=" + price
                + ", size='" + size + "', color='" + color + "'}";
    }
}
