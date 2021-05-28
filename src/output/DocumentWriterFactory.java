package output;

public class DocumentWriterFactory {
	
	//private WriterAtbashDecorator writerAtbashDecorator;
	//private WriterRot13Decorator writerRot13Decorator;

	public DocumentWriterFactory(){

	}

	public DocumentWriter createWriter(String type, String path, String enc){
		

		
		switch(type) {
			case ".xlsx":
				switch(enc) {
					case "none":
//						System.out.println("Attempting Writing Excel | No encryption");
						return new ExcelWriter(path);
					case "rot13":
//						System.out.println("Attempting Writing Excel | Rot13 encryption");
						return new WriterRot13Decorator(new ExcelWriter(path));
					case "atBash":
//						System.out.println("Attempting Writing Excel | AtBash encryption");
						return new WriterAtbashDecorator(new ExcelWriter(path));
			        default:
			            System.out.println("not correct encryption");
			            return null;
				}
			case ".docx":
				switch(enc) {
					case "none":
//						System.out.println("Attempting Writing Word | No encryption");
						return new WordWriter(path);
					case "rot13":
//						System.out.println("Attempting Writing Word | Rot13 encryption");
						return new WriterRot13Decorator(new WordWriter(path));
					case "atBash":
//						System.out.println("Attempting Writing Word | AtBash encryption");
						return new WriterAtbashDecorator(new WordWriter(path));
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
