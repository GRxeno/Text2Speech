package commands;

import java.awt.event.*;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;

public class OpenDocument implements ActionListener {
	
	private final JFileChooser openFileChooser;
	private Document doc;
	private ReplayManager replayManager;
	private JTextArea textArea;
	private JTextField textField;
	private FileNameExtensionFilter allFilters[];
	
	private String path;
	private String fileName;
	private String type;

	public OpenDocument(String path, String fileName, String type){
		this.path = path;
		this.fileName = fileName;
		this.type = type;
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
		allFilters = new FileNameExtensionFilter[1];
		
		allFilters[0] = new FileNameExtensionFilter("Documents (*.docx, *.xlsx)", "docx", "xlsx");
		//openFileChooser.addChoosableFileFilter(filter);
		openFileChooser.setFileFilter(allFilters[0]);
	} 

	public void setDocument(Document myDoc){
		this.doc = myDoc;
	}
	
	public void setText(JTextArea tA, JTextField tF) {
		this.textArea = tA;
		this.textField = tF;
	}
	
	public List<String> getContent(){
		return this.doc.getContent();
	}

	public void setReplayManager(ReplayManager reManager){
		this.replayManager = reManager;
	}

	public void actionPerformed(ActionEvent e){
		
		int response = openFileChooser.showOpenDialog(null); // choose file to open
		
		if (response == JFileChooser.APPROVE_OPTION) {
			
			path = openFileChooser.getSelectedFile().getAbsolutePath();
			fileName = openFileChooser.getSelectedFile().getName();
			type = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			
			this.openDoc();
			
			if (replayManager.isActiveRecording()) {
				OpenDocument tmp = new OpenDocument(path, fileName, type);
				tmp.setDocument(doc);
				tmp.setText(textArea, textField);
				replayManager.addCommand(tmp);
			}
			
		}
		else {
			System.out.println("Exit");
		}
	}
	
	public void openDoc() {
		doc.open(type, path, "");
		
		textField.setText(path);
		
		textArea.setText("");
		for(String tmpString : doc.getContent()){
			textArea.append(tmpString + "\n");
		}
	}
	
	@Override
	public String toString() {
		return "Action= OpenFile" + " | File to Open= " + fileName;
	}
	
}
