// 0/1 knapsack Recursive


import java.util.*;

public class Main {

    // Recursive function
    static int knapsack(int[] wt, int[] val, int W, int n) {

        // Base condition
        if (n == 0 || W == 0) return 0;

        // Choice diagram
        if (wt[n - 1] <= W) {
            return Math.max(
                val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1), // take
                knapsack(wt, val, W, n - 1)                          // not take
            );
        } else {
            return knapsack(wt, val, W, n - 1); // skip
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        int n = sc.nextInt(); // number of items
        int[] wt = new int[n];
        int[] val = new int[n];

        for (int i = 0; i < n; i++) wt[i] = sc.nextInt();
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        int W = sc.nextInt(); // capacity

        int ans = knapsack(wt, val, W, n);
        System.out.println(ans);
    }
}