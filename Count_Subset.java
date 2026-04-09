// Count subset with a given sum

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();                 // number of elements
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) 
            arr[i] = sc.nextInt();            // input array

        int sum = sc.nextInt();               // target sum

        int[][] dp = new int[n + 1][sum + 1];

        // 🔥 Initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(j == 0) 
                    dp[i][j] = 1;  
                    // WHY: sum 0 → 1 way (empty subset always possible)

                else if(i == 0) 
                    dp[i][j] = 0;  
                    // WHY: 0 elements → positive sum banana impossible → 0 ways
            }
        }

        // 🔁 Main DP logic
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(arr[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] +  
                        // WHY: not take → jitne subsets bina current element ke sum j bana rahe the
                        // wo sab valid hain → unko include karna hai

                        dp[i - 1][j - arr[i - 1]];
                        // WHY: take → current element le liya
                        // to remaining sum (j - arr[i-1]) banana padega
                        // jitne ways uske the → un sab me current element add ho jayega

                        // 🔥 WHY "+" ?
                        // kyunki hume TOTAL number of subsets chahiye
                        // dono cases valid hain → dono ko ADD karte hain

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: current element bada hai → le nahi sakte
                    // to sirf previous ways hi valid hain
                }
            }
        }

        System.out.println(dp[n][sum]);       // final answer
    }
}