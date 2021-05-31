package basicAlgo;

import java.util.Arrays;
import java.util.Comparator;

public class CakeThief {
	
	class Cake{
		int weight;
		int cost;
		int index;
		float costPerKg;
		Cake(int weight, int cost,int index){
			this.weight = weight;
			this.cost = cost;
			this.index = index;
			costPerKg = (float)cost/weight;
		}
	}
	
	private float greedyKnapsack(int[] weights, int[] costs, int capacity) {
		float totalProfit = 0.0f;
		Cake[] cakes = new Cake[weights.length];
		for(int iter = 0;iter < weights.length;iter++) {
			cakes[iter] = new Cake(weights[iter], costs[iter], iter);
		}
		
		//Sort the cakes according to the profit ratio
		//i.e costPerKg[i] >= costPerKg[i+1]
		Arrays.sort(cakes, new Comparator<Cake>() {
			@Override
			public int compare(Cake cake1, Cake cake2) {
				return Float.compare(cake2.costPerKg, cake1.costPerKg);
			}
		});
		
//		for(Cake cake : cakes) {
//			System.out.println(cake.weight+" "+cake.cost+" "+cake.index+" "+cake.costPerKg);
//		}
		
		float cakesPicked[] = new float[weights.length];

		int iter;
		for(iter = 0;iter < weights.length;iter++) {
			if(cakes[iter].weight > capacity) break;
				
			cakesPicked[cakes[iter].index] = 1.0f;
			capacity -= cakes[iter].weight;
			totalProfit += cakes[iter].cost;
			
		}
		
		if(iter < weights.length) {
			cakesPicked[cakes[iter].index] = (float)capacity/cakes[iter].weight;
			totalProfit += (cakesPicked[cakes[iter].index]*cakes[iter].cost);
		}
		
//		for(iter = 0;iter < weights.length;iter++) {
//			System.out.println(cakesPicked[iter]);
//		}
		
		return totalProfit;
	}
	
	public static void main(String[] args)
    {
        int[] weights = { 10,  20, 30 };
        int[] costs = { 60, 100, 120 };
        int capacity = 50;

        CakeThief objCT = new CakeThief();
        System.out.println(objCT.greedyKnapsack(weights, costs, capacity));
        
    }

}
