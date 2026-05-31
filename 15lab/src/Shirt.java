public class Shirt extends Clothes {
    private String collarType;
    private String sleeveLength;

    public Shirt(String name, String brand, double price, String size, String color,
                 SizeCategory category, String collarType, String sleeveLength) {
        super(name, brand, price, size, color, category);
        setCollarType(collarType);
        setSleeveLength(sleeveLength);
    }

    public String getCollarType() { return collarType; }
    public void setCollarType(String collarType) {
        if (collarType == null || collarType.trim().isEmpty()) {
            throw new IllegalArgumentException("Порожній комір");
        }
        this.collarType = collarType.trim();
    }

    public String getSleeveLength() { return sleeveLength; }
    public void setSleeveLength(String sleeveLength) {
        if (sleeveLength == null || sleeveLength.trim().isEmpty()) {
            throw new IllegalArgumentException("Порожня довжина рукава");
        }
        this.sleeveLength = sleeveLength.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", collar='" + collarType + "', sleeve='" + sleeveLength + "'";
    }
}
