package commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager {
	
	private boolean recordingStatus;
	private ArrayList<ActionListener> commands;

	public ReplayManager(){
		recordingStatus = false;
		commands = new ArrayList<ActionListener>();
	}
	
	public ArrayList<ActionListener> getCommands(){
		return commands;
	}
	
	public void printReplay() {
		for(ActionListener al : commands) {
			System.out.println(al.toString());
		}
	}
	
	public void addCommand(ActionListener aL) {
		commands.add(aL);
	}

	public void replay(){
		for(ActionListener al : commands) {
			 if(al.getClass().getSimpleName().equals("OpenDocument"))
				((OpenDocument) al).openDoc();
			 else if(al.getClass().getSimpleName().equals("SaveDocument"))
				((SaveDocument) al).saveDoc();
			 else if(al.getClass().getSimpleName().equals("DocumentToSpeech"))
				((DocumentToSpeech) al).speakDoc();
		}
	}

	public void startRecording(){
		commands.clear();
		recordingStatus = true;
	} 

	public void endRecording(){
		recordingStatus = false;
	}

	public boolean isActiveRecording(){
		return recordingStatus;
	}
	
}
