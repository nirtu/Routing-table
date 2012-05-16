import java.util.Vector;

public class EventListImpl implements EventList {
    private Vector<Event> data = null;
    
    public EventListImpl() {
        data = new Vector<Event>();
    }//EventListImpl
    
    public boolean add(Event e) {
        data.addElement(e);
        return true;
    }//add
    
    public Event removeNext() {
        if (data.isEmpty())
            return null;
        int firstIndex = 0;
        double first = ((Event)data.elementAt(firstIndex)).getTime();
        for (int i = 0; i < data.size(); i++)
            if (((Event)data.elementAt(i)).getTime() < first) {
                first = ((Event)data.elementAt(i)).getTime();
                firstIndex = i;
            }//if
        Event next = (Event)data.elementAt(firstIndex);
        data.removeElement(next);
        return next;
    }//removeNext
    
    public String toString() { return data.toString(); }
    
    public double getLastPacketTime(int entityFrom, int entityTo) {
        double time = 0.0;
        for (int i = 0; i < data.size(); i++)
            if ((((Event)(data.elementAt(i))).getType() == 
                                           NetworkSimulator.FROMLAYER2) &&
                (((Event)(data.elementAt(i))).getEntity() == entityTo) &&
                (((Event)(data.elementAt(i))).getPacket().getSource() ==
                                           entityFrom))
                time = ((Event)(data.elementAt(i))).getTime();
        return time;
    }//getLastPacketTime
}//EventListImpl