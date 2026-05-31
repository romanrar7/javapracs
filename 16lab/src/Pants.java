public class Pants extends Clothes {
    private int waist;
    private int length;

    public Pants(String name, String brand, double price, String size, String color,
                 SizeCategory category, int waist, int length) {
        super(name, brand, price, size, color, category);
        setWaist(waist);
        setLength(length);
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        if (waist < 20 || waist > 60) {
            throw new IllegalArgumentException("РќРµРєРѕСЂРµРєС‚РЅРёР№ РѕР±С…РІР°С‚ С‚Р°Р»С–С—");
        }
        this.waist = waist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < 50 || length > 130) {
            throw new IllegalArgumentException("РќРµРєРѕСЂРµРєС‚РЅР° РґРѕРІР¶РёРЅР° С€С‚Р°РЅС–РІ");
        }
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + ", waist=" + waist + ", length=" + length;
    }
}

