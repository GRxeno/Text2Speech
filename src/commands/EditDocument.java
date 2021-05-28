package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;

import model.Document;

public class EditDocument implements ActionListener{
	
	private Document doc;
	private JTextArea textArea;
	private JButton editButton;
	private boolean isEditable;
	
	public EditDocument() {
		isEditable = false;
	}
	
	
	public void setDocument(Document myDoc){
		this.doc = myDoc;
	}
	
	public void setParameters(JTextArea tA, JButton eB) {
		this.textArea = tA;
		this.editButton = eB;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (isEditable) {
			editButton.setText("Edit");	
			List<String> myList = new ArrayList<String>(Arrays.asList(textArea.getText().split("\n")));
			this.editFile(myList);
		} else {
			editButton.setText("Save");	
		}

		textArea.setEditable(!isEditable);
		isEditable = !isEditable;
		
	}
	
	public void editFile(List<String> text) {
		doc.setContent(text);
	}

}
