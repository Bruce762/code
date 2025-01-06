package org.example;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 負責執行票價的計算
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CounterMLB {
    // 基本的票價
    private final static double BASIC_PRICE = 500;
    // 假日的票價
    private final static double HOLIDAY_PRICE = 600;
    // 季後賽的票價
    private final static double POST_PRICE = 1000;
    // 套票的票價
    private final static double PACKAGE_PRICE = 250;
    // VIP的票價
    private final static double VIP_BONUS = 0.8;

    /*
     * mode 0 基本票價, 1 假日票價, 2 季後賽票價, 3 套票*/
    public static double counter(int mode, boolean isVIP) throws IllegalAccessException {
        if (mode < 0 || mode > 3)
            throw new IllegalAccessException("mode 應該介於 0~3之間");

        // 透過 judgePrice 獲得 price
        double result = judgePrice(mode);

        // 判斷是否出現 VIP + mode == 3 的情況，若有拋出例外
        boolean bothVipAndModeThree = isVIP && mode == 3;
        if (bothVipAndModeThree)
            throw new IllegalAccessException("不能為套票又使用 VIP");

        // 對 VIP 打折
        if (isVIP)
            result *= VIP_BONUS;

        log.info("mode {} isVIp {} 最後算出 {}", mode, isVIP, result);
        return result;
    }

    /**
     * 根據模式選擇金額*/
    public static double judgePrice(int mode) {
        return switch (mode) {
            case 1 -> HOLIDAY_PRICE;
            case 2 -> POST_PRICE;
            case 3 -> PACKAGE_PRICE;
            default -> BASIC_PRICE;
        };
    }
}
