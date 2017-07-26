package Objects;

public class ConvertNames
{
			
	public static String createUriString(String fileNameWithPath){	
    	
		String convertedURIString = "jar:file:///" + ConvertNames.convertFilePath(fileNameWithPath);
		Debug.log.debug("URI for archive file: " + convertedURIString);
		return convertedURIString;
	}
	
	public static String convertFilePath(String path) {
		return path.replace('\\', '/');
	}
	
	public static String getFileNameWithExt(String fileNameWithPath) {
		return fileNameWithPath.substring(fileNameWithPath.lastIndexOf('\\') + 1);
	}
	
	public static String getDirectoryPathString(String fileNameWithPath) {
		return fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf('\\') + 1);
	}
	
	public static String getFileName(String fileNameWithPath) {
		String fileNameFull = getFileNameWithExt(fileNameWithPath);
		return fileNameFull.substring(0,fileNameFull.indexOf('.'));
	}
	
	public static String getFileExensiont(String fileNameWithPath) {
		String fileNameFull = getFileNameWithExt(fileNameWithPath);
		return fileNameFull.substring(fileNameFull.indexOf('.'));
	}
	
	
}
