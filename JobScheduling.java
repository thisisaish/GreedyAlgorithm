package basicAlgo;

import java.util.Arrays;
import java.util.Comparator;

import basicAlgo.CakeThief.Cake;

public class JobScheduling {

	class Job{
		int startTime;
		int endTime;
		int profit;
		Job(int s, int e, int p){
			startTime = s;
			endTime = e;
			profit = p;
		}
	}
	public int maxProfit(int[] startTime, int[] endTime, int[] profit) {
		int len = profit.length;
		Job[] jobs = new Job[len];
		for(int iter = 0;iter < len;iter++) {
			jobs[iter] = new Job(startTime[iter], endTime[iter], profit[iter]);
		}
		
		//Sort the jobs such that the end time of the first job
		// is greater than the end time of the second job
		Arrays.sort(jobs, new Comparator<Job>() {
			@Override
			public int compare(Job job1, Job job2) {
				return Float.compare(job1.endTime, job2.endTime);
			}
		});
		
		
		int[] dp = new int[len];
		dp[0] = jobs[0].profit;
		for(int iter = 1;iter < len;iter++) {
			int temp = 0;
			int low = 0;
			int high = iter - 1;
			while(low < high) {
				int mid = low + (high - low + 1)/2;
				if(jobs[mid].endTime <= jobs[iter].startTime)
					low = mid;
				else
					high = mid - 1;
			}
			dp[iter] = jobs[iter].profit;
			if(jobs[low].endTime <= jobs[iter].startTime)
				dp[iter] += dp[low]; 
			
			dp[iter] = Math.max(dp[iter], dp[iter - 1]);
		}
		return dp[len - 1];
	}
	
	public static void main(String[] args) {
		int[] startTime = {1,2,3,4,6};
		int[] endTime = {3,5,10,6,9};
		int[] profit = {20,20,100,70,60};
		
		JobScheduling obj = new JobScheduling();
		System.out.println(obj.maxProfit(startTime, endTime, profit));
	}
	
}
