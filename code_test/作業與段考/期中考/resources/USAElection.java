package exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class USAElection {
    public static Object[] getElectionResult(List<Object[]> electionData) {
        String winner = "NONE";
        String AlignOrSplit = "X";
        // 每一州的資料用一個 HashMap 來表示，
        // key 是州的名稱, value 是一陣列，包含該州選舉人票數、兩個候選人的普選人口得票數
        Map<String, int[]> state_votes = new HashMap<>();
        String candidate1, candidate2;

        for (Object[] fields : electionData) {
            if (((String) fields[0]).equals("State")) {
                candidate1 = (String) fields[2];
                candidate2 = (String) fields[3];
                break;
            }

            int[] v = {
                    fields[1],
                    fields[2],
                    fields[3]
            };
            state_votes.put((String) fields[0], v);
        }
        int c1_popular_votes = 0; // 一號候選人的人口票
        int c2_popular_votes = 0; // 二號候選人的人口票
        int c1_electoral_votes = 0; // 一號候選人的選舉人票
        int c2_electoral_votes = 0; // 二號候選人的選舉人票

        for (Map.Entry<String, int[]> voteEntry : state_votes.entrySet()) {
            String state = voteEntry.getKey();
            int[] vote_arr = voteEntry.getValue();
            c1_popular_votes += vote_arr[2]; // 累加票數
            c2_popular_votes += vote_arr[3];

            if (vote_arr[2] > vote_arr[3]) // 選舉人票
                c1_electoral_votes += vote_arr[1];
            else if (vote_arr[3] > vote_arr[2])
                c2_electoral_votes += vote_arr[1];
        }

        int winner_electoral_votes = 0;
        if (c1_electoral_votes > c2_electoral_votes) {
            winner = candidate1;
            winner_electoral_votes = c1_electoral_votes;
            AlignOrSplit = (c1_popular_votes > c2_popular_votes) ? "Align" : "Split";
        } else if (c2_electoral_votes > c1_electoral_votes) {
            winner = candidate2;
            winner_electoral_votes = c2_electoral_votes;
            AlignOrSplit = (c2_popular_votes > c1_popular_votes) ? "Align" : "Split";
        } else
            winner_electoral_votes = -1;
        Object[] result = { winner, winner_electoral_votes, AlignOrSplit };
        return result;
    }
}