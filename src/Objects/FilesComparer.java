package Objects;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

public class FilesComparer {


	private  SizeFilesMap filesMD5HashMap = new SizeFilesMap();
	private HashMap<String,String> statusMap = new HashMap<String, String>();
	String fileSize;
	
	public void setUserSettings(Property prop) {
		String filename;
		for (int i=0;i<prop.getSftpSrcFileCount();i++) {
			filename = prop.getSftpSrcFullFileNamesList().get(i);
			filesMD5HashMap.putHash(ConvertNames.getFileNameWithExt(filename), "0");
			statusMap.put(ConvertNames.getFileNameWithExt(filename), "0");	
		}
		filesMD5HashMap.printFilesMap();
	}
	
	public void insertSizeForCopiedFile(String fileNamePath, String fileNameWithExt) {
	 fileSize = FileSize.getFileSize(fileNamePath + fileNameWithExt);		 
	 compareHash(fileNameWithExt,fileSize);
	 filesMD5HashMap.putHash(fileNameWithExt, fileSize);
	}
	
	public void compareHash(String filename, String hash) {
		if (filesMD5HashMap.getHash(filename).equals(hash)){
			Debug.log.debug("Size of file " + filename +" in HashMap is NOT changed!" );
			Debug.log.debug("Old size is [" + filesMD5HashMap.getHash(filename) + "], new size is [" + hash + "].");
			statusMap.put(filename, "0");
		} else {
			Debug.log.debug("Size for file " + filename +" in HashMap is changed!" );
			Debug.log.debug("Old size is [" + filesMD5HashMap.getHash(filename) + "], new size is [" + hash + "].");
			filesMD5HashMap.putHash(filename, hash);
			statusMap.put(filename, "1");
		}		
	}
	
	public boolean getNewFileFlag(String filename){
		if (statusMap.get(filename).equals("0"))
			return false;	
		else 
			return true;
	}

		
	

}
