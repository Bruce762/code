package org.example.baseball;
import java.util.*;
public class ForceOutChecker {
    public static void main(String[] args) {
    ForceOutChecker checker = new ForceOutChecker();
    Scanner scanner = new Scanner(System.in);



    while (true) {
        System.out.println("\n請輸入目前壘包狀態:");
        System.out.println("輸入 'exit' 結束程式");

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            break;
        }

        try {
            List<String> forceOutBases = checker.getForceOutBases(input);
            System.out.println("可以封殺的壘包: " + String.join(", ", forceOutBases));
        } catch (IllegalArgumentException e) {
            System.out.println("錯誤: " + e.getMessage());
        }
    }

    scanner.close();
    System.out.println("程式結束");
}

    /**
     * 判斷目前壘包狀態下可以形成封殺的壘包
     * @param bases 目前壘包狀態的字串，例如 "1B,2B"
     * @return 可以形成封殺的壘包列表
     */
    public List<String> getForceOutBases(String bases) {
        // 如果輸入是空的或是 "x"，代表沒有人在壘，只有一壘可以封殺
        if (bases == null || bases.trim().isEmpty() || bases.trim().equals("x")) {
            return Collections.singletonList("1B");
        }

        // 解析目前壘包狀態
        Set<String> currentBases = new HashSet<>(
                Arrays.asList(bases.split(",\\s*"))
        );

        // 驗證輸入格式
        for (String base : currentBases) {
            if (!base.equals("1B") && !base.equals("2B") && !base.equals("3B")) {
                throw new IllegalArgumentException("無效的壘包標示: " + base + "。請使用 1B、2B 或 3B。");
            }
        }

        List<String> forceOutBases = new ArrayList<>();

        // 一壘永遠可以封殺（因為打者會往一壘跑）
        forceOutBases.add("1B");

        // 如果一壘有人，二壘可以封殺
        if (currentBases.contains("1B")) {
            forceOutBases.add("2B");
        }

        // 如果一二壘都有人，三壘可以封殺
        if (currentBases.contains("1B") && currentBases.contains("2B")) {
            forceOutBases.add("3B");
        }

        // 如果一二三壘都有人，本壘可以封殺
        if (currentBases.contains("1B") &&
                currentBases.contains("2B") &&
                currentBases.contains("3B")) {
            forceOutBases.add("HB");
        }

        return forceOutBases;
    }
}
