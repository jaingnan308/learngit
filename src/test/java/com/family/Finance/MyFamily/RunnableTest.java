package com.family.Finance.MyFamily;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class RunnableTest implements Runnable {
	int time;
	String name;

	public RunnableTest(int x, String n) {
		time = x;
		name = n;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(name + ":"
						+ new Date(System.currentTimeMillis()));
				Thread.sleep(time);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static public void main(String args[]) {
		Thread t1 = new Thread(new RunnableTest(1000, "fat guy"));
		t1.start();
		Thread t2 = new Thread(new RunnableTest(3000, "Slow Guy"));
		t2.start();
	}
}
