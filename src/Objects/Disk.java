package Objects;

import java.io.File;

public class Disk
{

	
	public static boolean checkAvaliableSpace(Property property) {
		Debug.log.info("Checking free space on disk ...");
		String pathName = ConvertNames.getDriveLetterFromFullFileName(property.getSftpDestFilePath());
		File file = new File(pathName);
		long freeSpace = file.getFreeSpace() /1024 /1024 ;
		
		if (freeSpace > property.getMinFreeDiskSpace()) {
			Debug.log.debug("Free space on disk " + pathName.toUpperCase() + " " + freeSpace + " MB.");
			return true;
		} else {
			Debug.log.warn("Free space on disk " + pathName.toUpperCase() + " " + freeSpace + " MB. It's LESS than [minfreediskspaceMB]. Abort copy file(s)!");
			return false;
		}
	}
	
	
	public static void isSpaceOnDiskAvaliable() {
		  
		File file = new File("G:");
		long totalSpace = file.getTotalSpace(); //total disk space in bytes.
		long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
		long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.
		System.out.println(" === Partition Detail ===");

	  	System.out.println(" === bytes ===");
	  	System.out.println("Total size : " + totalSpace + " bytes");
	  	System.out.println("Space free : " + usableSpace + " bytes");
	  	System.out.println("Space free : " + freeSpace + " bytes");

	  	System.out.println(" === mega bytes ===");
	  	System.out.println("Total size : " + totalSpace /1024 /1024 + " mb");
	  	System.out.println("Space free : " + usableSpace /1024 /1024 + " mb");
	  	System.out.println("Space free : " + freeSpace /1024 /1024 + " mb");
	  }

}
