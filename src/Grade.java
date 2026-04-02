import java.math.BigDecimal;

public enum Grade {
    BRONZE("BRONZE",0.00,0),
    SILVER("SILVER",0.05,5),
    GOLD("GOLD",0.1,10),
    PLATINUM("PLATINUM",0.15,15);

    private String grade;
    private double discount;
    private int percent;

    Grade(String grade, double discount, int percent) {
        this.grade=grade;
        this.discount=discount;
        this.percent=percent;
    }

    public String getGrade() {
        return grade;
    }

    public double getDiscount() {
        return discount;
    }

    public int getPercent() {
        return percent;
    }

    public String discountPrice(String strPrice) {
        String imsi = strPrice.replaceAll("[^0-9]", "");
        int price = Integer.parseInt(imsi);
        return String.format("%,d", (int)(price * getDiscount()));
    }

    public String apply(String strPrice) {
        String imsiPrice = strPrice.replaceAll("[^0-9]", "");
        int price = Integer.parseInt(imsiPrice);
        String imsiDiscount = discountPrice(strPrice).replaceAll("[^0-9]", "");
        int discount = Integer.parseInt(imsiDiscount);
        int resultPrice = price - discount;
        return String.format("%,d" ,resultPrice);
    }
}
