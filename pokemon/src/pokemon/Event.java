package pokemon;


abstract public class Event {
	private long evtTime;
	
	public long getEvtTime() {
		return evtTime;
	}

	public void setEvtTime(long evtTime) {
		this.evtTime = evtTime;
	}

	public Event(long eventTime) {
		evtTime = eventTime;
		}
	
	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
		}
	
	abstract public void action();
	abstract public String description();
}