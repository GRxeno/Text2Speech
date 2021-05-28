package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.OpenDocument;
import commands.ReplayManager;
import commands.StartRecording;
import model.Document;

class StartRecordingTest {

	@Test
	@DisplayName("Test to check if start recording was done correctly")
	void startRecord() {
		
		CommandsFactory factory = new CommandsFactory();
		StartRecording recorder = (StartRecording) factory.createCommand("startRecord");
		ReplayManager reManager = new ReplayManager();
		recorder.setReplayManager(reManager);
		
		recorder.getReplayManager().startRecording();
		
		assertTrue(recorder.getReplayManager().isActiveRecording());
		
	}
	
	@Test
	@DisplayName("Test to check if start recording was done correctly")
	void endRecord() {
		
		CommandsFactory factory = new CommandsFactory();
		StartRecording recorder = (StartRecording) factory.createCommand("startRecord");
		ReplayManager reManager = new ReplayManager();
		recorder.setReplayManager(reManager);
		
		recorder.getReplayManager().endRecording();
		
		assertFalse(recorder.getReplayManager().isActiveRecording());
		
	}
	
	@Test
	@DisplayName("Test to check if replay was done correctly")
	void replayTest() {
		
		CommandsFactory factory = new CommandsFactory();
		StartRecording recorder = (StartRecording) factory.createCommand("startRecord");
		ReplayManager reManager = new ReplayManager();
		recorder.setReplayManager(reManager);
		recorder.getReplayManager().startRecording();
		
		String path = "C:\\Users\\UltraXeno\\Desktop\\Test.docx";
		OpenDocument opener = (OpenDocument) factory.createCommand("openFile");
		Document doc = new Document(); 
		opener.setDocument(doc);
		doc.setEncryption("none");
		doc.open(".docx", path, "");
		reManager.addCommand(opener);
		
		DocumentToSpeech speech = (DocumentToSpeech) factory.createCommand("txtToSpeech");
		reManager.addCommand(speech);
		
		recorder.getReplayManager().endRecording();
		
		assertEquals(reManager.getCommands().get(0).getClass().getSimpleName(), "OpenDocument"); 
		assertEquals(reManager.getCommands().get(1).getClass().getSimpleName(), "DocumentToSpeech");
	}

}
