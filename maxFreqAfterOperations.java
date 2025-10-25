import java.util.Arrays;
import java.util.Scanner;

class Solution {

    public int maxFrequency(int[] nums, int k, int ops) {

        Arrays.sort(nums);
        int n = nums.length;

        int l1 = 0, r1 = 0, cnt1 = 0;
        int l2 = 0, cnt2 = 0;
        int same = 0, prev = Integer.MIN_VALUE;
        int res = 0;

        for (int x : nums) {

            // Count consecutive identical numbers
            if (x == prev) same++;
            else {
                prev = x;
                same = 1;
            }

            // Adjust left side for ±k window
            while (nums[l1] < x - k) {
                cnt1--;
                l1++;
            }

            // Extend right side for ±k window
            while (r1 < n && nums[r1] <= x + k) {
                cnt1++;
                r1++;
            }

            // Option 1: within ±k range
            res = Math.max(res, same + Math.min(cnt1 - same, ops));

            // Option 2: within ±2k range
            cnt2++;
            while (nums[l2] < x - 2L * k) {
                cnt2--;
                l2++;
            }

            res = Math.max(res, Math.min(cnt2, ops));
        }

        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        System.out.print("Enter numOperations: ");
        int ops = sc.nextInt();

        Solution sol = new Solution();
        int result = sol.maxFrequency(nums, k, ops);

        System.out.println("Maximum frequency: " + result);

        sc.close();
    }
}
