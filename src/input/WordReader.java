package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordReader implements DocumentReader {
	
	private XWPFDocument document;
	private XWPFWordExtractor extract;
	private String path;
	
	public WordReader(String path){
		this.path = path;
	}

	public List<String> read(){
		List<String> myList = null;
		try {
			document = new XWPFDocument(new FileInputStream(path));
			extract = new XWPFWordExtractor(document);
			myList = new ArrayList<String>(Arrays.asList(extract.getText().split("\\r?\\n")));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				extract.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return myList;
	}
}
