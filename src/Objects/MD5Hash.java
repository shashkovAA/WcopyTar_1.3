package Objects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5Hash {

	private static byte[] b = null;
	private static byte[] hash = null;
	
	public static String getMD5Hash(String filename){
	
	//Debug.log.debug("Start md5hash compute of file " + filename);
	
	
	try {
		
		b = Files.readAllBytes(Paths.get(filename));
	} catch (IOException e) {
		
		Debug.log.error(e.getMessage());
	}
	try {
		hash = MessageDigest.getInstance("MD5").digest(b);
	} catch (NoSuchAlgorithmException e) {
		
		Debug.log.error(e.getMessage());
	}
	String hashString = DatatypeConverter.printHexBinary(hash).toLowerCase();
	Debug.log.debug("md5Hash of file " + filename + " is " + hashString);
	
	return hashString;
	}
}
