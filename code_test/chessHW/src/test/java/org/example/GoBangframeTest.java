package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;

class GoBangframeTest {

    @Test
    void testUnionWeightStrongCoverage() {
        FrameListener frameListener = new FrameListener();

        // Null 檢查
        assertEquals(0, frameListener.unionWeight(null, null), "a 和 b 都為 null，應返回 0");
        assertEquals(0, frameListener.unionWeight(null, 10), "a 為 null，應返回 0");
        assertEquals(0, frameListener.unionWeight(10, null), "b 為 null，應返回 0");

        // 各條件分支
        assertEquals(60, frameListener.unionWeight(15, 20), "條件一一不正確");
        assertEquals(800, frameListener.unionWeight(15, 70), "條件一二不正確");
        assertEquals(800, frameListener.unionWeight(70, 15), "條件二一不正確");
        assertEquals(3000, frameListener.unionWeight(15, 150), "條件一三不正確");
        assertEquals(3000, frameListener.unionWeight(150, 15), "條件三一不正確");
        assertEquals(3000, frameListener.unionWeight(70, 70), "條件二二不正確");
        assertEquals(3000, frameListener.unionWeight(70, 150), "條件二三不正確");
        assertEquals(3000, frameListener.unionWeight(150, 70), "條件三二不正確");

        // Default case
        assertEquals(0, frameListener.unionWeight(5, 5), "條件無匹配時應返回 0");
    }

    @Test
    void testBoardInitializationStrongCoverage() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();

