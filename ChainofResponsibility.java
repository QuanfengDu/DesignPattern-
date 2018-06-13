/*
 * Author: Quanfeng Du 
 * Data: June 13, 2018
 * Objective: demo the Chain of Repository pattern
 * Avoid coupling the sender of a request to its request to its receiver by giving more than one object a chance  
 * to handle the request.
 */


package DesignPattern;

abstract class AbstractLogger{
	public static int INFO=1;
	public static int DEBUG=2;
	public static int ERROR=3;
	
	protected int level;
	
	protected AbstractLogger nextLogger;
	public void setNextLogger(AbstractLogger nextLogger) {
		this.nextLogger=nextLogger;
	}
	
	public void logMessage(int level, String message) {
		if(this.level<=level) {
			write(message);
		}
		if(nextLogger!=null)
			nextLogger.logMessage(level, message);
	}
	abstract protected void write(String message);
}

class ErrorLogger extends AbstractLogger{
	public ErrorLogger(int level) {
		this.level=level;
	}
	@Override 
	protected void write(String message) {
		System.out.println("Error Console::Logger:"+message);
	}
}

class ConsoleLogger extends AbstractLogger{
	public ConsoleLogger(int level) {
		this.level=level;
	}
	@Override 
	protected void write(String message) {
		System.out.println("Standard Console::Logger:"+message);
	}
}

class FileLogger extends AbstractLogger{
	public FileLogger(int level) {
		this.level=level;
	}
	@Override 
	protected void write(String message) {
		System.out.println("File::Logger:"+message);
	}
}



public class ChainofResponsibility {
	private static AbstractLogger getChainofLogger() {
		AbstractLogger errorLogger =new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger=new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger =new ConsoleLogger(AbstractLogger.INFO);
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);
		return errorLogger;
	}
	
	public static void main(String[] args) {
		AbstractLogger loggerChain =getChainofLogger();
		
		loggerChain.logMessage(AbstractLogger.INFO, "information");
		loggerChain.logMessage(AbstractLogger.DEBUG, "debug");
		loggerChain.logMessage(AbstractLogger.ERROR, "Error");
		
	}
	
}
