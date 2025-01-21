import java.util.Arrays;

// TC - O(nlogn + n*(maxDeadline))
// SC - O(maxDeadline)

class JobSequencingProblem {
    static class Job {
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
    public static void main(String[] args) {
        int[] job = {1, 2, 3, 4, 5}; 
        int[] deadline = {2, 2, 3, 3, 4};
        int[] profit = {100, 19, 27, 25, 15};

        int n = job.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(job[i], deadline[i], profit[i]);
        }

        int maxProfit = findMaxProfit(jobs, n);

        System.out.println();
        System.out.println("Maximum profit: " + maxProfit);
        System.out.println();
    }
    public static int findMaxProfit(Job[] jobs, int n) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int[] result = new int[n];
        Arrays.fill(result, -1);
        int profit = 0;

        for(int i = 0 ;i < n; i++){
            for(int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--){
                if(result[j] == -1){
                    result[j] = jobs[i].id;
                    profit += jobs[i].profit;
                    break;
                }
            }
        }
        return profit;
    }
}