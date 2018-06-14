/*
 * Author: Quanfeng Du 
 * Data: June 14, 2018
 * Objective: demo the command pattern
 * Encapsulate a request as an object, thereby letting you parametrize clients with different requests,
 *  queue or log requests, and support undoable operations.
 */

package DesignPattern;

class AudioPlayer{
	public void play() {
		System.out.println("play...");
	}
	public void rewind() {
		System.out.println("Rewind...");
	}
	public void stop() {
		System.out.println("Stop...");
	}
	
}

interface Command{
	public void execute();
}

class PlayCommand implements Command{
	private AudioPlayer myAudio;
	
	public PlayCommand(AudioPlayer audioPlayer) {
		myAudio =audioPlayer;
	}
	
	@Override
	public  void execute() {
		myAudio.play();
	}
}

class RewindCommand implements Command{
	private AudioPlayer myAudio;
	public RewindCommand(AudioPlayer audioPlayer) {
		myAudio=audioPlayer;
	}
	
	@Override
	public void execute() {
		myAudio.rewind();
	}
}

class StopCommand implements Command{
	private AudioPlayer myAudio;
	public StopCommand(AudioPlayer audioPlayer) {
		myAudio=audioPlayer;
	}
	
	@Override
	public void execute() {
		myAudio.stop();
	}
}

class keypad{
	private Command playCommand;
	private Command rewindCommand;
	private Command stopCommand;
	public void setPlayCommand(Command playCommand) {
		this.playCommand=playCommand;
	}
	
	public void setRewindCommand(Command rewindCommand) {
		this.rewindCommand=rewindCommand;
	}
	public void setStopCommand(Command stopCommand) {
		this.stopCommand=stopCommand;
	}
	public void paly() {
		playCommand.execute();
	}
	public void rewind() {
		rewindCommand.execute();
	}
	public void stop() {
		stopCommand.execute();
	}
}



public class CommandPattern {
	public static void main(String[] args) {
		AudioPlayer audioPlayer=new AudioPlayer();
		Command playCommand=new PlayCommand(audioPlayer);
		Command rewindCommand=new RewindCommand(audioPlayer);
		Command stopCommand=new StopCommand(audioPlayer);
		
		keypad keypad=new keypad();
		keypad.setPlayCommand(playCommand);
		keypad.setStopCommand(stopCommand);
		keypad.setRewindCommand(rewindCommand);
		
		keypad.paly();
		keypad.stop();
		keypad.rewind();
		
		
		
	}
}
