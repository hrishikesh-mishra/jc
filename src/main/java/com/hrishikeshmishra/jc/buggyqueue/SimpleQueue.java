package com.hrishikeshmishra.jc.buggyqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrishikesh.mishra
 */
public class SimpleQueue<E> implements BlockingQueue<E> {

    private List<E> mList = new ArrayList<E>();

    public void put(E e) throws InterruptedException {
        mList.add(e);
    }

    public E take() throws InterruptedException {
        return mList.remove(0);
    }

    /** Rest Method are jus placeholder **/
    /** --------------------------------------------------- **/
    public boolean add(E e) {
        return false;
    }

    public boolean offer(E e) {
        return false;
    }



    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }



    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    public int remainingCapacity() {
        return 0;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    public E remove() {
        return null;
    }

    public E poll() {
        return null;
    }

    public E element() {
        return null;
    }

    public E peek() {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }
}
