
package uncompres;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Dawid
 */
public class Unpack 
{
    public static void main(String[] args) throws IOException
    {
        ZipFile zip = new ZipFile("D:\\Studia\\p.zip"); 
        ZipEntry entry;
        
        Enumeration< ? extends ZipEntry> enums = zip.entries();
        
        while(enums.hasMoreElements())
        {
            entry = enums.nextElement();
           // System.out.println(entry.getName());
           
        }
    }
}
