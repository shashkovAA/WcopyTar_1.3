package Objects;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.*;

public class ZipArchive  {
    private URI uri = null;
	private Map<String, String> env = new HashMap<>();
	private FileSystem zipfs;
	private MyFile zipFile;
	private MyFile copiedFile;
	
	
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
			moveFileToArchive();
		else 		
			Debug.log.error("Check archive file name! It must be in <name>.zip format.");
	}
	
	public boolean isArchiveFileNameCorrect() {
		String archivefShortFileName = zipFile.getFullString();
		return (archivefShortFileName.contains(".zip") && (archivefShortFileName.indexOf('.') != 0));
	}
	
	public boolean moveFileToArchive()	{
		
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
        	mvFileToArchive();
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


	private void mvFileToArchive() {
			
		Path extFile = Paths.get(copiedFile.getFullString());
		String timeAppender = MyCalendar.getCurrentTimeNowCustomFormat("_HH_mm_ss");
		String dateAppender = MyCalendar.getCurrentDateCustomFormat("_ddMMyyyy");
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
	public void setCopiedFile(String copiedFile){
		this.copiedFile = new MyFile(copiedFile);
	}

	public void setUserSettings(Property property){
		this.zipFile = new MyFile(property.getZipFileFullName());
		this.copiedFile = new MyFile(property.getSftpDestFilePath() + property.getSftpDestFileName());		
	}
	
	
	



}
