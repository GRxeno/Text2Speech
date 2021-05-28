package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.EditDocument;
import model.Document;

class EditDocumentTest {

	@Test
	@DisplayName("Test correct editng file")
	void editWord() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		EditDocument editor = (EditDocument) factory.createCommand("editFile");
		
		Document doc = new Document(); 
		editor.setDocument(doc);
		doc.setEncryption("none");
		
		doc.open(".docx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the editted text");
		
		editor.editFile(myList);
		
		assertEquals(doc.getContent().get(0), "this is the editted text");
	}

}
