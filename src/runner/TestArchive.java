package runner;

import java.nio.file.FileSystem;

import Objects.Debug;
import Objects.ZipArchive;

public class TestArchive
{

	public static void main(String[] args)
	{
		
		Debug.initDebugLog();
		ZipArchive zip = new ZipArchive();
		zip.setZipFile("G:\\copy\\1.zip");
		zip.setCopiedFile("C:\\temp\\MTA-20170526_vld_17_18.mta");
		zip.addFileToArchive();
		
	//	zip.addFileToArchive();

	}

}
