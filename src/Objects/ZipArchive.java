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

	private FilesComparer comparer;


	//private SizeFilesMap filesMD5HashMap;
	
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
        		
        		if (comparer.getNewFileFlag(ConvertNames.getFileNameWithExt(allPropers.getSftpSrcFullFileNamesList().get(i))))
        			mvFilesToArchive(allPropers.getSftpSrcFullFileNamesList().get(i));
        		else {
        			new File(allPropers.getSftpDestFilePath() + ConvertNames.getFileNameWithExt(allPropers.getSftpSrcFullFileNamesList().get(i))).delete();
        			Debug.log.info("The file " + ConvertNames.getFileNameWithExt(allPropers.getSftpSrcFullFileNamesList().get(i)) + " was NOT added to archive. It has same size!");
        		}
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
			
		MyFile copiedFile = new MyFile(allPropers.getSftpDestFilePath() + ConvertNames.getFileNameWithExt(copiedFileName));
		Path extFile = Paths.get(copiedFile.getFullString());
		String timeAppender = MyCalendar.getCurrentTimeNowCustomFormat("_HHmmss");
		String dateAppender = MyCalendar.getCurrentDateCustomFormat("_yyyyMMdd");
		String copiedFileNameWithTimestamp =copiedFile.getName() + dateAppender + timeAppender + copiedFile.getExtension();
        Path pathInZipfile = zipfs.getPath("/" + copiedFileNameWithTimestamp);          
        try {
	        Files.move(extFile,pathInZipfile, StandardCopyOption.REPLACE_EXISTING );
			Debug.log.info("The file "+ copiedFileNameWithTimestamp +" was added to archive " + zipFile.getFullFileNameWithPathAndDate());
			
			
		} catch (NoSuchFileException except){
			Debug.log.error("The file " + extFile + " not found on that path!");
			
		} 
        catch (IOException except){
			Debug.log.error(except);
			
		}
        
	}
	
	public void setZipFile (String fileNameWithPath) {
		this.zipFile = new MyFile(fileNameWithPath);
	}


	public void setUserSettings(Property prop){
		this.zipFile = new MyFile(prop.getZipFileFullName());
		this.allPropers = prop;
		//this.filesMD5HashMap = prop.getMD5HashMap();
		//this.copiedFile = new MyFile(property.getSftpDestFilePath() + property.getSftpDestFileName());		
	}

	public void setFilesComparerLink(FilesComparer comparer) {	
		this.comparer = comparer;
	}
	
	
	



}
