// Equal Sum partition

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) 
            arr[i] = sc.nextInt();

        int total = 0;
        for(int x : arr) total += x;

        // 🔥 Step 1: check even sum
        if(total % 2 != 0){
            System.out.println(false);
            // WHY: odd sum ko 2 equal parts me divide hi nahi kar sakte
            return;
        }

        int sum = total / 2;

        boolean[][] dp = new boolean[n + 1][sum + 1];

        // 🔥 Initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(j == 0) 
                    dp[i][j] = true;  
                    // WHY: sum 0 always possible (empty subset)

                else if(i == 0) 
                    dp[i][j] = false; 
                    // WHY: no elements → positive sum impossible
            }
        }

        // 🔁 Main logic (same as subset sum)
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){

                if(arr[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] || 
                        // WHY: already possible without current element

                        dp[i - 1][j - arr[i - 1]];
                        // WHY: include current element → remaining sum check

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: cannot include → skip
                }
            }
        }

        System.out.println(dp[n][sum]);
    }
}