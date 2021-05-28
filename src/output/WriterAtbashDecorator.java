package output;

import java.util.ArrayList;
import java.util.List;

public class WriterAtbashDecorator extends WriterDecorator{
	
	DocumentWriter docWriter;
    private final String PLAIN = "abcdefghijklmnopqrstuvwxyzáâãäåæçèéêëìíîïğñóôõö÷øù";
    private final String CIPHER = "zyxwvutsrqponmlkjihgfedcbaùø÷öõôóñğïîíìëêéèçæåäãâá";
	
	public WriterAtbashDecorator(DocumentWriter docWriter) {
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

        for (char c : encoded.toCharArray()) {
            cyphered += applyCipher(c);
        }

        return cyphered;
    }
    
    private char applyCipher(char input) {
        int idx = PLAIN.indexOf(input);

        return idx >= 0 ? CIPHER.toCharArray()[idx] : input;
    }

}
