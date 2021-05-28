package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import commands.DocumentToSpeech;
import model.Document;
import model.TTSFacade.FakeTTSFacade;

class PlayContentsTest {

	@Test
	@DisplayName("Test to check if all content were played correctly")
	void playAllText() {
		
		Document doc = new Document();
		DocumentToSpeech speech = new DocumentToSpeech("this is the text to be spoken", "Play All");
		speech.setDocument(doc);
		
		FakeTTSFacade fake = doc.getAudioManager().new FakeTTSFacade();

		fake.play(speech.getText());
		
		assertEquals(fake.getPlayedText(), "this is the text to be spoken");
	}
	
	@Test
	@DisplayName("Test to check if selected content were played correctly")
	void playSelectedText() {
		
		Document doc = new Document();
		DocumentToSpeech speech = new DocumentToSpeech("this is the text to be spoken", "Play Selected");
		speech.setDocument(doc);
		
		FakeTTSFacade fake = doc.getAudioManager().new FakeTTSFacade();

		fake.play(speech.getText());
		
		assertEquals(fake.getPlayedText(), "this is the text to be spoken");
	}
	
	@Test
	@DisplayName("Test to check if setting values change correctly")
	void changeValues() {
		
		Document doc = new Document();
		
		FakeTTSFacade fake = doc.getAudioManager().new FakeTTSFacade();

		fake.setVolume(0.5f);
		fake.setPitch(50);
		fake.setRate(60);
		
		assertEquals(fake.getVolume(), 0.5f);
		assertEquals(fake.getPitch(), 50);
		assertEquals(fake.getRate(), 60);
	}

}
