package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class USAElectionTest {
    private void setData(String path, List<ticketData> data) {
        try (InputStream is = USAElectionTest.class.getResourceAsStream(path)) {
            assert is != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;

                while ((line = br.readLine()) != null) {
                    // 移除引號並分割每行的欄位
                    String[] values = line.split(",");

                    // 將每行的資料轉為 Object[]
                    ticketData row = new ticketData();
                    row.setState(values[0]); // 州名 (String)
                    row.setElectoralVotes(values[1]); // 選舉人票數
                    row.setCandidate1Vote(values[2]); // 一號候選人 得票數
                    row.setCandidate2Vote(values[3]); // 二號候選人 得票數
                    log.info("測試獲得: {}", String.valueOf(row));
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void init() {
        log.info("-------\n開始測試\n------");
    }

    @Test
    @DisplayName("原始基本的測試資料 (Nebraska && Maine 進行比例分配)")
    void getElectionResult() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020.csv", data);

        String[] expected = new String[]{"Biden", "307", "Align"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("非Nebraska 與 Maine 同票的測試資料")
    void testSameTicket() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_occur_state_same_votes.csv", data);

        String expected = "不該出現同票的情況，請重新驗票";
        IllegalAccessException illegalAccessError = assertThrows(IllegalAccessException.class, () -> USAElection.getElectionResult(data));
        assertEquals(expected, illegalAccessError.getMessage());
    }

    @Test
    @DisplayName("平手的測試資料")
    void noWinner() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_NoWinner.csv", data);

        String[] expected = new String[]{"NONE", "-1", "X"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Biden Split的測試資料")
    void testBidenSplit() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_Biden_Split.csv", data);

        String[] expected = new String[]{"Biden", "318", "Split"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Trump Align 的測試資料")
    void testTrumpAlign() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_Trump_Align.csv", data);

        String[] expected = new String[]{"Trump", "489", "Align"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Trump Split 的測試資料")
    void testTrumpSplit() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_Trump_Split.csv", data);

        String[] expected = new String[]{"Trump", "489", "Split"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("測試 (Nebraska && Maine 比例分配) 是否有符合比例較大者 + 1 的條件 By 相同機率")
    void testBiggerProbabilityPlus1BySameProbability() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_probability_bigger_plus1_same.csv", data);

        String[] expected = new String[]{"Biden", "306", "Align"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("測試 (Nebraska && Maine 比例分配) 是否有符合比例較大者 + 1 的條件 By 相同機率")
    void testBiggerProbabilityPlus1ByBiggerProbability() throws IllegalAccessException {
        List<ticketData> data = new ArrayList<>();
        setData("/votes2020_probability_bigger_plus1_bigger1.csv", data);

        String[] expected = new String[]{"Biden", "307", "Align"};
        String[] actual = (String[]) USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }
}