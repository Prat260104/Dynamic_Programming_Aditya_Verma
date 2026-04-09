// target sum

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();               // number of elements
        int[] nums = new int[n];

        for(int i = 0; i < n; i++) 
            nums[i] = sc.nextInt();         // input array

        int target = sc.nextInt();          // target value

        int total = 0;
        for(int x : nums) total += x;       // total sum

        // 🔥 CORE LOGIC:
        // S1 - S2 = target
        // S1 + S2 = total
        // => 2*S1 = total + target
        // => S1 = (total + target)/2

        // ❗ Invalid cases
        if((total + target) % 2 != 0 || total < Math.abs(target)){
            System.out.println(0);
            // WHY:
            // 1. odd → cannot divide into integer subset
            // 2. target > total → impossible
            return;
        }

        int sum = (total + target) / 2;

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

                if(nums[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] +  
                        // WHY: not take → previous ways

                        dp[i - 1][j - nums[i - 1]];
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