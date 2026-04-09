// Rod cutting algo


import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();              // rod length
        int[] price = new int[n];

        for(int i = 0; i < n; i++) 
            price[i] = sc.nextInt();       // price of length (i+1)

        int[] length = new int[n];
        for(int i = 0; i < n; i++) 
            length[i] = i + 1;             // lengths: 1,2,3,...,n

        int[][] dp = new int[n + 1][n + 1];

        // 🔥 Initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){

                if(i == 0 || j == 0) 
                    dp[i][j] = 0;
                    // WHY: no pieces or length 0 → profit = 0
            }
        }

        // 🔁 Main DP logic (UNBOUNDED 🔥)
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){

                if(length[i - 1] <= j){

                    dp[i][j] = Math.max(
                        dp[i - 1][j],  
                        // WHY: not cut using this length

                        price[i - 1] + dp[i][j - length[i - 1]]
                        // 🔥 WHY same row (i)?
                        // because we can use same cut length multiple times
                    );

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: cannot cut → skip
                }
            }
        }

        System.out.println(dp[n][n]);      // max profit
    }
}