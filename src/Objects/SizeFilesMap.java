package Objects;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class SizeFilesMap {
	
	private HashMap<String,String>  map;
/*	private String filename = "";
	private String hash = "";*/
	
	public SizeFilesMap () {
		map = new HashMap<String,String>();
	}


	public String getHash(String key) {
		return map.get(key);
	}

	public void putHash(String key, String hash) {
		map.put(key, hash);
	}
	
	public HashMap<String,String> getSizeFilesMap() {
		return map;
	}
	
	public void printFilesMap() {
        Set<Entry<String,String>> hashSet = map.entrySet();
        for(Entry entry : hashSet ) {
        	Debug.log.debug("Md5HashMapFileName: ["+ entry.getKey()+"] , hash = ["+entry.getValue() + "]");
        }
	}
	
	public Set<Entry<String,String>> entrySet() {
		return map.entrySet();
	}

}
