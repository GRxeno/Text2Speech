package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//import model.Document;

public class StartRecording implements ActionListener {

	private ReplayManager replayManager;
	private String action;
	
	private JButton start;
	private JButton end;
	private JButton replay;
	
	public StartRecording() {
		
	}
	
	public void setButtons(JButton start, JButton end, JButton replay) {
		 this.start = start;
		 this.end = end;
		 this.replay = replay;
	}

	public void setReplayManager(ReplayManager reManager){
		this.replayManager = reManager;
	}
	
	public ReplayManager getReplayManager(){
		return this.replayManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		action = ((JButton) e.getSource()).getText();
		
		if (action.equals("Record")) {
			replayManager.startRecording();
			start.setEnabled(false);
			end.setEnabled(true);
			replay.setEnabled(false);
		}
		else if (action.equals("Stop")) {
			replayManager.endRecording();
			start.setEnabled(true);
			end.setEnabled(false);
			replay.setEnabled(true);
		}
		else if (action.equals("Replay")) {
			replayManager.printReplay();
			replayManager.replay();
		}
		
	}
	
}
