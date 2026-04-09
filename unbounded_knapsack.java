// unbounded knapsack

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();              // number of items
        int[] wt = new int[n];
        int[] val = new int[n];

        for(int i = 0; i < n; i++) 
            wt[i] = sc.nextInt();          // weights

        for(int i = 0; i < n; i++) 
            val[i] = sc.nextInt();         // values

        int W = sc.nextInt();              // capacity

        int[][] dp = new int[n + 1][W + 1];

        // 🔥 Initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= W; j++){

                if(i == 0 || j == 0) 
                    dp[i][j] = 0;
                    // WHY: no items or no capacity → value = 0
            }
        }

        // 🔁 Main DP logic (UNBOUNDED 🔥)
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){

                if(wt[i - 1] <= j){

                    dp[i][j] = Math.max(
                        dp[i - 1][j],  
                        // WHY: not take → move to previous item

                        val[i - 1] + dp[i][j - wt[i - 1]]
                        // 🔥 WHY dp[i][...] ?
                        // because we can reuse same item again (infinite supply)
                        // so we stay on same row (i)
                    );

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: cannot take → skip
                }
            }
        }

        System.out.println(dp[n][W]);
    }
}