package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Objects.ConvertNames;

public class TConvertNames
{
	private String fullFileNameWithPath = "C:\\temp\\files\\MTA-20170526_vld_17_18.mta";
	
	
	@Test
	public void testFilePath()
	{
		assertEquals("C:\\temp\\files\\", ConvertNames.getDirectoryPathString(fullFileNameWithPath));		
	}
	
	@Test
	public void testGetFullFileName()
	{
		assertEquals("MTA-20170526_vld_17_18.mta", ConvertNames.getFileNameWithExt(fullFileNameWithPath));		
	}
	
	@Test
	public void testGetFileName()
	{
		assertEquals("MTA-20170526_vld_17_18", ConvertNames.getFileName(fullFileNameWithPath));		
	}
	
	
	@Test
	public void testGetFileExtension()
	{
		assertEquals(".mta", ConvertNames.getFileExensiont(fullFileNameWithPath));		
	}

}
