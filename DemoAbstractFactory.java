/*
 * Author: Quanfeng Du 
 * Data: June 5, 2018
 * Objective: demo the abstract factory pattern
 * Provide an interface for creating families of related of dependent objects without specifying their concrete classes
 */


package DesignPattern;

//class CPU
abstract class CPU{}

//class EmberCPU
class EmberCPU extends CPU{}

//class EnginolaCPU
class EnginolaCPU extends CPU{}

//class MMU
abstract class MMU{}

//class EmberMMU
class EmberMMU extends MMU {}

//class EnginolaMMU
class EnginolaMMU extends MMU{}

//class EmberFactory
class EmberToolkit extends AbstractFactory{
	@Override 
	public CPU createCPU(){
		return new EmberCPU();
	}
	@Override 
	public MMU createMMU(){
		return new EmberMMU();
	}
}

//class EnginolaFactory 

class EnginolaToolkit extends AbstractFactory{
	@Override
	public CPU createCPU(){
		return new EnginolaCPU();
	}

	@Override 
	public MMU createMMU(){
		return new EnginolaMMU();
	}
}

enum Architecture {
	ENGINOLA, EMBER
}

abstract class AbstractFactory{
	private static EmberToolkit EMBER_TOOLKIT =new EmberToolkit();
	private static EnginolaToolkit ENGINOLATOOLKIT = new EnginolaToolkit();

	static AbstractFactory getFactory(Architecture architecture){
		AbstractFactory factory =null;
		switch(architecture){
			case ENGINOLA:
				factory = ENGINOLATOOLKIT;
				break;
			case EMBER:
				factory = EMBER_TOOLKIT;
				break;
		}
		return factory;
	}

	public abstract CPU createCPU();
	public abstract MMU createMMU();
}

public class DemoAbstractFactory{
	public static void main(String[] args){
		AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
		CPU cpu=factory.createCPU();
	}
}



