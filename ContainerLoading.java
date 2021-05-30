class solution{
  public int ContainerLoading(int capacity, int weight[]){
    //Sort weight array in non-decreasing order
    Arrays.sort(weight);
    int N = weight.length;
    int containersLoaded = 0;
    //Select containers in order of weight
    int i = 1;
    while(i <= N && weight[i] <= capacity){
        containersLoaded++;
        capacity -= weight[i];
        i++;
    }
    return containersLoaded;
  }
  public static void main(args[] String){
    Scanner sc = new Scanner(System.in);
    int capacity = sc.nextInt();
    int n = sc.nextInt();
    int[] weight = new int[n];
    for(int iter = 0;iter < n;iter++){
        weight[iter] = sc.nextInt();
    }
    int numOfContainers = ContainerLoading(capacity, weight);
    System.out.println(numOfContainers);
  }
}
