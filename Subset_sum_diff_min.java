// Minimum subset sum difference

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();                // number of elements
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) 
            arr[i] = sc.nextInt();           // input array

        int total = 0;
        for(int x : arr) total += x;         // total sum of array

        // 🔥 CORE IDEA:
        // Let subset sum = s1
        // then other subset = total - s1
        // difference = |s1 - (total - s1)| = |total - 2*s1|
        // 👉 To minimize difference → s1 should be as close as possible to total/2

        boolean[][] dp = new boolean[n + 1][total + 1];

        // 🔥 Initialization (subset sum base)
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= total; j++){

                if(j == 0) 
                    dp[i][j] = true;  
                    // WHY: sum 0 always possible (empty subset)

                else if(i == 0) 
                    dp[i][j] = false; 
                    // WHY: no elements → positive sum impossible
            }
        }

        // 🔁 Fill DP (subset sum logic)
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= total; j++){

                if(arr[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] || 
                        // WHY: not take → already possible

                        dp[i - 1][j - arr[i - 1]];
                        // WHY: take → remaining sum check
                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: cannot take → skip
                }
            }
        }

        // 🔥 Reduction:
        // We only care about subset sums from 0 → total/2
        // WHY: beyond that, values repeat (mirror case)

        int minDiff = Integer.MAX_VALUE;

        for(int s1 = 0; s1 <= total / 2; s1++){

            if(dp[n][s1]){  
                // WHY: consider only those sums which are actually possible

                int diff = Math.abs(total - 2 * s1);
                // WHY: derived formula → |total - 2*s1|

                minDiff = Math.min(minDiff, diff);
                // WHY: we want minimum possible difference
            }
        }

        System.out.println(minDiff);
    }
}