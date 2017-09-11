package Objects;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.*;

public class ZipArchive  {
    private volatile URI uri = null;
	private Map<String, String> env = new HashMap<>();
	private FileSystem zipfs;
	private MyFile zipFile;
	private Property allPropers;
	private String testCopiedFileName;

	
	public void addFileToArchive() {
		
        if (isExistArchiveFilePath())
        	checkArchiveFileName();
        else 
        	Debug.log.warn("Archive file directory is not exist!");
	}  
	
	public boolean isExistArchiveFilePath(){
		File archiveFilePath = new File(zipFile.getPath());
		return archiveFilePath.isDirectory();										
	}
	
	public void checkArchiveFileName(){
		if (isArchiveFileNameCorrect())
			moveFilesToArchive();
		else 		
			Debug.log.error("Check archive file name! It must be in <name>.zip format.");
	}
	
	public boolean isArchiveFileNameCorrect() {
		String archivefShortFileName = zipFile.getFullString();
		return (archivefShortFileName.contains(".zip") && (archivefShortFileName.indexOf('.') != 0));
	}
	
	public boolean moveFilesToArchive()	{
		
		if (!isExistArchiveFile()) {
        	env.put("create", "true");
        	Debug.log.info("Archive file is not exist. File " + zipFile.getFullFileNameWithPathAndDate() + " is created.");	  	
        }
        else {
        	Debug.log.debug("Archive file "+ zipFile.getFullFileNameWithPathAndDate() + " is already exist!");
			env.put("create", "false");
        }	
		try {
        	uri = URI.create(ConvertNames.createUriString(zipFile.getFullFileNameWithPathAndDate()));
        	zipfs = FileSystems.newFileSystem(uri, env);
        	
        	for (int i=0; i<allPropers.getSftpSrcFileCount(); i++) {
        	mvFilesToArchive(allPropers.getSftpSrcFullFileNamesList().get(i));
        	}
        	
        	zipfs.close();
        	return true;
        } catch (Exception except) {
        		Debug.log.error(except);
        		return false;
        }		
	}

	private boolean isExistArchiveFile(){
		File archiveFile = new File(zipFile.getFullFileNameWithPathAndDate());
		return archiveFile.isFile();		
	}


	private void mvFilesToArchive(String copiedFileName) {
			
		MyFile copiedFile = new MyFile(allPropers.getSftpDestFilePath() + copiedFileName);
		Path extFile = Paths.get(copiedFile.getFullString());
		String timeAppender = MyCalendar.getCurrentTimeNowCustomFormat("_HHmmss");
		String dateAppender = MyCalendar.getCurrentDateCustomFormat("_yyyyMMdd");
		String copiedFileNameWithTimestamp =copiedFile.getName() + dateAppender + timeAppender + copiedFile.getExtension();
        Path pathInZipfile = zipfs.getPath("/" + copiedFileNameWithTimestamp);          
        try {
	        Files.move( extFile,pathInZipfile, StandardCopyOption.REPLACE_EXISTING );
			Debug.log.info("The file "+ copiedFileNameWithTimestamp +" was added in archive " + zipFile.getFullFileNameWithPathAndDate());
			
			
		} catch (NoSuchFileException except){
			Debug.log.error("Файл " + extFile + " не найден по указанному пути.");
			
		} 
        catch (IOException except){
			Debug.log.error(except);
			
		}
        
	}
	
	public void setZipFile (String fileNameWithPath) {
		this.zipFile = new MyFile(fileNameWithPath);
	}
	public void testSetCopiedFile(String copiedFile){
		this.testCopiedFileName = copiedFile;
	}

	public void setUserSettings(Property property){
		this.zipFile = new MyFile(property.getZipFileFullName());
		this.allPropers = property;
		//this.copiedFile = new MyFile(property.getSftpDestFilePath() + property.getSftpDestFileName());		
	}
	
	
	



}
