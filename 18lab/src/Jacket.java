public class Jacket extends Clothes {
    private boolean waterproof;
    private String lining;

    public Jacket(String name, String brand, double price, String size, String color,
                  SizeCategory category, boolean waterproof, String lining) {
        super(name, brand, price, size, color, category);
        this.waterproof = waterproof;
        setLining(lining);
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }

    public String getLining() {
        return lining;
    }

    public void setLining(String lining) {
        if (lining == null || lining.trim().isEmpty()) {
            throw new InvalidFieldValueException("Підкладка не може бути порожньою");
        }
        this.lining = lining.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", waterproof=" + waterproof + ", lining='" + lining + "'";
    }
}
