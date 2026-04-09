// 0/1 knapsack tabulation

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] wt = new int[n];
        int[] val = new int[n];

        for(int i = 0; i < n; i++) wt[i] = sc.nextInt();
        for(int i = 0; i < n; i++) val[i] = sc.nextInt();

        int W = sc.nextInt();

        int[][] dp = new int[n + 1][W + 1];

       
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= W; j++){
                if(i == 0 || j == 0) dp[i][j] = 0;
            }
        }

       
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                if(wt[i - 1] <= j){
                    dp[i][j] = Math.max(
                        val[i - 1] + dp[i - 1][j - wt[i - 1]], // take
                        dp[i - 1][j]                          // not take
                    );
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][W]);
    }
}