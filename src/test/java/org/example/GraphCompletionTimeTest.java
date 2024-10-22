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


}

