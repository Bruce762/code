package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;
class calTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", numLinesToSkip = 1)
    void calpriceal(int isHoliday, int isMember, int isPlayoff, int isPackageTicket,int expect)throws Exception {
        int p= TicketCalculator.calPrice(isHoliday,isMember,isPlayoff,isPackageTicket);
        assertEquals(p, expect);
    }
}