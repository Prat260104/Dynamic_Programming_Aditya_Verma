// count subset sum with given difference

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();                 // number of elements
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) 
            arr[i] = sc.nextInt();            // input array

        int diff = sc.nextInt();              // given difference

        int total = 0;
        for(int x : arr) total += x;          // total sum

        // 🔥 CORE LOGIC:
        // sum1 - sum2 = diff
        // sum1 + sum2 = total
        // => 2*sum1 = total + diff
        // => sum1 = (total + diff)/2

        // ❗ Invalid case
        if((total + diff) % 2 != 0){
            System.out.println(0);
            // WHY: cannot divide into integer subset sum
            return;
        }

        int sum = (total + diff) / 2;

        int[][] dp = new int[n + 1][sum + 1];

        // 🔥 Initialization (count DP)
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(j == 0) 
                    dp[i][j] = 1;  
                    // WHY: sum 0 → 1 way (empty subset)

                else if(i == 0) 
                    dp[i][j] = 0;  
                    // WHY: no elements → no way
            }
        }

        // 🔁 Main DP logic
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(arr[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] +  
                        // WHY: not take → previous ways

                        dp[i - 1][j - arr[i - 1]];
                        // WHY: take → remaining sum ways

                        // 🔥 WHY "+" ?
                        // counting problem → total ways = sum of both choices

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: cannot take → only previous ways
                }
            }
        }

        System.out.println(dp[n][sum]);
    }
}