package commands;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Document;

public class CommandsFactory {
	
	private Document doc;
	private JTextArea textArea;
	private JTextField textField;
	private JSpinner lineSpinner;
	private ReplayManager replayManager;
	
	private JButton start;
	private JButton end;
	private JButton replay;
	private JButton editButton;

	private OpenDocument open;
	private SaveDocument save;
	private DocumentToSpeech speech;
	private EditDocument edit;
	private StartRecording record;
	
	public CommandsFactory(){ 
		edit = new EditDocument();
	}
	
	public void setParameters(Document myDoc, ReplayManager rM, JTextArea tA,  JTextField tF, JSpinner lS){
		this.doc = myDoc;
		this.textArea = tA;
		this.textField = tF;
		this.lineSpinner = lS;
		this.replayManager = rM;
	}
	
	public void setButtons(JButton eB, JButton start, JButton end, JButton replay){
		this.editButton = eB;
		 this.start = start;
		 this.end = end;
		 this.replay = replay;
	}

	public ActionListener createCommand(String command){
		
		switch(command) {
			case "openFile":
				open = new OpenDocument("","","");
				open.setDocument(doc);
				open.setReplayManager(replayManager);
				open.setText(textArea, textField);
				return open;
			case "saveFile":
				save = new SaveDocument("","","");
				save.setDocument(doc);
				save.setReplayManager(replayManager);
				save.setTextArea(textArea);
				return save;
			case "txtToSpeech":
				speech = new DocumentToSpeech("","");
				speech.setDocument(doc);
				speech.setParameters(textArea, lineSpinner);
				speech.setReplayManager(replayManager);
				return speech;
			case "editFile":
				edit.setDocument(doc);
				edit.setParameters(textArea, editButton);
				return edit;
			case "startRecord":
				record = new StartRecording();
				record.setButtons(start, end, replay);
				record.setReplayManager(replayManager);
				return record;
            default:
                System.out.println("no match");
                return null;
		}
		
	}	
	
}
