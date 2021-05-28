package input;

import java.util.ArrayList;
import java.util.List;

public class ReaderRot13Decorator extends ReaderDecorator {
	
	DocumentReader docReader;
	int rot = 13;

	public ReaderRot13Decorator(DocumentReader docReader) {
		this.docReader = docReader;
	}

	@Override
	public List<String> read() {
		return readEncr(docReader.read());
	}

	@Override
	public List<String> readEncr(List<String> myList) {
		List<String> newMyList = new ArrayList<String>();
		
		for (String tmpString : myList) {
			newMyList.add(decode(tmpString));
		}
		
		return newMyList;
	}
	
	public String decode(String input) {
        String encoded = (input).toLowerCase();
        String deciphered = "";

        for (char ch : encoded.toCharArray()) {
        	int c = (int)ch;
        	
        	//    122 = 'z'    97 = 'a'          
        	if ((c <= 122) && (c >= 97)){
        		
        		//English
        		if (c - rot < 97){
        			int dif = Math.abs(97 - (c - rot)) - 1;
        			c = 122 - dif;
        		}else {
        			c -= rot;
        		}
        		deciphered += Character.toString ((char) c);
        		
        	} 
        	//        969 = 'ù'       945 = 'á'
//        	else if ((c <= 969) && (c >= 945)) {
//        		
//        		// Greek
//        		if (c + rot < 945){
//        			int dif = Math.abs(945 - (c - rot)) - 1;
//        			c = 969 - dif;
//        		}else {
//        			c -= rot;
//        		}
//        		deciphered += Character.toString ((char) c);
//
//        	}
        	
        	else
        	{
        		deciphered += ch;
        	}
        }

        return deciphered;
    }

	
}
