package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        // 设置：将样本 JSON 数据写入临时文件
        String jsonData = "{\n" +
                "  \"season\": \"2023\",\n" +
                "  \"teamRecord\": [\n" +
                "    {\n" +
                "      \"team\": \"BAL\",\n" +
                "      \"wins\": 101,\n" +
                "      \"losses\": 61,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"TB\",\n" +
                "      \"wins\": 99,\n" +
                "      \"losses\": 63,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"TOR\",\n" +
                "      \"wins\": 89,\n" +
                "      \"losses\": 73,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"NYY\",\n" +
                "      \"wins\": 82,\n" +
                "      \"losses\": 80,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"BOS\",\n" +
                "      \"wins\": 78,\n" +
                "      \"losses\": 84,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIN\",\n" +
                "      \"wins\": 87,\n" +
                "      \"losses\": 75,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"DET\",\n" +
                "      \"wins\": 78,\n" +
                "      \"losses\": 84,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"CLE\",\n" +
                "      \"wins\": 76,\n" +
                "      \"losses\": 86,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"CWS\",\n" +
                "      \"wins\": 61,\n" +
                "      \"losses\": 101,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"KC\",\n" +
                "      \"wins\": 56,\n" +
                "      \"losses\": 106,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"HOU\",\n" +
                "      \"wins\": 90,\n" +
                "      \"losses\": 72,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"TEX\",\n" +
                "      \"wins\": 90,\n" +
                "      \"losses\": 72,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"SEA\",\n" +
                "      \"wins\": 88,\n" +
                "      \"losses\": 74,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"LAA\",\n" +
                "      \"wins\": 73,\n" +
                "      \"losses\": 89,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"OAK\",\n" +
                "      \"wins\": 50,\n" +
                "      \"losses\": 112,\n" +
                "      \"league\": \"AL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"ATL\",\n" +
                "      \"wins\": 104,\n" +
                "      \"losses\": 58,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"PHI\",\n" +
                "      \"wins\": 90,\n" +
                "      \"losses\": 72,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIA\",\n" +
                "      \"wins\": 85,\n" +
                "      \"losses\": 77,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"NYM\",\n" +
                "      \"wins\": 75,\n" +
                "      \"losses\": 87,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"WSH\",\n" +
                "      \"wins\": 71,\n" +
                "      \"losses\": 91,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"East\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIL\",\n" +
                "      \"wins\": 92,\n" +
                "      \"losses\": 70,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"CHC\",\n" +
                "      \"wins\": 83,\n" +
                "      \"losses\": 79,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"CIN\",\n" +
                "      \"wins\": 82,\n" +
                "      \"losses\": 80,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"PIT\",\n" +
                "      \"wins\": 76,\n" +
                "      \"losses\": 86,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"STL\",\n" +
                "      \"wins\": 71,\n" +
                "      \"losses\": 91,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"Central\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"LAD\",\n" +
                "      \"wins\": 100,\n" +
                "      \"losses\": 62,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"ARI\",\n" +
                "      \"wins\": 84,\n" +
                "      \"losses\": 78,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"SD\",\n" +
                "      \"wins\": 82,\n" +
                "      \"losses\": 80,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"SF\",\n" +
                "      \"wins\": 79,\n" +
                "      \"losses\": 83,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"West\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"COL\",\n" +
                "      \"wins\": 59,\n" +
                "      \"losses\": 103,\n" +
                "      \"league\": \"NL\",\n" +
                "      \"division\": \"West\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"playoffTeams\": [\n" +
                "    {\n" +
                "      \"team\": \"TEX\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"TB\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"BAL\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"HOU\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"ARI\",\n" +
                "          \"advanced\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"TB\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"TEX\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIN\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"TOR\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"HOU\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"TOR\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"MIN\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"ARI\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"MIL\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"LAD\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"PHI\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"TEX\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIL\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"ARI\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"PHI\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"MIA\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"ATL\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"ARI\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"MIA\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"PHI\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"BAL\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"TEX\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"HOU\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"MIN\",\n" +
                "          \"advanced\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"opponent\": \"TEX\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"ATL\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"PHI\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"team\": \"LAD\",\n" +
                "      \"series\": [\n" +
                "        {\n" +
                "          \"opponent\": \"ARI\",\n" +
                "          \"advanced\": false\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        // 创建临时文件并写入 JSON 数据
        File jsonFile = new File("src/main/java/org/example/team2023battle.json");
        jsonFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(jsonFile)) {
            writer.write(jsonData);
        }

        // 捕获程序输出
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        // 运行主程序
        Main.main(new String[]{});

        // 恢复系统输出
        System.out.flush();
        System.setOut(oldOut);

        // 获取程序输出
        String output = baos.toString();

        // 定义预期输出（根据主程序实际输出填写）
        String expectedOutput = "TEX ────┐\n" +
                "        ├───TEX──┐\n" +
                "TB  ────┘        │\n" +
                "                 TEX──┐\n" +
                "      HOU────────┘    │\n" +
                "MIN ────┐             TEX──┐\n" +
                "        ├───HOU──┐    │    │\n" +
                "TOR ────┘        │    │    │\n" +
                "                 HOU──┘    │\n" +
                "      TEX────────┘         │\n" +
                "ATL ────┐                  TEX\n" +
                "        ├───PHI──┐         │\n" +
                "PHI ────┘        │         │\n" +
                "                 ARI──┐    │\n" +
                "      LAD────────┘    │    │\n" +
                "MIL ────┐             ARI──┘\n" +
                "        ├───ARI──┐    │\n" +
                "ARI ────┘        │    │\n" +
                "                 ARI──┘\n" +
                "      ARI────────┘\n";

        // 断言输出是否与预期一致
        assertEquals(expectedOutput.trim(), output.trim());
    }
}
