public class NumberToWords {
    public static String toWords(int number) {
        if (number == 0) {
            return "нуль";
        }

        int n = number;
        String result = "";
        if (n < 0) {
            result = "мінус ";
            n = -n;
        }

        int hundreds = n / 100;
        int tens = (n % 100) / 10;
        int ones = n % 10;

        if (hundreds > 0) {
            result = result + getHundreds(hundreds) + " ";
        }

        if (tens == 1) {
            result = result + getTeens(ones);
        } else {
            if (tens > 1) {
                result = result + getTens(tens) + " ";
            }
            if (ones > 0) {
                result = result + getOnes(ones);
            }
        }

        return result.trim();
    }

    private static String getOnes(int value) {
        switch (value) {
            case 1: return "один";
            case 2: return "два";
            case 3: return "три";
            case 4: return "чотири";
            case 5: return "п'ять";
            case 6: return "шість";
            case 7: return "сім";
            case 8: return "вісім";
            case 9: return "дев'ять";
            default: return "";
        }
    }

    private static String getTeens(int value) {
        switch (value) {
            case 0: return "десять";
            case 1: return "одинадцять";
            case 2: return "дванадцять";
            case 3: return "тринадцять";
            case 4: return "чотирнадцять";
            case 5: return "п'ятнадцять";
            case 6: return "шістнадцять";
            case 7: return "сімнадцять";
            case 8: return "вісімнадцять";
            case 9: return "дев'ятнадцять";
            default: return "";
        }
    }

    private static String getTens(int value) {
        switch (value) {
            case 2: return "двадцять";
            case 3: return "тридцять";
            case 4: return "сорок";
            case 5: return "п'ятдесят";
            case 6: return "шістдесят";
            case 7: return "сімдесят";
            case 8: return "вісімдесят";
            case 9: return "дев'яносто";
            default: return "";
        }
    }

    private static String getHundreds(int value) {
        switch (value) {
            case 1: return "сто";
            case 2: return "двісті";
            case 3: return "триста";
            case 4: return "чотириста";
            case 5: return "п'ятсот";
            case 6: return "шістсот";
            case 7: return "сімсот";
            case 8: return "вісімсот";
            case 9: return "дев'ятсот";
            default: return "";
        }
    }
}
