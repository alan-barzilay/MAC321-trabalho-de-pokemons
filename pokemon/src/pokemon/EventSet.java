package pokemon;
class EventSet {
	private Event[] events = new Event[2];
	
	public void add(Event e) {
		if(events[0] == null) {	
			events[0] = e;
		}
		else {
			events[1] = e;
		}		
	}

	public Event[] getEvents() {
		return events;
	}
	public void setEvents(Event[] events) {
		this.events = events;
	}

	public void clearEvents(){
		Event[] events_vazio = new Event[2];
		events_vazio[0]= null;
		events_vazio[1]= null;
		setEvents(events_vazio);
	}
	
	
}