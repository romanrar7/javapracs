package sumdu;

/**
 * Клас одягу для практичної роботи №4.
 */
public class Clothes {
    private String name;
    private String brand;
    private double price;
    private String size;

    public Clothes(String name, String brand, double price, String size) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Clothes{name='" + name + "', brand='" + brand
                + "', price=" + price + ", size='" + size + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Clothes other = (Clothes) obj;
        if (Double.compare(other.price, price) != 0) {
            return false;
        }
        if (name == null ? other.name != null : !name.equals(other.name)) {
            return false;
        }
        if (brand == null ? other.brand != null : !brand.equals(other.brand)) {
            return false;
        }
        return size == null ? other.size == null : size.equals(other.size);
    }
}
