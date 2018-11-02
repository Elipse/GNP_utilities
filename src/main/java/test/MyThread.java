package test;

import java.awt.EventQueue;
import java.util.function.Consumer;

public class MyThread extends Thread {

    volatile private Thread waiter;
    volatile private int steps;
    volatile private int delay;
    volatile private boolean threadSuspended;
    volatile private Consumer<Boolean> consumer;

    public MyThread(int delay, int steps, Consumer<Boolean> consumer) {
	super();
	this.waiter = this;
	//
	this.steps = steps;
	this.delay = delay;
	this.consumer = consumer;
    }

    public void setTimer(int delay, int steps, Consumer<Boolean> consumer) {
	this.steps = steps;
	this.delay = delay;
	this.consumer = consumer;
	// Solo si está en sleep() tiene efecto y sentido la sentencia.
	interrupt();
	// Si si está en wait() tiene efecto y sentido la sentencia.
	doResume();
    }

    public void doSuspend() {
	// En cuanto despierte, si está dormido, se suspenderá con wait().
	this.threadSuspended = true;
    }

    public synchronized void doResume() {
	// Solo si está en wait() tienen efecto y sentido las sentencias.
	this.threadSuspended = false;
	notify();
    }

    public synchronized void doStop() {
	waiter = null;
	// Por si está durmiendo...
	interrupt();
	// Por si está suspendido...
	notify();
    }

    @Override
    public void run() {

	outter: while (waiter == this) {
	    try {

		int limit = steps;
		int interval = delay;

		System.out.println(Thread.currentThread() + " Arranca: "
			+ System.currentTimeMillis() + " ");

		for (int i = 0; i < limit; i++) {
		    Thread.sleep(interval);
		}

		if (threadSuspended) {
		    synchronized (this) {
			while (threadSuspended && waiter == this)
			    wait();
		    }
		}

		updateUI(true);
	    } catch (InterruptedException e) {
		updateUI(false);
		// La interrupción fue para reiniciar el temporizador
		if (waiter == this)
		    continue outter;
	    }
	}

	System.out.println("Se paró el Thread...");
    }

    private void updateUI(boolean mode) {
	EventQueue.invokeLater(() -> consumer.accept(mode));
    }
}
