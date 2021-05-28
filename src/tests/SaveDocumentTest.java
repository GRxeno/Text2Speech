package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.OpenDocument;
import model.Document;

class SaveDocumentTest {

	@Test
	@DisplayName("Test correct saving word file with no encryption")
	void saveWordNoEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.save(".docx", path, "");
		
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "this is the text to be saved");
	}
	
	@Test
	@DisplayName("Test correct saving word file with rot13 encryption")
	void saveWordRotEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.setEncryption("rot13");
		doc.save(".docx", path, "");
		
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "guvf vf gur grkg gb or fnirq");
	}
	
	@Test
	@DisplayName("Test correct saving word file with atBash encryption")
	void saveWordBashEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.setEncryption("atBash");
		doc.save(".docx", path, "");
		
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		
		assertEquals(doc.getContent().get(0), "gsrh rh gsv gvcg gl yv hzevw");
	}
	
	
	@Test
	@DisplayName("Test correct saving excel file with no encryption")
	void saveExcelNoEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".xlsx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.save(".xlsx", path, "");
		
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "this is the text to be saved ; ");
	}
	
	@Test
	@DisplayName("Test correct saving excel file with rot13 encryption")
	void saveExcelRotEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".xlsx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.setEncryption("rot13");
		doc.save(".xlsx", path, "");
		
		doc.setEncryption("none");
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "guvf vf gur grkg gb or fnirq ; ");
	}
	
	@Test
	@DisplayName("Test correct saving excel file with atBash encryption")
	void saveExcelBashEnc() {
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.xlsx";
		
		CommandsFactory factory = new CommandsFactory();
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".xlsx", path, "");
		
		List<String> myList = new ArrayList<String>();
		myList.add("this is the text to be saved");
		
		doc.setContent(myList);
		doc.setEncryption("atBash");
		doc.save(".xlsx", path, "");
		
		doc.setEncryption("none");
		doc.open(".xlsx", path, "");
		
		assertEquals(doc.getContent().get(0), "gsrh rh gsv gvcg gl yv hzevw ; ");
	}


}
