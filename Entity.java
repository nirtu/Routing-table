public abstract class Entity {
	protected static final boolean DEBUG = false;
	
	/** Entity's ID */
	protected int myId = -1;
	
	/** Entity's neighbors */
	protected int[] myNeighbors = null; 
	
    /** Entity's distance table, corresponding to the interpretation of --
     *  from {@code this} node, to node i via node j, where i and j is a row
     *  and column index respectively.
     */
    protected int[][] distanceTable =
    		new int[NetworkSimulator.NUMENTITIES][NetworkSimulator.NUMENTITIES];

    /** Initialization, which start to send (broadcast) packets to neighbors */
    public abstract void rtinit();
    
    /** Automatically invoked by NetworkSimulator when a new packet is received
     *  by the entity.
     * @param p
     * 			Received packet.
     */
    public abstract void rtupdate(Packet p);
    
    /** String representation of the distance matrix. */
    public String toString() {
    	String nl = System.getProperty("line.separator");
    	StringBuilder sb = new StringBuilder();
    	sb.append(nl);
    	//sb.append("           via" + nl);
    	if (myId == 0)
    		sb.append(" D0 |   1   2   3" + nl);
    	else if (myId == 1)
    		sb.append(" D1 |   0   2   3" + nl);
    	else if (myId == 2)
    		sb.append(" D2 |   0   1   3" + nl);
    	else if (myId == 3)
    		sb.append(" D3 |   0   1   2" + nl);
    	sb.append("----+------------" + nl);
    	for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++) {
    		if (i == myId)
    			continue;
    		sb.append("   " + i + "|");
    		for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++) {
    			if (j == myId)
    				continue;
    			if (distanceTable[i][j] < 10)    
    				sb.append("   ");
    			else if (distanceTable[i][j] < 100)
    				sb.append("  ");
    			else 
    				sb.append(" ");
    			sb.append(distanceTable[i][j]);
			}//for
    		sb.append(nl);
    	}//for
    	return sb.toString();
    }//toString
}//Entity