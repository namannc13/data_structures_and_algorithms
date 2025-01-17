// TC: O(n)
// SC: O(1)

public class JumpGameII {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
    public static int jump(int[] nums) {
       int jumps = 0;
       int l = 0, r = 0;
       while(r < nums.length - 1){
           int maxReach = 0;
           for(int i = l; i <= r; i++){
            maxReach = Math.max(maxReach, i + nums[i]);
           }
           l = r + 1;
           r = maxReach;
           jumps++;
       }
       return jumps;
    }
}

