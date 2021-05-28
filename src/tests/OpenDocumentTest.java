package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import commands.CommandsFactory;
import commands.OpenDocument;
import model.Document;

class OpenDocumentTest {

	@Test
	@DisplayName("Test correct openning word file with no encryption")
	void openWordNoEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "this is to test opener");
	}
	
	@Test
	@DisplayName("Test correct openning word file with rot13 encryption")
	void openWordRotEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("rot13");
		
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "guvf vf gb grfg bcrare");
	}
	
	@Test
	@DisplayName("Test correct openning word file with atBash encryption")
	void openWordBashEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("atBash");
		
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "gsrh rh gl gvhg lkvmvi");
	}
	
	@Test
	@DisplayName("Test correct openning excel file with no encryption")
	void openExcelNoEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "this is to test opener ; ");
	}
	
	@Test
	@DisplayName("Test correct openning excel file with rot13 encryption")
	void openExcelRotEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("rot13");
		
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "guvf vf gb grfg bcrare ; ");
	}
	
	@Test
	@DisplayName("Test correct openning excel file with atBash encryption")
	void openExcelBashEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("atBash");
		
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "gsrh rh gl gvhg lkvmvi ; ");
	}


}
