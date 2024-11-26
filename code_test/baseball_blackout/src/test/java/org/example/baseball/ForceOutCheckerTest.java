package org.example.baseball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class ForceOutCheckerTest {

    private ForceOutChecker checker;

    @BeforeEach
    void setUp() {
        checker = new ForceOutChecker();
    }

    @Test
    @DisplayName("當沒有人在壘時，只有一壘可以封殺")
    void testEmptyBases() {
        List<String> result = checker.getForceOutBases("x");
        assertEquals(Arrays.asList("1B"), result, "空壘時應該只有一壘可以封殺");
    }

    @Test
    @DisplayName("當一壘有人時，一、二壘可以封殺")
    void testRunnerOnFirst() {
        List<String> result = checker.getForceOutBases("1B");
        assertEquals(Arrays.asList("1B", "2B"), result, "一壘有人時應該可以在一、二壘形成封殺");
    }

    @Test
    @DisplayName("當一、三壘有人時，一、二壘可以封殺")
    void testRunnersOnFirstAndThird() {
        List<String> result = checker.getForceOutBases("1B,3B");
        assertEquals(Arrays.asList("1B", "2B"), result, "一三壘有人時應該可以在一、二壘形成封殺");
    }

    @Test
    @DisplayName("當只有三壘有人時，只有一壘可以封殺")
    void testRunnerOnThird() {
        List<String> result = checker.getForceOutBases("3B");
        assertEquals(Arrays.asList("1B"), result, "三壘有人時應該只有一壘可以封殺");
    }

    @Test
    @DisplayName("當一、二壘有人時，一、二、三壘可以封殺")
    void testRunnersOnFirstAndSecond() {
        List<String> result = checker.getForceOutBases("1B,2B");
        assertEquals(Arrays.asList("1B", "2B", "3B"), result, "一二壘有人時應該可以在一、二、三壘形成封殺");
    }

    @Test
    @DisplayName("當二、三壘有人時，只有一壘可以封殺")
    void testRunnersOnSecondAndThird() {
        List<String> result = checker.getForceOutBases("2B,3B");
        assertEquals(Arrays.asList("1B"), result, "二三壘有人時應該只有一壘可以封殺");
    }

    @Test
    @DisplayName("當滿壘時，所有壘包都可以封殺")
    void testBasesLoaded() {
        List<String> result = checker.getForceOutBases("1B,2B,3B");
        assertEquals(Arrays.asList("1B", "2B", "3B", "HB"), result, "滿壘時所有壘包都應該可以封殺");
    }

    @Test
    @DisplayName("當輸入為null時，只有一壘可以封殺")
    void testNullInput() {
        List<String> result = checker.getForceOutBases(null);
        assertEquals(Arrays.asList("1B"), result, "輸入為null時應該只有一壘可以封殺");
    }

    @Test
    @DisplayName("當輸入為空字串時，只有一壘可以封殺")
    void testEmptyString() {
        List<String> result = checker.getForceOutBases("");
        assertEquals(Arrays.asList("1B"), result, "輸入為空字串時應該只有一壘可以封殺");
    }

    @Test
    @DisplayName("當輸入格式錯誤時，應拋出IllegalArgumentException")
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> checker.getForceOutBases("1B,4B"),
                "無效的壘包標示應該拋出IllegalArgumentException");
    }

    @Test
    @DisplayName("當二壘有人時，只有一壘可以封殺")
    void testRunnerOnSecond() {
        List<String> result = checker.getForceOutBases("2B");
        assertEquals(Arrays.asList("1B"), result, "二壘有人時應該只有一壘可以封殺");
    }
}