package commands;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Document;

public class SaveDocument implements ActionListener {
	
	private final JFileChooser saveFileChooser;
	private Document doc;
	private ReplayManager replayManager;
	private JTextArea textArea;
	private FileNameExtensionFilter allFilters[];
	
	private String path;
	private String fileName;
	private String type;
	
	public SaveDocument(String path, String fileName, String type){
		this.path = path;
		this.fileName = fileName;
		this.type = type;
		saveFileChooser = new JFileChooser();
		saveFileChooser.setCurrentDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
		allFilters = new FileNameExtensionFilter[2];
		
		allFilters[0] = new FileNameExtensionFilter("MS Word (*.docx)", "docx");
		allFilters[1] = new FileNameExtensionFilter("MS Excel (*.xlsx)", "xlsx");
		
		for (FileNameExtensionFilter filter : allFilters) {
			saveFileChooser.addChoosableFileFilter(filter);
		}
		saveFileChooser.setFileFilter(allFilters[0]);
	}

	public void setDocument(Document myDoc){
		this.doc = myDoc;
	}
	
	public void setTextArea(JTextArea tA) {
		this.textArea = tA;
	}

	public void setReplayManager(ReplayManager reManager){
		this.replayManager = reManager;
	}

	public void actionPerformed(ActionEvent e){
		
		int response = saveFileChooser.showSaveDialog(null); // choose file to save
		
		if (response == JFileChooser.APPROVE_OPTION) {
			
			path = saveFileChooser.getSelectedFile().getAbsolutePath();
			fileName = saveFileChooser.getSelectedFile().getName();
			
			if((saveFileChooser.getFileFilter() == allFilters[0]) && (!fileName.endsWith(".docx"))) {
				fileName+=".docx";
				path+=".docx";
			}
			if((saveFileChooser.getFileFilter() == allFilters[1]) && (!fileName.endsWith(".xlsx"))) {
				fileName+=".xlsx";
				path+=".xlsx";
			}
			
			type = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			
			this.saveDoc();	
			
			if (replayManager.isActiveRecording()) {
				SaveDocument tmp = new SaveDocument(path, fileName, type);
				tmp.setDocument(doc);
				tmp.setTextArea(textArea);
				replayManager.addCommand(tmp);
			}
			
		}
		else {
			System.out.println("Exit");
		}
	}
	
	public void saveDoc() {
		List<String> myList = new ArrayList<String>(Arrays.asList(textArea.getText().split("\n")));
		doc.setContent(myList);
		doc.save(type, path, "");
	}
	
	@Override
	public String toString() {
		return "Action= SaveFile" + " | File to Save= " + fileName;
	}
	
}
