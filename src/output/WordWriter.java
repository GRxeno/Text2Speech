package output;

import java.io.*;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter{
	
	private String path;
	
	public WordWriter(String path){
		this.path = path;
	}

	public boolean write(List<String> myList){
		
		//String fileName = path.substring(path.lastIndexOf('\\') + 1);
		
		if (myList.isEmpty()) {
			// TO-DO Create empty file
			return false;
		}
		
		FileOutputStream out = null;
		
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		
		for (String tmpString : myList) {
			
			run.setText(tmpString);
			run.addBreak();
			
			try {
				 out = new FileOutputStream(new File(path));
				 document.write(out);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			document.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
