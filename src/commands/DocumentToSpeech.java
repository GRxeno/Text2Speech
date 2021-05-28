package commands;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import model.Document;

public class DocumentToSpeech implements ActionListener {
	
	private Document doc;
	private ReplayManager replayManager;
	private JTextArea textArea;
	private JSpinner lineSpinner;
	private String lastText;
	private String action;
	
	public DocumentToSpeech(String txt, String act){
		this.lastText = txt;
		this.action = act;
	}

	public void setDocument(Document myDoc){
		this.doc = myDoc;
	}
	
	public void setParameters(JTextArea tA, JSpinner jS) {
		this.textArea = tA;
		this.lineSpinner = jS;
	}

	public void setReplayManager(ReplayManager reManager){
		this.replayManager = reManager;
	}

	public void actionPerformed(ActionEvent e){
		
		action = ((JButton) e.getSource()).getText();
		
		if (action.equals("Play All")) {
			lastText = doc.playContents();
		}
		else if (action.equals("Play Selected")) {
			lastText = textArea.getSelectedText();
		}
		else if (action.equals("Play Line")) {
			lastText = doc.playLine((int)lineSpinner.getValue());
		}
		
		this.speakDoc();
		
		if (replayManager.isActiveRecording()) {
			DocumentToSpeech tmp = new DocumentToSpeech(lastText, action);
			tmp.setDocument(doc);
			replayManager.addCommand(tmp);
		}

	}
	
	public void speakDoc() {
		doc.playString(lastText);
	}
	
	public String getText() {
		return lastText;
	}
	
	@Override
	public String toString() {
		return "Action= " + action + " | Text to Play= " + lastText;
	}
}
