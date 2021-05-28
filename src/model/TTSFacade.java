package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	
	public class FakeTTSFacade extends TTSFacade {
		private String playedText = "";
		private float vol;
		private int pitch;
		private int rate;
		
		@Override
		public void play(String text) {
			playedText = text;
			super.play(text);
		}
		@Override
		public void setVolume(float volume){
			vol = volume;
			super.setVolume(volume);
		}
		@Override
		public void setPitch(int pitch){
			this.pitch = pitch;
			super.setPitch(pitch);
		}
		@Override
		public void setRate(int rate){
			this.rate = rate;
			super.setRate(rate);;
		}
		
		public String getPlayedText() {
			return this.playedText;
		}
		public float getVolume() {
			return this.vol;
		}
		public int getPitch() {
			return this.pitch;
		}		
		public int getRate() {
			return this.rate;
		}
	}
	
	private VoiceManager vm;
	private Voice voice;

	public TTSFacade(){
		
	    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	    vm = VoiceManager.getInstance();
	    voice = vm.getVoice("kevin16");
	    if (voice != null) {
	        voice.allocate();// Allocating Voice

	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}

	public void play(String text){
		if (text != null) {
			voice.speak(text);
		}		
	}

	public void setVolume(float volume){
		voice.setVolume(volume);
	}

	public void setPitch(int pitch){
		voice.setPitch(pitch);
	}

	public void setRate(int rate){
		voice.setRate(rate);
	}

}
