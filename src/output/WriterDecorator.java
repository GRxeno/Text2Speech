package output;

import java.util.List;

public abstract class WriterDecorator implements DocumentWriter{
	
	public WriterDecorator(){

	}

	public abstract List<String> writeEncr(List<String> myList);

}
