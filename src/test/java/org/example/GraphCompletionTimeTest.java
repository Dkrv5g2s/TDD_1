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
}
