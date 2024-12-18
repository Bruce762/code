package org.example;

public class FitnessClub {

    private GitHubService gitHubService;

    public FitnessClub(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public int calculateFee(int grade, int absenceCount, int typingMinutes, int wpm, String gitHubRepo) {
        if (grade < 2) {
            throw new IllegalArgumentException("Grade must be >= 2");
        }

        int baseFee = 500;
        int discount = 0;

        // Absence discount
        if (absenceCount < 5) {
            discount += 50;
        }

        // WPM discount
        if (wpm > 100) {
            discount += 150;
        } else if (wpm > 80) {
            discount += 100;
        } else if (wpm > 60) {
            discount += 50;
        }

        // Typing minutes discount based on grade
        if (grade == 2 && typingMinutes >= 5) {
            discount += (typingMinutes >= 10 ? 200 : 100);
        } else if (grade == 3 && typingMinutes >= 10) {
            discount += (typingMinutes >= 15 ? 200 : 100);
        } else if (grade == 4 && typingMinutes >= 15) {
            discount += (typingMinutes >= 20 ? 200 : 100);
        }

        // GitHub repo discount (for grade >= 2)
        // GitHub repo discount
        int lines = gitHubService.getLines(gitHubRepo);
        int wmc = gitHubService.getWMC(gitHubRepo);
        if (grade == 2) {
            discount += (lines / 1000) * 50;
            discount = Math.min(discount, 200);
        } else if ( wmc > 50) {
            discount += (lines / 1000) * 50;
            discount = Math.min(discount, 200);
        }

        // Final fee calculation
        int finalFee = baseFee - discount;
        return Math.max(finalFee, 0); // Fee cannot be negative
    }
}
