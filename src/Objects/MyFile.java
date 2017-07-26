package Objects;

public class MyFile
{
	private String path;
	private String name;
	private String extension;
	private String fullString;
	private String fullFileNameWithPathAndDate;
	
	

	public MyFile(String fullFileNameString) {
		this.path = ConvertNames.getDirectoryPathString(fullFileNameString);
		this.name = ConvertNames.getFileName(fullFileNameString);
		this.extension = ConvertNames.getFileExensiont(fullFileNameString);
		this.fullString = fullFileNameString;
		this.fullFileNameWithPathAndDate = this.path + this.name + "_" + MyCalendar.getCurrentDateCustomFormat("yyyyMMdd") + this.extension;
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
		return fullFileNameWithPathAndDate;
	}
	



}
