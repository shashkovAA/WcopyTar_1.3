package Objects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class FileSize {

	static File file;
	static long size;
	
	public FileSize(){}
	
	public static String getFileSize(String filename){
	
		
	size = new File(filename).length();
	Debug.log.debug("Size of file " + filename + " is " + size);
	
	return String.valueOf(size);
	}
}
