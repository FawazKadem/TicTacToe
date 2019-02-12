
/**
 * @author fmahb
 *
 * Connector holds data and points to the next connector
 * Includes methods to get/set data stored and the next connector
 * @param <T>
 * 				Type of data connector holds
 */
public class Connector<T> {
	
	/**
	 * Instance variable representing next connector
	 */
	private Connector next;
	/**
	 * Instance variable representing the record stored within a connector
	 */
	private TTTRecord record;
	
	/**
	 * Constructor if both next node and data are given
	 * @param next next connector
	 * @param record data stored
	 */
	public Connector(Connector<T> next, TTTRecord record){
		this.next = next;
		this.record = record;
		
	}
	
	/**
	 * Constructor if no next is given
	 * @param record data stored
	 */
	public Connector(TTTRecord record){
		this.next = null;
		this.record = record;
		
	}

	/**
	 * @return next node
	 */
	public Connector<T> getNext() {
		return next;
	}

	/**
	 * sets next node of current node to given node
	 * @param next node to be set as next node
	 */
	public void setNext(Connector<T> next) {
		this.next = next;
	}

	/**
	 * @return record stored
	 */
	public TTTRecord getRecord() {
		return record;
	}

	/**
	 * sets record of this node to given record
	 * @param record data to be stored
	 */
	public void setRecord(TTTRecord record) {
		this.record = record;
	}
	
	
	
	
	
	
	
}
