public class Project {
	// Infinity weight
	public static final int INF = 999;
	
    public final static void main(String[] argv) {
    	int[][] cost = {
//    		{0,1,3,7},
//			{1,0,1,INF},
//			{3,1,0,2},
//			{7,INF,2,0}
    			
    			{0,3,23,INF},
    			{3,0,2,INF},
    			{23,2,0,5},
    			{INF,INF,5,0}
    	};//cost
			
		NetworkSimulator simulator = new NetworkSimulator(cost);
        simulator.runSimulator();
        simulator.printRT();
    }//main
}//Project