package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Objects.ConvertNames;

public class TConvertNames
{
	private String fullFileNameWithPathWinFormat = "C:\\temp\\files\\MTA-20170526_vld_17_18.mta";
	private String fullFileNameWithPathUnixFormat = "/var/log/avaya/aes/TSAPI/g3trace-cm5s8400-1.trace.out";
	private String fullFileNameWithPathWUnixFormat = ConvertNames.convertFilePathFromWinToUnix(fullFileNameWithPathWinFormat);
	
	
	@Test
	public void testFilePathWin()
	{
		assertEquals("C:\\temp\\files\\", ConvertNames.getDirectoryPathStringWinFormat(fullFileNameWithPathWinFormat));
	}
	
	@Test
	public void testFilePathUnix()
	{
		assertEquals("C:/temp/files/", ConvertNames.getDirectoryPathStringUnixFormat(fullFileNameWithPathWUnixFormat));		
	}
	
	
	@Test
	public void testGetFullFileName()
	{
		assertEquals("MTA-20170526_vld_17_18.mta", ConvertNames.getFileNameWithExt(fullFileNameWithPathWinFormat));
		assertEquals("MTA-20170526_vld_17_18.mta", ConvertNames.getFileNameWithExt(fullFileNameWithPathWUnixFormat));
		assertEquals("g3trace-cm5s8400-1.trace.old", ConvertNames.getFileNameWithExt(fullFileNameWithPathUnixFormat));
	}
	
	@Test
	public void testGetFileName()
	{
		assertEquals("MTA-20170526_vld_17_18", ConvertNames.getFileName(fullFileNameWithPathWinFormat));		
	}
	
	
	@Test
	public void testGetFileExtension()
	{
		assertEquals(".mta", ConvertNames.getFileExension(fullFileNameWithPathWinFormat));		
	}
	
	@Test
	public void testGetDriveLetter()
	{
		assertEquals("c:", ConvertNames.getDriveLetterFromFullFileName(fullFileNameWithPathWinFormat));		
	}

}
