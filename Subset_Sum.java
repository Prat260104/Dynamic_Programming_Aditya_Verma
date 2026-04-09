// Subset Sum

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) 
            arr[i] = sc.nextInt();

        int sum = sc.nextInt();

        boolean[][] dp = new boolean[n + 1][sum + 1];

        // 🔥 Initialization
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= sum; j++){

                if(j == 0) 
                    dp[i][j] = true;  
                    // WHY: sum 0 always possible → kuch bhi na lo (empty subset)

                else if(i == 0) 
                    dp[i][j] = false; 
                    // WHY: 0 elements se positive sum banana impossible hai
            }
        }

        // 🔁 Main DP logic
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){

                if(arr[i - 1] <= j){

                    dp[i][j] = 
                        dp[i - 1][j] || 
                        // WHY: agar bina current element ke hi sum j ban raha tha
                        // to hume current element lene ki zarurat hi nahi

                        dp[i - 1][j - arr[i - 1]];
                        // WHY: agar current element lete hain,
                        // to baaki sum = (j - current element)
                        // aur wo previous elements se ban jana chahiye

                } 
                else {

                    dp[i][j] = dp[i - 1][j];
                    // WHY: current element bada hai → le hi nahi sakte
                    // to sirf previous result hi valid hai
                }
            }
        }

        System.out.println(dp[n][sum]);
    }
}