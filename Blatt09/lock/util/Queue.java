package lock.util;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * An implementation of a Queue with a limited capacity.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 */
public class Queue<E> {

	private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
	private final Lock rLock = rwlock.readLock();
	private final Lock wLock = rwlock.writeLock();
	private final Condition cond = wLock.newCondition();


	/**
	 * Holds the objects stored by this {@code Queue}.
	 */
	private Object[] objects;
	/**
	 * index of the first instance stored by this {@code Queue}.
	 */
	private int first;
	/**
	 * number of elements contained in this {@code Queue}
	 */
	private int size;

	/**
	 * @param capacity
	 *            number of objects which may be hold in this {@code Queue}.
	 */
	public Queue(int capacity) {
		this.objects = new Object[capacity];
		this.first = 0;
		this.size = 0;
	}

	/**
	 * Inserts {@code o} at the first free position of this {@code Queue}
	 * 
	 * @param o
	 *            object to be inserted
	 * @throws InterruptedException
	 * 
	 * @throws RuntimeException
	 *             if this {@code Queue} is already full
	 */
	public void enq(E o) throws InterruptedException {
		try {
			wLock.lock();
			while(this.full()) {
				cond.await();
			}
			objects[(first + size) % objects.length] = o;
			size++;
		} finally {
			cond.signalAll();
			wLock.unlock();
		}
	}

	/**
	 * Removes the object at the first position of this {@code Queue}.
	 * 
	 * @return the removed object
	 * @throws InterruptedException
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E deq() throws InterruptedException {
		try {
			wLock.lock();
			while(this.empty()) {
				cond.await();
			}
			E o = (E) objects[first];
			first = (first + 1) % objects.length;
			size--;
			return o;
		} finally {
			cond.signalAll();
			wLock.unlock();
		}
	}

	/**
	 * Returns the object at the first position of this {@code Queue}
	 * 
	 * @return the first element of this {@code Queue}
	 * @throws NoSuchElementException
	 *             if this {@code Queue} is already empty
	 */
	public E front() {
		try {
			rLock.lock();
			if (this.empty()) {
				throw new NoSuchElementException();
			}
			return (E) objects[first];
		} finally {
			rLock.unlock();
		}

	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is empty
	 */
	public boolean empty() {
		try {
			rLock.lock();
			return this.size == 0;
		} finally {
			rLock.unlock();
		}
	}

	/**
	 * 
	 * @return {@code true} if this {@code Queue} is full
	 */
	public boolean full() {
		try {
			rLock.lock();
			return this.size == objects.length;
		} finally {
			rLock.unlock();
		}
	}

}
