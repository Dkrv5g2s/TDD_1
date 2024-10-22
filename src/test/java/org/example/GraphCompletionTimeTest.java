package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphCompletionTimeTest {


    @Test
    public void testSimpleLinearGraph() {
        GraphCompletionTime graph = new GraphCompletionTime(3);
        graph.setTimeRequired(1, 2); // Node 1: 2時間
        graph.setTimeRequired(2, 3); // Node 2: 3時間
        graph.setTimeRequired(3, 1); // Node 3: 1時間
        graph.addEdge(1, 2); // 1 -> 2
        graph.addEdge(2, 3); // 2 -> 3

        int result = graph.calculateMaxCompletionTime(3);
        assertEquals(6, result); // 2 + 3 + 1 = 6
    }

    @Test
    public void testCyclicGraph() {
        GraphCompletionTime graph = new GraphCompletionTime(2);
        graph.setTimeRequired(1, 2);
        graph.setTimeRequired(2, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);

        int result = graph.calculateMaxCompletionTime(2);
        assertEquals(-1, result); // 應返回一個指示循環的值
    }

    @Test
    public void testGraphWithNoCycle() {
        GraphCompletionTime graph = new GraphCompletionTime(3);
        graph.setTimeRequired(1, 2); // 設定節點 0 的處理時間
        graph.setTimeRequired(2, 3); // 設定節點 1 的處理時間
        graph.setTimeRequired(3, 1); // 設定節點 2 的處理時間

        // 添加邊以形成無循環的拓撲結構
        graph.addEdge(1, 2); // 0 -> 1
        graph.addEdge(2, 3); // 1 -> 2

        int result = graph.calculateMaxCompletionTime(3); // 計算最大完成時間
        assertEquals(6, result); // 總時間應為 6 (2 + 3 + 1)
    }

    @Test
    public void testGraphWithMultipleDependenciesCycle() {
        GraphCompletionTime graph = new GraphCompletionTime(4);
        graph.setTimeRequired(1, 3); // 設定節點 0 的處理時間
        graph.setTimeRequired(2, 4); // 設定節點 1 的處理時間
        graph.setTimeRequired(3, 2); // 設定節點 2 的處理時間
        graph.setTimeRequired(4, 5); // 設定節點 3 的處理時間

        // 添加邊以形成無循環的拓撲結構
        graph.addEdge(1, 2); // 0 -> 1
        graph.addEdge(1, 3); // 0 -> 2
        graph.addEdge(2, 4); // 1 -> 3
        graph.addEdge(3, 4); // 2 -> 3

        int result = graph.calculateMaxCompletionTime(4); // 計算最大完成時間
        assertEquals(14, result); // 總時間應為 10 (2 + 3 + 1 + 4)
    }

    @Test
    public void testMultipleDisjointGraphs() {
        GraphCompletionTime graph = new GraphCompletionTime(6);

        // 第一個不相連的部分圖
        graph.setTimeRequired(1, 3); // Node 1 需要 3 時間
        graph.setTimeRequired(2, 2); // Node 2 需要 2 時間
        graph.setTimeRequired(3, 5); // Node 3 需要 4 時間
        graph.addEdge(1, 2); // 邊 1 -> 2
        graph.addEdge(2, 3); // 邊 2 -> 3

        // 第二個不相連的部分圖
        graph.setTimeRequired(4, 6); // Node 4 需要 6 時間
        graph.setTimeRequired(5, 1); // Node 5 需要 1 時間
        graph.setTimeRequired(6, 2); // Node 6 需要 2 時間
        graph.addEdge(4, 5); // 邊 4 -> 5
        graph.addEdge(5, 6); // 邊 5 -> 6

        int result = graph.calculateMaxCompletionTime(6);
        // 預期結果應該是計算第一個圖(3+2+4=9) 和第二個圖(6+1+2=9)，因此返回 9
        assertEquals(10, result);
    }





}

