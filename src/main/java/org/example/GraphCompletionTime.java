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

    // 計算最大完成時間
    public int calculateMaxCompletionTime(int n) {
        return -1;
    }


}
