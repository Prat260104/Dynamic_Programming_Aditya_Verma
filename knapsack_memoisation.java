// 0/1 knapsack memoization

import java.util.*;

public class Main {

    static int[][] dp;

    static int knapsack(int[] wt, int[] val, int W, int n) {

        // Base case
        if (n == 0 || W == 0) return 0;

        // 🔥 Memoization check
        if (dp[n][W] != -1) return dp[n][W];

        // Choice diagram
        if (wt[n - 1] <= W) {
            dp[n][W] = Math.max(
                val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1),
                knapsack(wt, val, W, n - 1)
            );
        } else {
            dp[n][W] = knapsack(wt, val, W, n - 1);
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];

        for (int i = 0; i < n; i++) wt[i] = sc.nextInt();
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        int W = sc.nextInt();

        // 🔥 DP array initialize
        dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = knapsack(wt, val, W, n);
        System.out.println(ans);
    }
}