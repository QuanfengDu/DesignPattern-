/*
 * Author: Quanfeng Du 
 * Data: June 12, 2018
 * Objective: demo the prototype pattern
 * Specify the kinds of objects to create using a 
 * prototypical instance, and create new objects by 
 * copying this prototype. To implement this pattern
 * in JAVA, first to implement the cloneable interface,
 * in JVM, only by implementing this interface, the object 
 * is cloneable, otherwise, the CloneNotSupportedException
 * will throw. Second to override the clone method, all the 
 * class extends from Object class, clone method in Object class
 * is used to return a cloned object, but the access type is protected. 
 */
package DesignPattern;

import java.util.*;


//the prototype Interface 

interface Prototypes{
	Prototypes clone();
	String getName();
	void execute();
}

class PrototypeModule{
	private static List<Prototypes> prototypes=new ArrayList<>();
	public static void addPrototypes(Prototypes p) {
		prototypes.add(p);
	}
	public static Prototypes createPrototype(String name) {
		for(Prototypes p: prototypes)
			if(p.getName().equals(name))
				return p.clone();
		System.out.println(name+": does not exist");
		return null;
	}
}

class PrototypeAlpha implements Prototypes{
	private String name="Alpha";
	@Override
	public Prototypes clone() {
		return new PrototypeAlpha();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override 
	public void execute() {
		System.out.println(name+":dose something");
	}
}

class PrototypeBeta implements Prototypes{
	private String name="Beta";
	@Override
	public Prototypes clone() {
		return new PrototypeBeta();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override 
	public void execute() {
		System.out.println(name+":dose something");
	}
}

class PrototypeRelease implements Prototypes{
	private String name="Release";
	@Override
	public Prototypes clone() {
		return new PrototypeRelease();
	}
	@Override
	public String getName() {
		return name;
	}
	@Override 
	public void execute() {
		System.out.println(name+":dose real work");
	}
}


public class Prototype {
	public static void main(String[] args) {
		if(args.length>0) {
			initialzePrototypes();
			List<Prototypes> prototypes=new ArrayList<>();
			for(String protoName:args) {
				Prototypes prototype=PrototypeModule.createPrototype(protoName);
				if(prototype!=null)
					prototypes.add(prototype);
			}
			for(Prototypes p: prototypes)
				p.execute();
		}
		else 
			System.out.println("No Argument");
	}
	public static void initialzePrototypes() {
		PrototypeModule.addPrototypes(new PrototypeAlpha());
		PrototypeModule.addPrototypes(new PrototypeBeta());
		PrototypeModule.addPrototypes(new PrototypeRelease());
		
	}
	
	
}
