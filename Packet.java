public class Packet {
    private int source = 0;
    private int dest = 0;
    private int dvIndex = 0;
    private int[] mincost = null;
    
    public static final int ENTITY = 0;
    public static final int COST = 1;
    
    public Packet(Packet p) {
        source = p.source;
        dest = p.dest;
        dvIndex = p.dvIndex;
        mincost = p.mincost.clone();
    }//Packet
    
    public Packet(int s, int d, int dvI, int[] dv) {
        source = s;
        dest = d;
        dvIndex = dvI;
        mincost = new int[NetworkSimulator.NUMENTITIES];
        if (dv.length != NetworkSimulator.NUMENTITIES) {
            System.out.println("Packet(): Invalid data format.");
            System.exit(1);
        }//if
        mincost = dv.clone();
    }//Packet
    
    public int getSource() { return source; }
    
    public int getDest() { return dest; }
    
    public int getCost(int ent) { return mincost[ent];  }
    
    public int getDvIndex() { return dvIndex; }

    public int getMinCost() {
    	int min = Project.INF;
    	for (int i = 0; i < mincost.length; ++i)
    		if (min > mincost[i])
    			min = mincost[i];
    	return min;
    }//getMinCost
    
    public String toString() {
        String str;
        str = "dest: " + dest + "  source: " + source + "  dv-index: "
        		+ dvIndex + "  dv: [";
        for (int i = 0; i < NetworkSimulator.NUMENTITIES - 1; i++)
            str += (getCost(i) + ",");
        str += (getCost(NetworkSimulator.NUMENTITIES - 1) + "]");
        return str;
    }//toString    
}//Packet