package Objects;

public class ConvertNames
{
			
	public static String createUriString(String fileNameWithPath){	
    	
		String convertedURIString = "jar:file:///" + ConvertNames.convertFilePathFromWinToUnix(fileNameWithPath);
		Debug.log.debug("URI for archive file: " + convertedURIString);
		return convertedURIString;
	}
	
	public static String convertFilePathFromWinToUnix(String path) {
		return path.replace('\\', '/');
	}
	
	public static String getFileNameWithExt(String fileNameWithPath) {
		
		int indexEndFilePath = Math.max(fileNameWithPath.lastIndexOf('\\'), fileNameWithPath.lastIndexOf('/'));
		return fileNameWithPath.substring(indexEndFilePath + 1);
	}
	
	
	public static String getDirectoryPathStringWinFormat(String fileNameWithPath) {
		return fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf('\\') + 1);
	}
	
	public static String getDirectoryPathStringUnixFormat(String fileNameWithPath) {
		return fileNameWithPath.substring(0, fileNameWithPath.lastIndexOf('/') + 1);
	}
	
	public static String getFileName(String fileNameWithPath) {
		String fileNameFull = getFileNameWithExt(fileNameWithPath);
		return fileNameFull.substring(0,fileNameFull.indexOf('.'));
	}
	
	public static String getFileExension(String fileNameWithPath) {
		String fileNameFull = getFileNameWithExt(fileNameWithPath);
		return fileNameFull.substring(fileNameFull.indexOf('.'));
	}
	
	
}
