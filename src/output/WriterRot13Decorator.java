package output;

import java.util.ArrayList;
import java.util.List;

public class WriterRot13Decorator extends WriterDecorator {
	
	DocumentWriter docWriter;
	int rot = 13;

	public WriterRot13Decorator(DocumentWriter docWriter) {
		this.docWriter = docWriter;
	}


	@Override
	public boolean write(List<String> myList) {
		return this.docWriter.write(writeEncr(myList));
	}

	@Override
	public List<String> writeEncr(List<String> myList) {
		List<String> newMyList = new ArrayList<String>();
		
		for (String tmpString : myList) {
			newMyList.add(encode(tmpString));
		}
		
		return newMyList;
	}
	
	 public String encode(String input) {
	        String encoded = (input).toLowerCase();
	        String cyphered = "";

	        for (char ch : encoded.toCharArray()) {
	        	int c = (int)ch;
	        	
	        	//    122 = 'z'    97 = 'a'          
	        	if ((c <= 122) && (c >= 97)){
	        		
	        		//English
	        		if (c + rot > 122){
	        			int dif = Math.abs(122 - (c + rot)) - 1;
	        			c = 97 + dif;
	        		}else {
	        			c += rot;
	        		}
	        		cyphered += Character.toString ((char) c);
	        		
	        	} 
	        	//        969 = 'ù'       945 = 'á'
//	        	else if ((c <= 969) && (c >= 945)) {
//	        		
//	        		// Greek
//	        		if (c + rot > 969){
//	        			int dif = Math.abs(969 - (c + rot)) - 1;
//	        			c = 945 + dif;
//	        		}else {
//	        			c += rot;
//	        		}
//	        		cyphered += Character.toString ((char) c);
//
//	        	}
	        	
	        	else
	        	{
	        		cyphered += ch;
	        	}
	        }

	        return cyphered;
	    }

	   
}
