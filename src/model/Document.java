package model;

import java.util.ArrayList;
import java.util.List;

import input.*;
import output.*;

public class Document {
	
	private ArrayList<String> contents;
	private TTSFacade audioManager;
	private DocumentReaderFactory docReaderFactory;
	private DocumentWriterFactory docWriterFactory;
	private String encryption;

	public Document(){
		contents = new ArrayList<String>();
		docReaderFactory = new DocumentReaderFactory();
		docWriterFactory = new DocumentWriterFactory();
		audioManager = new TTSFacade();
	}
	
	public int getLines() {
		return contents.size();
	}
	
	public TTSFacade getAudioManager() {
		return this.audioManager;
	}
	
	public void setEncryption(String enc) {
		this.encryption = enc;
	}
	
	public void setContent(List<String> myList){
		this.contents = (ArrayList<String>) myList;
	}
	
	public List<String> getContent(){
		return this.contents;
	}

	public void setAudioManager(TTSFacade facade){
		this.audioManager = facade;
	}

	public void setDocReaderFactory(DocumentReaderFactory rFactory){
		this.docReaderFactory = rFactory;
	}

	public void setDocWriterFactory(DocumentWriterFactory wFactory){
		this.docWriterFactory = wFactory;
	}

	public void open(String type, String path, String enc){
		contents = (ArrayList<String>) docReaderFactory.createReader(type, path, encryption).read();
	}

	public String playContents(){
		String tmp = "";
		for (String strTemp : contents){
//			audioManager.play(strTemp);
			tmp += strTemp + " ";
		}
		return tmp;
	}
	
	public String playString(String text){
		if (text != null) {
			audioManager.play(text);
			return text;
		}
		return "";
	}

	public String playLine(int line){
		if (!contents.isEmpty() && line > 0) {
//			audioManager.play(contents.get(line-1));
			return contents.get(line-1);
		}
		return "";
	}

	public void save(String type, String path, String enc){
		docWriterFactory.createWriter(type, path, encryption).write(contents);
	}

}
