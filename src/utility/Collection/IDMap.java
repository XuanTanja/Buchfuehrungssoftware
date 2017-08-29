package utility.Collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * IDMap ist �hnlich zu einer HashMap, die jedoch mehrere Buchungss�tze mit der
 * gleichen ID aufnehmen kann.
 *
 */
public class IDMap<K, V> implements Serializable {

	private HashMap<K, ArrayList<V>> container;

	public IDMap() {
		container = new HashMap<>();
	}

	/**
	 * <i><b>Leeren der IDMap</b></i><br>
	 * <br>
	 * Entfernt alle Inhalte aus der IDMap. <br>
	 */
	public void clear() {
		container = new HashMap<>();
	}

	/**
	 * <i><b>Schl�ssel in IDMap vorhanden?</b></i><br>
	 * <br>
	 * Pr�ft, ob der Key in der IDMap vorhanden ist. <br>
	 * 
	 * @param key
	 *            - zu �berpr�fender Schl�ssel
	 * 
	 * @return Liefert, ob der Schl�ssel vorhanden ist (true) oder nicht
	 *         (false).
	 */
	public boolean containsKey(K key) {
		return container.containsKey(key);

	}

	/**
	 * <i><b>Wert in IDMap vorhanden?</b></i><br>
	 * <br>
	 * Pr�ft, ob ein bestimmter Wert in der IDMap vorhanden ist. <br>
	 * 
	 * @param value
	 *            - zu �berpr�fender Wert
	 * 
	 * @return Liefert, ob der Wert vorhanden ist (true) oder nicht (false).
	 */
	public boolean containsValue(Object value) {
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			if (it.next().contains(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <i><b>zugeh�rige Wert zum Key entnehmen</b></i><br>
	 * <br>
	 * Gibt den n-ten Wert zur�ck, der dem Schl�ssel zugeordnet wurde. <br>
	 * 
	 * @param key
	 *            - Schl�ssel des Wertes
	 * @param index
	 *            - Position des Wertes
	 * 
	 * @return der Wert an der Position index
	 */
	public V get(K key, int index) {
		return container.get(key).get(index);
	}

	/**
	 * <i><b>zugeh�rige Werte zum Key entnehmen</b></i><br>
	 * <br>
	 * Gibt alle Werte zur�ck, die dem Schl�ssel zugeordnet wurden. <br>
	 * 
	 * @param key
	 *            - Schl�ssel des Wertes
	 * 
	 * @return alle zugeordneten Werte
	 */
	public ArrayList<V> getAll(K key) {
		return container.get(key);
	}

	/**
	 * <i><b>leere IDmap?</b></i><br>
	 * <br>
	 * �berpr�ft, ob die IDMap leer ist. <br>
	 * 
	 * @return Ob die IDMap leer ist (true) oder nicht (false).
	 */
	public boolean isEmpty() {
		return container.isEmpty();
	}

	/**
	 * <i><b>KeySet aller vorhandenen Schl�ssel</b></i><br>
	 * <br>
	 * Gibt alle Schl��sel zur�ck, die der IDMap hinzugef�gt wurden. <br>
	 * 
	 * @return alle Schl�ssel der IDMap
	 */
	public Set<K> keySet() {
		return container.keySet();
	}

	/**
	 * <i><b>Hinzuf�gen eines neuen Wertes</b></i><br>
	 * <br>
	 * F�gt der IDMap einen Wert hinzu, der einem bestimmten Schl�ssel
	 * zugeordnet wird. Falls der Schl�ssel noch nicht vorhanden ist, wird er
	 * neu hinzugef�gt. <br>
	 * 
	 * @param key
	 *            - Schl�ssel des Wertes
	 * @param value
	 *            - der Wert, der der IDMap hinzugef�gt werden soll.
	 */
	public void put(K key, V value) {
		if (containsKey(key)) {
			container.get(key).add(value);
		} else {
			ArrayList<V> newlist = new ArrayList<>();
			newlist.add(value);
			container.put(key, newlist);
		}
	}

	/**
	 * <i><b>Hinzuf�gen neuer Werte</b></i><br>
	 * <br>
	 * F�gt der IDMap meherere Werte hinzu, die alle dem gleichen Schl�ssel
	 * zugeordnet werden sollen. <br>
	 * 
	 * @param key
	 *            - Schl�ssel des Wertes
	 * @param value
	 *            - Liste aller Werte, die der IDMap hinzugef�gt werden sollen.
	 */
	public void putAll(K key, ArrayList<V> value) {
		container.put(key, value);
	}

	/**
	 * <i><b>L�schen eines Schl�ssels</b></i><br>
	 * <br>
	 * L�scht einen bestimmten Schl�ssel aus der IDmap inklusive seiner Werte.
	 * <br>
	 * 
	 * @param key
	 *            - Schl�ssel, der gel�scht werden soll
	 * 
	 * @return Liste aller Werte, die dem Schl�ssel zugeordnet wurden.
	 */
	public ArrayList<V> remove(K key) {
		return container.remove(key);
	}

	/**
	 * <i><b>L�nge der IDMap</b></i><br>
	 * <br>
	 * Gibt die Anzahl aller in der IDMap vorhandenen Werte zur�ck. <br>
	 * 
	 * @return L�nge der IDMap
	 */
	public int size() {
		int size = 0;
		Iterator<ArrayList<V>> it = container.values().iterator();
		while (it.hasNext()) {
			size += it.next().size();
		}
		return size;
	}

	/**
	 * <i><b>Alle Werte in einer Collection zur�ckgeben</b></i><br>
	 * <br>
	 * Gibt alle Werte in Form einer Collection zur�ck. <br>
	 * 
	 * @return Collection mit allen Werten
	 */
	public Collection<ArrayList<V>> values() {
		return container.values();
	}

}
