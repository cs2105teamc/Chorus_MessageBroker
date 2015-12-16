package com.chorus.database;

import java.util.Enumeration;
import java.util.Hashtable;
/**
 * 
 * @author garfijam
 * @since 7 Dec 2015
 * @version 1.0
 *
 * @param <T>
 */
public abstract class ObjectPool<T> {
	private long expirationTime;

	private Hashtable<T, Long> locked, unlocked;

	/**
	 ** ==================================================== 
	 ** ================= Constructors =====================
	 ** ====================================================
	 **/
	
	/**
	 * 
	 */
	public ObjectPool() {
		expirationTime = 30000; // 30 seconds
		locked = new Hashtable<T, Long>();
		unlocked = new Hashtable<T, Long>();
	}

	/**
	 ** ==================================================== 
	 ** =================== Methods =======================
	 ** ====================================================
	 **/
	/**
	 * <h2>create</h2>
	 * @return
	 */
	protected abstract T create();

	/**
	 * <h2>validate</h2>
	 * @param o
	 * @return
	 */
	public abstract boolean validate(T o);

	/**
	 * <h2>expire</h2>
	 * @param o
	 */
	public abstract void expire(T o);

	/**
	 * <h2>checkOut</h2>
	 * @return
	 */
	public synchronized T checkOut() {
		long now = System.currentTimeMillis();
		T t;
		if (unlocked.size() > 0) {
			Enumeration<T> e = unlocked.keys();
			while (e.hasMoreElements()) {
				t = e.nextElement();
				if ((now - unlocked.get(t)) > expirationTime) {
					// object has expired
					unlocked.remove(t);
					expire(t);
					t = null;
				} else {
					if (validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return (t);
					} else {
						// object failed validation
						unlocked.remove(t);
						expire(t);
						t = null;
					}
				}
			}
		}
		// no objects available, create a new one
		t = create();
		locked.put(t, now);
		return (t);
	}

	/**
	 * <h2>checkIn</h2>
	 * @param t
	 */
	public synchronized void checkIn(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}
}
