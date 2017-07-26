package runner;

import java.util.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.*;

public class test {
    public static void main(String [] args) throws Throwable {
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        // locate file system by using the syntax 
        // defined in java.net.JarURLConnection
        URI uri = URI.create("jar:file:///c:/temp/zipfstest.zip");
        FileSystem zipfs = FileSystems.newFileSystem(uri, env);
       
        //FileSystem zipfs2 =FileSystems.getFileSystem(uri);
       
            Path externalTxtFile = Paths.get("/temp/test2.txt");
            Path pathInZipfile = zipfs.getPath("/test2.txt");
            
                   Files.copy( externalTxtFile,pathInZipfile, 
                              StandardCopyOption.REPLACE_EXISTING);  
                   
                   zipfs.close();           
    
    }
}
