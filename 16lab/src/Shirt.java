public class Shirt extends Clothes {
    private String collarType;
    private String sleeveLength;

    public Shirt(String name, String brand, double price, String size, String color,
                 SizeCategory category, String collarType, String sleeveLength) {
        super(name, brand, price, size, color, category);
        setCollarType(collarType);
        setSleeveLength(sleeveLength);
    }

    public String getCollarType() {
        return collarType;
    }

    public void setCollarType(String collarType) {
        if (collarType == null || collarType.trim().isEmpty()) {
            throw new IllegalArgumentException("РўРёРї РєРѕРјС–СЂР° РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј");
        }
        this.collarType = collarType.trim();
    }

    public String getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(String sleeveLength) {
        if (sleeveLength == null || sleeveLength.trim().isEmpty()) {
            throw new IllegalArgumentException("Р”РѕРІР¶РёРЅР° СЂСѓРєР°РІР° РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅСЊРѕСЋ");
        }
        this.sleeveLength = sleeveLength.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", collarType='" + collarType + "', sleeveLength='" + sleeveLength + "'";
    }
}

