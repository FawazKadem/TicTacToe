import java.util.*;

/**
 * TTTDictionary represents a hashtable made up of an array of singly linked
 * lists
 * 
 * @author fmahb
 *
 */

public class TTTDictionary implements TTTDictionaryADT {

	/**
	 * size size of array itemsInDict number of items contained in dictionary
	 */
	private int size;

	private int itemsInDict = 0;

	public int hashFuncCount = 0;

	private SinglyLinkedList<Connector>[] hashtable;

	/**
	 * Constructs dictionary with given size using a hashtable
	 * 
	 * @param size
	 */
	public TTTDictionary(int size) {
		SinglyLinkedList<Connector>[] hashtable = new SinglyLinkedList[size];
		this.size = size;
		this.hashtable = hashtable;

	}

	/**
	 * put a new record into the dictionary
	 * 
	 * @param record
	 *            to be added
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		Connector newConnector;

		if (get(record.getConfiguration()) != null) {

			throw new DuplicatedKeyException("This Configuration already exists within the dictionary");
		}

		int hashIndex = hash(record.getConfiguration());

		if ((hashtable[hashIndex]) != null) {

			newConnector = new Connector(record);
			hashtable[hashIndex].addToEnd(newConnector);
			itemsInDict++;
			return 1;
		} else {

			hashtable[hashIndex] = new SinglyLinkedList();

			newConnector = new Connector(record);

			hashtable[hashIndex].addToEnd(newConnector);
			itemsInDict++;

			return 0;
		}

	}

	/**
	 * remove given configuration from dictionary
	 * 
	 * @param config
	 *            to be removed
	 */
	public void remove(String config) throws InexistentKeyException {

		if (get(config) != null) {
			int indexToRemove = hash(config);

			if (hashtable[indexToRemove].size() == 1) {
				hashtable[indexToRemove] = null;
			} else {
				// if removed config is stored in head
				for (Connector c = hashtable[indexToRemove].getHead(); c.getNext() != null; c = c.getNext()) {
					if (c.getRecord().getConfiguration().equals(config)) {
						if (hashtable[indexToRemove].getHead() == c) {
							hashtable[indexToRemove].setHead(c.getNext());
							itemsInDict--;
							break;
						}

						if (hashtable[indexToRemove].getTail() == c) {
							if (hashtable[indexToRemove].size() == 2) {
								hashtable[indexToRemove].setTail(hashtable[indexToRemove].getHead());
								hashtable[indexToRemove].getHead().setNext(null);
								itemsInDict--;
								break;
							}

							else {
								Connector currentNode = hashtable[indexToRemove].getHead();
								while ((currentNode.getNext()) != null) {
									currentNode = currentNode.getNext();
									if (currentNode.getNext().getNext() == null) {
										hashtable[indexToRemove].setTail(currentNode);
										currentNode.setNext(null);

									}
								}
							}

							itemsInDict--;
							break;
						}

						Connector currentNode = hashtable[indexToRemove].getHead();
						while ((currentNode.getNext()) != c) {
							currentNode = currentNode.getNext();
							if (currentNode.getNext() == c) {
								currentNode.setNext(c.getNext());
								itemsInDict--;
								break;

							}
						}
						break;
					}

				}
			}

		}

		else {

			throw new InexistentKeyException("That does not exist in this dictionary");
		}
	}

	/**
	 * search for given record
	 * 
	 * @return record being searched for or null if it doesn't exist
	 */
	public TTTRecord get(String config) {
		int indexToFind = hash(config);

		if (hashtable[indexToFind] != null) {
			//
			// if (hashtable[indexToFind].size() == 1) {
			//
			// return hashtable[indexToFind].getHead().getRecord();
			//
			// }

			for (Connector c = hashtable[indexToFind].getHead(); c != null; c = c.getNext()) {
				if (c.getRecord().getConfiguration().equals(config)) {
					return c.getRecord();
				}
			}

		}

		return null;

	}

	/**
	 * @return number of elements in dictionary
	 */
	public int numElements() {

		return itemsInDict;
	}

	/**
	 * hash function to hash configuration
	 * 
	 * @param config
	 * @return hash of config
	 */
	private int hash(String config) {

		int hashValue = 0;
		final int PRIME_MULTIPLIER = 193;

		for (int i = config.length() - 1; i >= 0; i--) {
			hashValue = (hashValue * PRIME_MULTIPLIER + config.charAt(i)) % size;
		}

		return hashValue;

	}
}
