package Objects;

import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipNativeInitializationException;

public class SevenZipMy
{

	public static void main(String[] args)
	{
		Debug.initDebugLog();
		try {
            SevenZip.initSevenZipFromPlatformJAR();
            Debug.log.info("7-Zip-JBinding library was initialized");
        } catch (SevenZipNativeInitializationException e) {
            e.printStackTrace();
        }

	}

}
