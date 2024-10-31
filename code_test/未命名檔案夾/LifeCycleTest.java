package demo;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

class LifeCycleTest {

    @BeforeAll
    @DisplayName("initialAll")
    static void initAll() {
        System.out.println("initialAll");
    }

    @BeforeEach
    @DisplayName("  init")
    void init() {
        System.out.println("  init");
    }

    @Test
    @DisplayName("    display test 1")
    void test1() {
        System.out.println("    test 1");
    }

    @Test
    @DisplayName("    display test 2")
    void test2() {
        System.out.println("    test 2");
    }

    @Test
    @DisplayName("    failingTest")//先暫時不測試的意思
    void failingTest() {
        System.out.println("    failingTest");
        fail("a failing test");
    }

    @Test
    @DisplayName("    skippedTest")
    @Disabled("Disabled- for demonstration")
    void skippedTest() {
        System.out.println("    skippedTest");
    }

    @Test
    @DisplayName("    abortedTest")
    void abortedTest() {
        System.out.println("    abortedTest 1");
        assumeTrue("abc".contains("Z"));
        System.out.println("    abortedTest 2");
    }

    @AfterEach
    @DisplayName("  tearDown")
    void tearDown() {
        System.out.println("  tearDown");
    }

    @AfterAll
    @DisplayName("tearDownAll")
    static void tearDownAll() {
        System.out.println("tearDownAll");
    }
}


