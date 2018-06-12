/*
 * Author: Quanfeng Du 
 * Data: June 12, 2018
 * Objective: demo the singleton pattern
 * Ensure a class has only one instance, and provide a global point of access to it.
 */
package DesignPattern;

public class Singleton {
	private volatile static Singleton instance;
	private Singleton() {}
	public static Singleton getSingleton() {
		if(instance==null) {
			synchronized(Singleton.class) {
				if(instance==null)
					instance =new Singleton();
			}
		}
		return instance;
	}
}
