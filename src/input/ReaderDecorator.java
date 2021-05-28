package input;

import java.util.List;

public abstract class ReaderDecorator implements DocumentReader {
	
	public ReaderDecorator(){

	}

	public abstract List<String> readEncr(List<String> myList);
}