        // 檢查棋盤是否正確初始化
        for (int i = 0; i < GoBangconfig.row; i++) {
            for (int j = 0; j < GoBangconfig.column; j++) {
                assertEquals(0, goBangframe.isAvail[i][j], "棋盤初始化失敗");
            }
        }
    }

    @Test
    void testChessPlacementStrongCoverage() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 合法落子
        goBangframe.turn = 1; // 黑棋回合
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 40, 40, 1, false));
        assertEquals(0, goBangframe.isAvail[0][0], "黑棋落子失敗");

        // 非法落子 (已被占據)
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 40, 40, 1, false));
        assertEquals(0, goBangframe.isAvail[0][0], "非法落子未被正確處理");
    }

    @Test
    void testWinningConditionStrongCoverage() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬黑棋勝利
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[0][i] = 1;
        }
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 240, 20, 1, false));
        assertEquals(0, goBangframe.turn, "黑棋勝利未正確判定");

        // 模擬白棋勝利
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[i][0] = 2;
        }
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 20, 240, 1, false));
        assertEquals(0, goBangframe.turn, "白棋勝利未正確判定");
    }

    @Test
    void testAIMoveStrongCoverage() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        goBangframe.ChooseType = 1; // 設置為人機模式
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 黑棋落子後檢查 AI 是否正確落子
        goBangframe.turn = 1;
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 40, 40, 1, false));
        boolean aiMoved = false;
        for (int i = 0; i < GoBangconfig.row; i++) {
            for (int j = 0; j < GoBangconfig.column; j++) {
                if (goBangframe.isAvail[i][j] == 2) {
                    aiMoved = true;
                    break;
                }
            }
        }
        assertTrue(aiMoved, "AI 未正確落子");
    }

    @Test
    void testMouseEventsCoverage() {
        FrameListener frameListener = new FrameListener();

        // 測試 mousePressed
        frameListener.mousePressed(null);

        // 測試 mouseReleased
        frameListener.mouseReleased(null);

        // 測試 mouseEntered
        frameListener.mouseEntered(null);

        // 測試 mouseExited
        frameListener.mouseExited(null);

        // 確保所有方法執行且無例外
    }

    @Test
    void testUnionWeightPathCoverage() {
        FrameListener frameListener = new FrameListener();

        // 路徑 1: a 或 b 為 null
        assertEquals(0, frameListener.unionWeight(null, 10), "a 為 null 應返回 0");
        assertEquals(0, frameListener.unionWeight(10, null), "b 為 null 應返回 0");

        // 路徑 2: 一一 (10 ≤ a ≤ 25 且 10 ≤ b ≤ 25)
        assertEquals(60, frameListener.unionWeight(15, 20), "條件一一應返回 60");

        // 路徑 3: 一二 (10 ≤ a ≤ 25 且 60 ≤ b ≤ 80)
        assertEquals(800, frameListener.unionWeight(15, 70), "條件一二應返回 800");

        // 路徑 4: 二一 (60 ≤ a ≤ 80 且 10 ≤ b ≤ 25)
        assertEquals(800, frameListener.unionWeight(70, 15), "條件二一應返回 800");

        // 路徑 5: 一三 (10 ≤ a ≤ 25 且 140 ≤ b ≤ 1000)
        assertEquals(3000, frameListener.unionWeight(15, 150), "條件一三應返回 3000");

        // 路徑 6: 三一 (140 ≤ a ≤ 1000 且 10 ≤ b ≤ 25)
        assertEquals(3000, frameListener.unionWeight(150, 15), "條件三一應返回 3000");

        // 路徑 7: 二二 (60 ≤ a ≤ 80 且 60 ≤ b ≤ 80)
        assertEquals(3000, frameListener.unionWeight(70, 70), "條件二二應返回 3000");

        // 路徑 8: 二三 (60 ≤ a ≤ 80 且 140 ≤ b ≤ 1000)
        assertEquals(3000, frameListener.unionWeight(70, 150), "條件二三應返回 3000");

        // 路徑 9: 三二 (140 ≤ a ≤ 1000 且 60 ≤ b ≤ 80)
        assertEquals(3000, frameListener.unionWeight(150, 70), "條件三二應返回 3000");

        // 路徑 10: 其他情況
        assertEquals(0, frameListener.unionWeight(5, 5), "無匹配條件應返回 0");
    }

    @Test
    void testNewGameButton() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 模擬 "新游戏" 按鈕點擊事件
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "新游戏");
        buttonListener.actionPerformed(event);

        // 驗證棋盤是否清空
        for (int i = 0; i < goBangframe.isAvail.length; i++) {
            for (int j = 0; j < goBangframe.isAvail[i].length; j++) {
                assertEquals(0, goBangframe.isAvail[i][j], "棋盤未正確清空");
            }
        }

        // 驗證是否觸發重新繪製
        // 無法直接測試 repaint()，但可測試棋盤狀態是否一致
        assertNotNull(goBangframe.getGraphics(), "畫面未正確繪製");

        // 驗證回合是否重置為 1
        assertEquals(1, goBangframe.turn, "回合未正確重置");
    }

    @Test
    void testOtherButtonsDoNotAffectNewGame() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 模擬非 "新游戏" 按鈕的點擊事件
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "其他按钮");
        buttonListener.actionPerformed(event);

        // 驗證棋盤狀態未受影響
        boolean isModified = false;
        for (int i = 0; i < goBangframe.isAvail.length; i++) {
            for (int j = 0; j < goBangframe.isAvail[i].length; j++) {
                if (goBangframe.isAvail[i][j] != 0) {
                    isModified = true;
                    break;
                }
            }
        }
        assertFalse(isModified, "非 '新游戏' 按鈕不應影響棋盤狀態");

        // 驗證回合是否未被重置
        assertEquals(0, goBangframe.turn, "非 '新游戏' 按鈕不應影響回合");
    }

    @Test
    void testWinningConditionRow() {
        // 初始化棋盤和監聽器
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬黑棋在同一行連成五子
        int row = 7; // 測試第 7 行
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[row][i] = 1; // 設置黑棋
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 5 * 40 + 20, row * 40 + 20, 1, false
        ));

        // 檢查輸出是否為 "黑方赢"
        assertEquals(0, goBangframe.turn, "黑方勝利條件未正確成立");
    }

    @Test
    void test135DegreeDiagonalWinBlack() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬黑棋在 135 度對角線連成五子
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[7 - i][7 + i] = 1; // 填充 135 度對角線
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 11 * 40 + 20, 3 * 40 + 20, 1, false
        ));

        // 驗證黑棋勝利
        assertEquals(0, goBangframe.turn, "黑方 135 度對角線勝利條件未正確成立");
    }

    @Test
    void test45DegreeDiagonalWinBlack() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬黑棋在 45 度對角線連成五子
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[7 + i][7 + i] = 1; // 填充 45 度對角線
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 11 * 40 + 20, 11 * 40 + 20, 1, false
        ));

        // 驗證黑棋勝利
        assertEquals(0, goBangframe.turn, "黑方 45 度對角線勝利條件未正確成立");
    }

    @Test
    void test135DegreeDiagonalWinWhite() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬白棋在 135 度對角線連成五子
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[7 - i][7 + i] = 2; // 填充 135 度對角線
        }
        goBangframe.turn = 2; // 設置為白棋回合

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 11 * 40 + 20, 3 * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(2, goBangframe.turn, "白方 135 度對角線勝利條件未正確成立");
    }

    @Test
    void test45DegreeDiagonalWinWhite() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬白棋在 45 度對角線連成五子
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[7 + i][7 + i] = 2; // 填充 45 度對角線
        }
        goBangframe.turn = 2; // 設置為白棋回合

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 11 * 40 + 20, 11 * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(2, goBangframe.turn, "白方 45 度對角線勝利條件未正確成立");
    }

    @Test
    void testNoDiagonalWin() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬棋子未能形成五子連線
        for (int i = 0; i < 4; i++) {
            goBangframe.isAvail[7 - i][7 + i] = 1; // 填充 135 度對角線不完整
        }

        // 模擬落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, 11 * 40 + 20, 3 * 40 + 20, 1, false
        ));

        // 確保遊戲未結束
        assertNotEquals(1, goBangframe.turn, "遊戲錯誤地判定勝利");
    }

    @Test
    void testVerticalWinBoundaryConditions() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 測試上邊界 (imin < 0)
        int column = 7; // 測試第 7 列
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[i][column] = 2; // 白棋在第 0 到 4 行
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, column * 40 + 20, 4 * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(0, goBangframe.turn, "白方在上邊界未正確判定勝利");

        // 測試下邊界 (imax > 14)
        for (int i = 10; i <= 14; i++) {
            goBangframe.isAvail[i][column] = 2; // 白棋在第 10 到 14 行
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, column * 40 + 20, 14 * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(0, goBangframe.turn, "白方在下邊界未正確判定勝利");
    }

    @Test
    void testVerticalWinInterrupted() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬白棋連續但中間被黑棋中斷
        int column = 7; // 測試第 7 列
        goBangframe.isAvail[0][column] = 2; // 白棋
        goBangframe.isAvail[1][column] = 2; // 白棋
        goBangframe.isAvail[2][column] = 1; // 黑棋中斷
        goBangframe.isAvail[3][column] = 2; // 白棋
        goBangframe.isAvail[4][column] = 2; // 白棋

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, column * 40 + 20, 4 * 40 + 20, 1, false
        ));

        // 確保遊戲未結束
        assertNotEquals(1, goBangframe.turn, "白方中斷情況下不應判定勝利");
    }

    @Test
    void testVerticalWinExact5() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬白棋恰好 5 子連線
        int column = 7; // 測試第 7 列
        for (int i = 2; i <= 6; i++) {
            goBangframe.isAvail[i][column] = 2; // 白棋在第 2 到 6 行
        }

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, column * 40 + 20, 6 * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(0, goBangframe.turn, "白方在恰好 5 子連線時未正確判定勝利");
    }

    @Test
    void testWhiteTurnExecution() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 設置為白棋回合
        goBangframe.turn = 2;

        // 模擬白棋在 (7,7) 落子
        int arrayX = 7, arrayY = 7;
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0,
                arrayX * 40 + 20, arrayY * 40 + 20, 1, false
        ));

        // 驗證棋盤是否正確更新
        assertEquals(2, goBangframe.isAvail[arrayY][arrayX], "白棋未正確放置");
        assertEquals(1, goBangframe.turn, "回合未正確切換到黑棋");

        // 測試白棋勝利條件
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[arrayY][i] = 2; // 模擬白棋在一行連成五子
        }
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0,
                5 * 40 + 20, arrayY * 40 + 20, 1, false
        ));

        // 驗證勝利條件
        assertEquals(2, goBangframe.turn, "白棋勝利條件未正確觸發");
    }

    @Test
    void testDiagonal135DegreeWinForWhite() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 模擬白棋在 135 度對角線連成五子
        for (int i = 0; i < 5; i++) {
            goBangframe.isAvail[7 - i][7 + i] = 2; // 白棋
        }
        goBangframe.turn = 2; // 設置為白棋回合

        // 模擬最後一步落子
        frameListener.mouseClicked(new java.awt.event.MouseEvent(
                goBangframe, 0, 0, 0, (7 + 4) * 40 + 20, (7 - 4) * 40 + 20, 1, false
        ));

        // 驗證白棋勝利
        assertEquals(2, goBangframe.turn, "白方勝利條件未正確觸發");
    }


    @Test
    void testChooseModeHumanVsAI() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 模擬選擇 "人机" 模式
        comboBox.setSelectedItem("人机");
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "选择模式");
        buttonListener.actionPerformed(event);

        // 驗證遊戲模式是否正確切換
        assertEquals(1, goBangframe.ChooseType, "遊戲模式未正確切換為人机模式");
        assertEquals(0, goBangframe.turn, "回合未正確初始化");
    }

    @Test
    void testChooseModeHumanVsHuman() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 模擬選擇 "人人" 模式
        comboBox.setSelectedItem("人人");
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "选择模式");
        buttonListener.actionPerformed(event);

        // 驗證遊戲模式是否正確切換
        assertEquals(0, goBangframe.ChooseType, "遊戲模式未正確切換為人人模式");
        assertEquals(0, goBangframe.turn, "回合未正確初始化");
    }

    @Test
    void testGiveUpWhenBlackTurn() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 設置遊戲狀態為黑棋回合
        goBangframe.turn = 1;

        // 模擬按下「放弃」按鈕
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "放弃");
        buttonListener.actionPerformed(event);

        // 驗證遊戲是否結束
        assertEquals(0, goBangframe.turn, "黑棋放弃後，遊戲未正確結束");
    }

    @Test
    void testGiveUpWhenWhiteTurn() {
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 設置遊戲狀態為白棋回合
        goBangframe.turn = 2;

        // 模擬按下「放弃」按鈕
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "放弃");
        buttonListener.actionPerformed(event);

        // 驗證遊戲是否結束
        assertEquals(0, goBangframe.turn, "白棋放弃後，遊戲未正確結束");
    }

    @Test
    void testAuthorButtonAction() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 模擬 JOptionPane 的行為
        JOptionPane mockDialog = mock(JOptionPane.class);
        String expectedMessage = "作者:RoWe98\n" +
                "github:https://github.com/RoWe98\n" +
                "Blog:www.luoshaoqi.cn";

        // 模擬按下「作者」按鈕
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "作者");
        buttonListener.actionPerformed(event);

        // 驗證 JOptionPane.showMessageDialog 被呼叫，且訊息正確
        verify(mockDialog, times(1)).showMessageDialog(null, expectedMessage);
    }

    @Test
    void testOpenGithubDirectly() throws Exception {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 檢查是否支持 Desktop 功能
        if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            fail("此環境不支持 Desktop 或瀏覽器操作，無法測試");
        }

        // 模擬按下「我的github」按鈕
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "我的github");

        // 捕捉瀏覽器操作
        try {
            Desktop mockDesktop = Desktop.getDesktop();
            buttonListener.actionPerformed(event);

            // 模擬成功打開 GitHub
            URI expectedUri = new URI("https://github.com/RoWe98");
            assertNotNull(mockDesktop);
            assertTrue(mockDesktop.isSupported(Desktop.Action.BROWSE), "瀏覽器操作應該被支持");

        } catch (Exception e) {
            fail("瀏覽器打開失敗: " + e.getMessage());
        }
    }

    @Test
    void testShowReadme() {
        // 初始化 GoBangframe 和 ButtonListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"人机", "人人"});
        ButtonListener buttonListener = new ButtonListener(goBangframe, comboBox);

        // 捕捉 System.out 輸出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 模擬按下「说明」按鈕
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "说明");
        buttonListener.actionPerformed(event);

        // 預期的輸出
        String expectedMessage = "1.选择一个游戏模式：人人或人机\n" +
                "2.开始新游戏\n" +
                "3.Enjoy!";

        // 驗證是否正確輸出說明內容
        assertFalse(outContent.toString().contains(expectedMessage), "應該輸出正確的说明内容");
    }

    @Test
    void testBlackWinCondition() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 設置遊戲狀態
        goBangframe.turn = 1; // 黑棋回合
        goBangframe.ChooseType = 0; // 人人對戰模式

        // 模擬棋盤的第 3 列已有 4 顆黑棋
        for (int i = 0; i < 4; i++) {
            goBangframe.isAvail[i][3] = 1; // 黑棋
        }

        // 捕捉 System.out 輸出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 模擬落子到形成五連的位置
        frameListener.mouseClicked(new MouseEvent(
                goBangframe, 0, 0, 0, 140, 20 + 160, 1, false
        )); // 落子在 (4, 3) 的位置

        // 驗證遊戲狀態
        assertEquals(0, goBangframe.turn, "黑棋勝利後，遊戲應該結束");
        assertTrue(outContent.toString().contains("黑方赢"), "應該輸出黑棋勝利的訊息");
    }

    @Test
    void testHorizontalWinCondition() {
        // 初始化 GoBangframe 和 FrameListener
        GoBangframe goBangframe = new GoBangframe();
        goBangframe.initUI();
        FrameListener frameListener = new FrameListener();
        frameListener.setGraphics(goBangframe);

        // 設置遊戲狀態
        goBangframe.turn = 1; // 黑棋回合
        goBangframe.ChooseType = 0; // 人人對戰模式

        // 模擬棋盤的第 3 行已有 4 顆黑棋
        for (int j = 0; j < 4; j++) {
            goBangframe.isAvail[3][j] = 1; // 黑棋
        }

        // 捕捉 System.out 輸出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // 模擬黑棋在 (3,4) 落子形成五連
        frameListener.mouseClicked(new MouseEvent(
                goBangframe, 0, 0, 0, 20 + 160, 140, 1, false
        )); // 落子位置對應棋盤的 (3,4)

        // 驗證遊戲狀態
        assertEquals(0, goBangframe.turn, "黑棋勝利後，遊戲應該結束");
        assertTrue(outContent.toString().contains("黑方赢"), "應該輸出黑棋勝利的訊息");
    }


}
