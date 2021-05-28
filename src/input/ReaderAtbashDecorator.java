package input;

import java.util.ArrayList;
import java.util.List;

public class ReaderAtbashDecorator extends ReaderDecorator{
	
	DocumentReader docReader;
    private final String PLAIN = "abcdefghijklmnopqrstuvwxyzáâãäåæçèéêëìíîïğñóôõö÷øù";
    private final String CIPHER = "zyxwvutsrqponmlkjihgfedcbaùø÷öõôóñğïîíìëêéèçæåäãâá";
	
	public ReaderAtbashDecorator(DocumentReader docReader) {
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

        for (char c : encoded.toCharArray()) {
            deciphered += applyCipher(c);
        }

        return deciphered;
    }

    
    private char applyCipher(char input) {
        int idx = PLAIN.indexOf(input);

        return idx >= 0 ? CIPHER.toCharArray()[idx] : input;
    }
	
	

}
