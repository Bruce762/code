package xdemo;

import java.util.Arrays;

public class SwimmingPool {

    public double price(String day, boolean isMember, int age) {
        double price;
        String[] working = { "Monday", "Tuesday", "Wednsday", "Thursday", "Freday" };
        boolean isHoliday = !Arrays.asList(working).contains(day);
        if (age > 70) {
            price = 50;
        } else if (isMember) {
            if (isHoliday) {
                price = 120;
            } else
                price = 70;
        } else if (isHoliday) {
            price = 150;
        } else
            price = 100;

        return price;
    }
}
