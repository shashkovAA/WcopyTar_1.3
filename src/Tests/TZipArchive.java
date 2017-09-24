package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Objects.Debug;
import Objects.ZipArchive;


public class TZipArchive
{
	private ZipArchive zip = new ZipArchive();

	
	@Before
    public void setUp() throws Exception {

		Debug.initDebugLog();
		zip.setZipFile("G:\\copy\\1.zip");
		//zip.setCopiedFile("C:\\temp\\test.txt");
    }

    @After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testIsExistDirectory()
	{
		assertEquals(true, zip.isExistArchiveFilePath());
	}
	
	@Test
	public void testIsCorrecttArhiveFileName()
	{
		assertEquals(true, zip.isArchiveFileNameCorrect());
	}
	
	@Test
	public void testMoveFileToArchive()
	{
		assertEquals(true, zip.moveFilesToArchive());
	}

}
