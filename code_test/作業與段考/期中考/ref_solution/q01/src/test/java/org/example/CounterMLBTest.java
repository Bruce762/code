package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CounterMLBTest {

    @BeforeEach
    void init() {
        log.info("開始測試");
    }

    @ParameterizedTest(name = "票價測試 #{index} - 預期票價:{0}, 票種:{1}, VIP:{2}")
    @DisplayName("測試 .csv 內設計的所有正常可能資料")
    @CsvFileSource(resources = "/Data.csv", numLinesToSkip = 1)
    void counter(double expected, int mode, boolean isVip) throws IllegalAccessException {
        double result = CounterMLB.counter(mode, isVip);
        log.info("預期: {} 測試結果: {}",expected , result);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("測試 套票 + VIP 的組合出現")
    @ValueSource(ints = {3})
    void counterForCheckVIPAndPackage(int mode) {
        IllegalAccessException illegalAccessException = assertThrows(IllegalAccessException.class, () -> CounterMLB.counter(mode, TRUE));
        log.info("測試拋出接受到的 message {}", illegalAccessException.getMessage());
        assertEquals("不能為套票又使用 VIP", illegalAccessException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("測試 mode 非規範內的拋出")
    @ValueSource(ints = {-1,4,5})
    void checkModeInZeroToThree(int mode) {
        IllegalAccessException illegalAccessException = assertThrows(IllegalAccessException.class, () -> CounterMLB.counter(mode, TRUE));
        log.info("測試拋出接受到的 message {}", illegalAccessException.getMessage());
        assertEquals("mode 應該介於 0~3之間", illegalAccessException.getMessage());
    }
}