
/**
 * SinglyLinkedList class is a list containing nodes which point to the node in
 * front of them and each contain data Includes methods to add to list, return
 * size, and to get and set first and last nodes
 * 
 * @author fmoham26
 *
 * @param <T>
 *            Type of data the list will contain (for this project, connector nodes)
 */
public class SinglyLinkedList<T> {

	/**
	 * Instance variable representing the first node of the list
	 */
	private Connector<T> head;
	/**
	 * Instance variable representing the last node of the list
	 */
	private Connector<T> tail;

	/**
	 * Method to add a connector to the end of list and set it to be the new
	 * tail
	 * 
	 * @param element
	 *            connector to be added
	 */
	public void addToEnd(Connector element) {

		Connector<T> newConnector = element;

		if (head != null) {
			tail.setNext(newConnector);
			tail = newConnector;
		}
		// if list is empty sets both tail and head to be the given connector
		else {
			head = tail = newConnector;
		}
	}

	/**
	 * @return head (first node of list)
	 */
	public Connector<T> getHead() {
		return head;
	}

	/**
	 * Sets head to given node
	 * 
	 * @param head
	 */
	public void setHead(Connector<T> head) {
		this.head = head;
	}

	/**
	 * @return tail (last node)
	 */
	public Connector<T> getTail() {
		return tail;
	}

	/**
	 * Sets node to given node
	 * 
	 * @param tail
	 */
	public void setTail(Connector<T> tail) {
		this.tail = tail;
	}

	
	/**
	 * @return number of nodes in list
	 */
	public int size() {
		int size = 0;
		if (head != null)
			size++;

		for (Connector c = head; c.getNext() != null; c = c.getNext()) {
			size++;
		}
		return size;
	}

}
