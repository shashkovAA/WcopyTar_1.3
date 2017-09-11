package Objects;

public class MyFile
{
	private String path;
	private String name;
	private String extension;
	private String fullString;


	public MyFile(String fullFileNameString) {
		this.path = ConvertNames.getDirectoryPathStringWinFormat(fullFileNameString);
		this.name = ConvertNames.getFileName(fullFileNameString);
		this.extension = ConvertNames.getFileExension(fullFileNameString);
		this.fullString = fullFileNameString;

	}
	
	public String getPath(){
		return path;
	}

	public String getName(){
		return name;
	}	

	public String getExtension(){
		return extension;
	}
	
	public String getFullString(){
		return fullString;
	}
	
	public String getFullFileNameWithPathAndDate() {
		return (this.path + this.name + "_" + MyCalendar.getCurrentDateCustomFormat("yyyyMMdd") + this.extension);
	}
	



}
