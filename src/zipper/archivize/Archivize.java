
package zipper.archivize;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 *
 * @author Dawid
 */
public class Archivize implements Compresable
{
    private ZipOutputStream zipOut;
    
    private CheckedOutputStream check;
    
    private FileInputStream fileIn;
    
   // private String basePath;
    
    private int buff;
    
    private String message;
    
    private CompressionLevel compressionLv;
    
    private final String source;
    
    private final String target;
    
    private long checkSum;
   
    
    /**
     * 
     * @param comment Comments with describes our archive
     * @param sourcePath Path where which contain files to archive
     * @param targetPath Path where archive must be write
     * @param lv  Level of compression CompressionLevel from Compresable
     */
    public Archivize(String comment, String sourcePath, String targetPath,CompressionLevel lv)
    {
        message = comment;
        source = sourcePath;
        target = targetPath;
        compressionLv = lv;
       
        
        buff = 1024;
    }
    /**
     * 
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void archivize() throws FileNotFoundException, IOException
    {    
            FileOutputStream fos = new FileOutputStream(target);
            check = new CheckedOutputStream(fos, new Adler32());            
            zipOut = new ZipOutputStream(new BufferedOutputStream(check));            
            //Seting basic ZIP info
           
            zipOut.setMethod(ZipOutputStream.DEFLATED);//method of archive            
            zipOut.setLevel(compressionLv.getValue());//must be from <0-9> set             
            zipOut.setComment(message);    
            
            //Start of archive
            addFile(new File(source));      
            checkSum = check.getChecksum().getValue();
            
            zipOut.close();       
    }

    private void addFile(File file) throws FileNotFoundException, IOException
    {
           if(file.isDirectory())
                for(File f:file.listFiles())
                    addFile(f);
         
           if(file.isFile())  
               toArchive(file,file.getAbsolutePath().replace(source+File.separator, ""));
    }

    private void toArchive(File file,String entryName) throws FileNotFoundException, IOException
    {
        System.out.println(file.getAbsoluteFile()+" =>  "+entryName);
        ZipEntry entry = new ZipEntry(source.substring(source.lastIndexOf(File.separator)+1)+File.separator+entryName);   
       
        zipOut.putNextEntry(entry);
        
        fileIn = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fileIn,buff);
        
        int c;
        byte[] BUFFER = new byte[buff];
        while((c=bis.read(BUFFER, 0, buff)) > 0)        
            zipOut.write(BUFFER, 0, c);
        
        bis.close();       
    } 
    
    private long getCheckSum()
    {
        return checkSum;
    }
    /**
     * 
     */
   
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Archivize a = new Archivize("Comments", "D:\\Studia\\Elektrotechnika\\Architektura systemów komputerowych\\Architektura","D:\\p.zip", CompressionLevel._0);
        a.archivize();
        System.out.println(a.getCheckSum());        
    }
    
}