package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 計算本次總統大學獲勝的人、票數、獲勝方式
 */
@Slf4j
class USAElection {
    /* 本次選舉的總選舉人票數 */
    public final static int TOTALVOTES = 538;

    public static Object[] getElectionResult(List<ticketData> electionData) throws IllegalAccessException {
        String winner = "NONE";
        String AlignOrSplit = "X";
        // 每一州的資料用一個 HashMap 來表示，
        // key 是州的名稱, value 是一陣列，包含該州選舉人票數、兩個候選人的普選人口得票數
        Map<String, int[]> state_votes = new HashMap<>();
        String candidate1 = "", candidate2 = "";

        for (ticketData fields : electionData) {
            if (((String) fields.getState()).equals("State")) {
                candidate1 = fields.getCandidate1Vote();
                candidate2 = fields.getCandidate2Vote();
                continue;
            }

            int[] v = {
                    Integer.parseInt(fields.getElectoralVotes()),
                    Integer.parseInt(fields.getCandidate1Vote()),
                    Integer.parseInt(fields.getCandidate2Vote())
            };
            state_votes.put(fields.getState(), v);
        }
        int c1_popular_votes = 0; // 一號候選人的人口票
        int c2_popular_votes = 0; // 二號候選人的人口票
        int c1_electoral_votes = 0; // 一號候選人的選舉人票
        int c2_electoral_votes = 0; // 二號候選人的選舉人票

        for (Map.Entry<String, int[]> voteEntry : state_votes.entrySet()) {
            String state = voteEntry.getKey();
            int[] vote_arr = voteEntry.getValue();
            c1_popular_votes += vote_arr[1]; // 累加票數
            c2_popular_votes += vote_arr[2];
            if (Objects.equals(state, "Nebraska") || Objects.equals(state, "Maine")) {
                double totalPopularVotes = vote_arr[1] + vote_arr[2];
                double c1votePercentage = (double) vote_arr[1] / totalPopularVotes,
                        c2votePercentage = (double) vote_arr[2] / totalPopularVotes;

                int currentTakeVote1 = (int) Math.floor(vote_arr[0] * c1votePercentage),
                        currentTakeVote2 = (int) Math.floor(vote_arr[0] * c2votePercentage);
                if (currentTakeVote1 + currentTakeVote2 != vote_arr[0]) {
                    if (c1votePercentage > c2votePercentage) {
                        currentTakeVote1 += 1;
                    } else {
                        currentTakeVote2 += 1;
                    }
                }
                log.info("比例原則 {} 總票數 {}", state, vote_arr[0]);
                log.info("c1 得票率 {} 獲得票數 {} ,c2 得票率 {} 獲得票數 {}"
                        , c1votePercentage, currentTakeVote1, c2votePercentage, currentTakeVote2);
                c1_electoral_votes += currentTakeVote1;
                c2_electoral_votes += currentTakeVote2;
            } else {
                if (vote_arr[1] > vote_arr[2]) // 選舉人票
                    c1_electoral_votes += vote_arr[0];
                else if (vote_arr[2] > vote_arr[1])
                    c2_electoral_votes += vote_arr[0];
                else
                    throw new IllegalAccessException("不該出現同票的情況，請重新驗票");
            }
        }

        // 針對總選舉人票數不等於應有進行拋出例外
        if (c1_electoral_votes + c2_electoral_votes != TOTALVOTES) {
            log.error(String.valueOf(c1_electoral_votes + " " + c2_electoral_votes));
            throw new IllegalAccessException("c1_electoral_votes + c2_electoral_votes != TOTAL VOTES");
        }
        log.info("C1 get electoral_votes:{}  popular_votes:{}", c1_electoral_votes, c1_popular_votes);
        log.info("C2 get electoral_votes:{}  popular_votes:{}", c2_electoral_votes, c2_popular_votes);

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

        log.info("{}", winner);
        return new String[]{winner, String.valueOf(winner_electoral_votes), AlignOrSplit};
    }
}