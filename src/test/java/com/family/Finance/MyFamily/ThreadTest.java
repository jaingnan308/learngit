package com.family.Finance.MyFamily;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class ThreadTest extends Thread {
	int time;
	String name;

	public ThreadTest(int x, String n) {
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
		ThreadTest tp1 = new ThreadTest(1000, "Fast Guy");
		tp1.start();
		ThreadTest tp2 = new ThreadTest(3000, "Slow Guy");
		tp2.start();
	}
}
