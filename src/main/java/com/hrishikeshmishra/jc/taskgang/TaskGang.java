package com.hrishikeshmishra.jc.taskgang;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public abstract class TaskGang<E> implements Runnable {

    private volatile List<E> input = null;

    private Executor executor = null;

    private final AtomicLong currentCycle = new AtomicLong(0);

    protected List<E> getInput() {
        return input;
    }

    protected void setInput(List<E> input) {
        this.input = input;
    }

    protected Executor getExecutor() {
        return executor;
    }

    protected void setExecutor(Executor executor) {
        this.executor = executor;
    }

    protected long incrementCycle() {
        return currentCycle.incrementAndGet();
    }

    protected long currentCycle() {
        return currentCycle.get();
    }

    protected abstract List<E> getNextInput();

    protected void initiateHook(int inputSize) {

    }

    protected abstract void initiateTaskGang(int inputSize);

    protected boolean advanceTaskToNextCycle() {
        return false;
    }

    protected abstract void awaitTasksDone();

    protected void taskDone(int index) throws IndexOutOfBoundsException {

    }

    protected abstract boolean processInput(E inputData);

    public void run() {

        setInput(getNextInput());

        initiateTaskGang(getInput().size());

        awaitTasksDone();
    }

    protected Runnable makeTask(final int index) {

        return new Runnable() {
                public void run() {

                    try {
                        E element = getInput().get(index);
                        if (processInput(element)) taskDone(index);
                        else return;
                    } catch (IndexOutOfBoundsException e) {}
                }
        };
    }
}
