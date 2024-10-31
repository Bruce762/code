package demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;

import java.awt.*;

import static org.junit.jupiter.api.condition.OS.*;


public class OSTest {
    @Test
    @EnabledOnOs(WINDOWS)
    void onlyOnWindows() {
        System.out.println("This will test on Windows");
    }

    @Test
    @EnabledOnOs(MAC)
    void onlyOnMAC() {
        System.out.println("This will test on MAC");
    }

}
