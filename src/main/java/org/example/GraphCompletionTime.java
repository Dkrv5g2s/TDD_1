package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class GraphCompletionTime {
    private final List<Integer> inDegree;           // 入度列表
    private final List<Integer> timeRequired;       // 每個節點的所需時間列表
    private final List<Integer> earliestCompletion; // 每個節點的最早完成時間列表
    private final List<List<Integer>> adjacencyList; // 每個節點的後續節點列表


    // 初始化各個 List
    public GraphCompletionTime(int n) {
        inDegree = new ArrayList<>(n);
        timeRequired = new ArrayList<>(n);
        earliestCompletion = new ArrayList<>(n);
        adjacencyList = new ArrayList<>(n);



        // 初始化各個 List 的元素值
        for (int i = 0; i < n; i++) {
            inDegree.add(0);               // 初始化入度為 0
            timeRequired.add(0);           // 初始化時間需求為 0
            earliestCompletion.add(0);     // 初始化最早完成時間為 0
            adjacencyList.add(new ArrayList<>()); // 初始化後續節點列表
        }

    }

    // 設定每個節點的所需時間
    public void setTimeRequired(int node, int time) {
        timeRequired.set(node - 1, time); // 使用 set() 方法設置時間需求
    }

    // 添加邊，將 `from` 節點設為 `to` 節點的前置節點
    public void addEdge(int from, int to) {
        adjacencyList.get(from - 1).add(to - 1); // 將 `to` 節點加入 `from` 的後續節點列表中
        inDegree.set(to - 1, inDegree.get(to - 1) + 1); // 更新 `to` 節點的入度
    }


    // 判斷是否為有向無環圖（DAG）
    private boolean isDAG(int n) {
        Queue<Integer> queue = new LinkedList<>();
        int processedCount = 0;

        for (int i = 0; i < n; i++) {
            if (inDegree.get(i) == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            processedCount++;

            for (int nextNode : adjacencyList.get(node)) {
                inDegree.set(nextNode, inDegree.get(nextNode) - 1);
                if (inDegree.get(nextNode) == 0) {
                    queue.add(nextNode);
                }
            }
        }

        return processedCount == n; // 如果處理的節點數等於 n，則是 DAG
    }

    // 計算最大完成時間
    public int calculateMaxCompletionTime(int n) {
        if (!isDAG(n)) {
            return -1; // 如果有循環，返回 -1
        }

        // 追蹤每個不相連子圖的最大完成時間
        int overallMaxTime = 0;

        // 記錄已訪問的節點
        boolean[] visited = new boolean[n];

        // 對於每個節點，如果它尚未訪問過，計算它所屬子圖的完成時間
        for (int i = 0; i < n; i++) {
            if (!visited[i] && inDegree.get(i) == 0) {
                int maxTimeForComponent = calculateTotalTimeForComponent(i, visited);
                overallMaxTime = Math.max(overallMaxTime, maxTimeForComponent);
            }
        }

        return overallMaxTime;
    }

    // 計算單個不相連子圖的總完成時間
    private int calculateTotalTimeForComponent(int startNode, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode] = true;

        int currentCompletionTime = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            currentCompletionTime=currentCompletionTime+timeRequired.get(node);

            for (int nextNode : adjacencyList.get(node)) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }

        return currentCompletionTime;
    }

}
