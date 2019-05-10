package pokemon;


abstract public class Event {
	private double priority;
	
	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public Event(double priority) {
		this.priority = priority;
		}
	
	abstract public void action();
	abstract public String description();
}