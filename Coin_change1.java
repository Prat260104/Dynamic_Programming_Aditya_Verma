// COIN CHANGE 1


class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        int INF = amount + 1;

        // 🔥 Single loop initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= amount; j++){
                if(j == 0) dp[i][j] = 0;
                else if(i == 0) dp[i][j] = INF;
            }
        }

        // DP fill
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= amount; j++){
                if(coins[i-1] <= j){
                    dp[i][j] = Math.min(
                        dp[i-1][j],
                        1 + dp[i][j - coins[i-1]]
                    );
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][amount] == INF ? -1 : dp[n][amount];
    }
}