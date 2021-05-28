package input;

public class DocumentReaderFactory {

	public DocumentReaderFactory(){

	}

	public DocumentReader createReader(String type, String path, String enc){

		
		switch(type) {
			case ".xlsx":
				switch(enc) {
					case "none":
//						System.out.println("Attempting Reading Excel | No encryption");
						return new ExcelReader(path);
					case "rot13":
//						System.out.println("Attempting Reading Excel | Rot13 encryption");
						return new ReaderRot13Decorator(new ExcelReader(path));
					case "atBash":
//						System.out.println("Attempting Reading Excel | AtBash encryption");
						return new ReaderAtbashDecorator(new ExcelReader(path));
			        default:
			            System.out.println("not correct encryption");
			            return null;
				}
			case ".docx":
				switch(enc) {
					case "none":
//						System.out.println("Attempting Reading Word | No encryption");
						return new WordReader(path);
					case "rot13":
//						System.out.println("Attempting Reading Word | Rot13 encryption");
						return new ReaderRot13Decorator(new WordReader(path));
					case "atBash":
//						System.out.println("Attempting Reading Word | AtBash encryption");
						return new ReaderAtbashDecorator(new WordReader(path));
			        default:
			            System.out.println("not correct encryption");
			            return null;
				}
	        default:
	            System.out.println("not correct extension to read");
	            return null;
		}
	}
}
