public class MyEntity extends Entity {
    /**
     * @param myId
     * 			Node ID.
     * @param myNeighbors
     * 			myNeighbors[i] is the distance from {@code this} node to the
     * 			i-th node.
     */
    public MyEntity(int myId, int[] myNeighbors) {
    	this.myId = myId;
    	this.myNeighbors = myNeighbors.clone();
    }//MyEntity
    
    /**
     * Initialization (first broadcast).
     */
    public final void rtinit() {
    	
    	for (int i=0; i<NetworkSimulator.NUMENTITIES; i++)
    		for(int j=0; j<NetworkSimulator.NUMENTITIES; j++)
    			this.distanceTable[i][j] = Project.INF;
    	
    	for(int i=0; i < NetworkSimulator.NUMENTITIES ; i++)
    		if(i == this.myId)
    			continue;
    		else 
    			this.distanceTable[i][i] = this.myNeighbors[i];
    	
    	for(int i=0; i< NetworkSimulator.NUMENTITIES ; i++) //use for lines in the distance table
    		for(int j=0 ; j<NetworkSimulator.NUMENTITIES; j++) // use for identify neighbor
    			if(i == this.myId || this.myNeighbors[j] == Project.INF || j ==this.myId)
    				continue;
    			else 
    			{
    				Packet p = new Packet(this.myId, j, i, this.distanceTable[i] );
    				NetworkSimulator.toLayer2(p);
    			}
    }//start
    
 
    /**
     *  Handles the received packet, this action includes an update of the
     *  distance table if any.
     *  @param p
     *  		Packet that have been received.
     */
    public final void rtupdate(Packet p) {   
    	if(p.getDvIndex() == this.myId)
    		return;
    	
    	if((p.getMinCost()+this.distanceTable[p.getSource()][p.getSource()]) < this.distanceTable[p.getDvIndex()][p.getSource()])
    	{
    		this.distanceTable[p.getDvIndex()][p.getSource()] = (p.getMinCost()+this.distanceTable[p.getSource()][p.getSource()]);
    		for(int i=0; i < NetworkSimulator.NUMENTITIES; i++)
    			if(i == this.myId || myNeighbors[i] == Project.INF)
    				continue;
    			else
    			{
    				Packet pac = new Packet(this.myId, i, p.getDvIndex(), this.distanceTable[p.getDvIndex()] );
    				NetworkSimulator.toLayer2(pac);
    			}    		
    	}
    }//update  
}//MyEntity













