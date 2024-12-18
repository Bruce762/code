package org.example;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class FitnessClubTest {

    @Mock
    private GitHubService gitHubService;

    private FitnessClub fitnessClub;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fitnessClub = new FitnessClub(gitHubService);
    }

    @Test
    public void testCalculateFee_WithAllDiscounts() {
        // Setup mock responses for GitHubService
        when(gitHubService.getLines("repo1")).thenReturn(5000); // 5k lines
        when(gitHubService.getWMC("repo1")).thenReturn(60); // wmc > 50

        // Test data
        int grade = 3;
        int absenceCount = 3;
        int typingMinutes = 12;
        int wpm = 85;
        String gitHubRepo = "repo1";

        // Calculate fee
        int fee = fitnessClub.calculateFee(grade, absenceCount, typingMinutes, wpm, gitHubRepo);

        // Check the fee calculations
        assertEquals(300, fee); // Base fee 500 - all discounts
    }

    @Test
    public void testCalculateFee_NoDiscounts() {
        // Test data
        int grade = 2;
        int absenceCount = 6; // No absence discount
        int typingMinutes = 4; // No typing discount
        int wpm = 50; // No typing speed discount
        String gitHubRepo = "repo1";

        // Setup mock response for GitHubService
        when(gitHubService.getLines("repo1")).thenReturn(1000); // 1k lines
        when(gitHubService.getWMC("repo1")).thenReturn(30); // wmc < 50

        // Calculate fee
        int fee = fitnessClub.calculateFee(grade, absenceCount, typingMinutes, wpm, gitHubRepo);

        // Check the fee calculations
        assertEquals(450, fee); // No discounts, should be 500
    }

    @Test
    public void testCalculateFee_InvalidGrade() {
        // Test for invalid grade
        assertThrows(IllegalArgumentException.class, () -> {
            fitnessClub.calculateFee(1, 3, 10, 70, "repo1");
        });
    }

    @Test
    public void testGitHubServiceInteraction() {
        // Setup mock responses for GitHubService
        when(gitHubService.getLines("repo1")).thenReturn(5000); // 5k lines
        when(gitHubService.getWMC("repo1")).thenReturn(60); // wmc > 50

        // Test data
        int grade = 3;
        int absenceCount = 3;
        int typingMinutes = 12;
        int wpm = 85;
        String gitHubRepo = "repo1";

        // Calculate fee
        fitnessClub.calculateFee(grade, absenceCount, typingMinutes, wpm, gitHubRepo);

        // Verify the interaction with GitHubService
        verify(gitHubService, times(1)).getLines("repo1");
        verify(gitHubService, times(1)).getWMC("repo1");
    }
    @Test
    public void testWPMDiscounts() {
        // Test WPM discount tiers
        when(gitHubService.getLines("repo1")).thenReturn(0);
        when(gitHubService.getWMC("repo1")).thenReturn(0);

        // Below 60 WPM - no discount
        int fee1 = fitnessClub.calculateFee(2, 0, 0, 50, "repo1");
        assertEquals(450, fee1);

        // Exactly 60 WPM - no discount
        int fee2 = fitnessClub.calculateFee(2, 0, 0, 60, "repo1");
        assertEquals(450, fee2);

        // Just above 60 WPM - 50 discount
        int fee3 = fitnessClub.calculateFee(2, 0, 0, 61, "repo1");
        assertEquals(400, fee3);

        // Exactly 80 WPM - 50 discount
        int fee4 = fitnessClub.calculateFee(2, 0, 0, 80, "repo1");
        assertEquals(400, fee4);

        // Just above 80 WPM - 100 discount
        int fee5 = fitnessClub.calculateFee(2, 0, 0, 81, "repo1");
        assertEquals(350, fee5);

        // Exactly 100 WPM - 100 discount
        int fee6 = fitnessClub.calculateFee(2, 0, 0, 100, "repo1");
        assertEquals(350, fee6);

        // Just above 100 WPM - 150 discount
        int fee7 = fitnessClub.calculateFee(2, 0, 0, 101, "repo1");
        assertEquals(300, fee7);
    }

    @Test
    public void testTypingMinutesDiscounts() {
        // Test typing minutes for different grades
        when(gitHubService.getLines("repo1")).thenReturn(0);
        when(gitHubService.getWMC("repo1")).thenReturn(0);

        // Grade 2 - Typing Minutes Discount
        int fee1 = fitnessClub.calculateFee(2, 0, 5, 70, "repo1");
        assertEquals(300, fee1);

        int fee2 = fitnessClub.calculateFee(2, 0, 10, 70, "repo1");
        assertEquals(300, fee2);

        // Grade 3 - Typing Minutes Discount
        int fee3 = fitnessClub.calculateFee(3, 0, 10, 70, "repo1");
        assertEquals(300, fee3);

        int fee4 = fitnessClub.calculateFee(3, 0, 15, 70, "repo1");
        assertEquals(200, fee4);

        // Grade 4 - Typing Minutes Discount
        int fee5 = fitnessClub.calculateFee(4, 0, 15, 70, "repo1");
        assertEquals(300, fee5);

        int fee6 = fitnessClub.calculateFee(4, 0, 20, 70, "repo1");
        assertEquals(200, fee6);
        int fee7 = fitnessClub.calculateFee(4, 0, 10, 70, "repo1");
        assertEquals(400, fee7);
    }

    @Test
    public void testGitHubRepositoryDiscounts() {
        // Test GitHub repository discounts for different grades
        when(gitHubService.getWMC("repo1")).thenReturn(60); // Above threshold for grades > 2

        // Grade 2 - Discount based on lines
        int fee1 = fitnessClub.calculateFee(2, 0, 0, 70, "repo1");
        when(gitHubService.getLines("repo1")).thenReturn(1000);
        assertEquals(400, fee1);

        when(gitHubService.getLines("repo1")).thenReturn(5000); // Max 200 discount
        int fee2 = fitnessClub.calculateFee(2, 0, 0, 70, "repo1");
        assertEquals(300, fee2);

        // Grade 3 - Discount with WMC condition
        when(gitHubService.getWMC("repo1")).thenReturn(30); // Below threshold
        int fee3 = fitnessClub.calculateFee(3, 0, 0, 70, "repo1");
        when(gitHubService.getLines("repo1")).thenReturn(1000);
        assertEquals(400, fee3);

        when(gitHubService.getWMC("repo1")).thenReturn(60); // Above threshold
        int fee4 = fitnessClub.calculateFee(3, 0, 0, 70, "repo1");
        when(gitHubService.getLines("repo1")).thenReturn(5000); // Max 200 discount
        assertEquals(350, fee4);
    }

}
