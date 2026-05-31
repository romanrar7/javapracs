public class Skirt extends Clothes {
    private int lengthCm;
    private String cut;

    public Skirt(String name, String brand, double price, String size, String color,
                 SizeCategory category, int lengthCm, String cut) {
        super(name, brand, price, size, color, category);
        setLengthCm(lengthCm);
        setCut(cut);
    }

    public int getLengthCm() {
        return lengthCm;
    }

    public void setLengthCm(int lengthCm) {
        if (lengthCm < 20 || lengthCm > 120) {
            throw new IllegalArgumentException("РќРµРєРѕСЂРµРєС‚РЅР° РґРѕРІР¶РёРЅР° СЃРїС–РґРЅРёС†С–");
        }
        this.lengthCm = lengthCm;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        if (cut == null || cut.trim().isEmpty()) {
            throw new IllegalArgumentException("Р¤Р°СЃРѕРЅ РЅРµ РјРѕР¶Рµ Р±СѓС‚Рё РїРѕСЂРѕР¶РЅС–Рј");
        }
        this.cut = cut.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", lengthCm=" + lengthCm + ", cut='" + cut + "'";
    }
}

