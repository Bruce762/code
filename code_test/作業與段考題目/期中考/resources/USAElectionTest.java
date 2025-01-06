package exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class USAElectionTest {

    @Test
    void getElectionResult() {
        List<Object[]> data = new ArrayList<>();

        try (InputStream is = USAElectionTest.class.getResourceAsStream("/votes2020.csv");
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while ((line = br.readLine()) != null) {
                // 移除引號並分割每行的欄位
                String[] values = line.split(",");

                // 將每行的資料轉為 Object[]
                Object[] row = new Object[values.length];
                row[0] = values[0]; // 州名 (String)
                row[1] = values[1]; // 選舉人票數
                row[2] = values[2]; // 一號候選人 得票數
                row[3] = values[3]; // 二號候選人 得票數

                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[] expected = new Object[] { "Biden", 306, "Align" };
        Object[] actual = USAElection.getElectionResult(data);
        assertArrayEquals(expected, actual);
    }
}