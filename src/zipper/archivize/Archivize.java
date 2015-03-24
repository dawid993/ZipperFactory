/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private String basePath;
    
    private int buff;
   
    
    public Archivize(int sizeBuffor)
    {
        buff = sizeBuffor;
    }
    /**
     * 
     * @param comment Archivum comment
     * @param sourcePath Path to the source of file/files which will be archive
     * @param targetPath Path to the file which will contain compressed file/files
     */
    @Override
    public void archivize(String comment, String sourcePath, String targetPath,int lv) throws FileNotFoundException, IOException
    {
            basePath = sourcePath;
            
            FileOutputStream fos = new FileOutputStream(targetPath);
            check = new CheckedOutputStream(fos, new Adler32());            
            zipOut = new ZipOutputStream(new BufferedOutputStream(check));
            
            //Seting basic ZIP info
            zipOut.setMethod(ZipOutputStream.DEFLATED);//method of archive
            zipOut.setLevel(lv);//must be from <0-9> set
            zipOut.setComment(comment);    
            
            //Start of archive
            System.out.println(sourcePath);
            addFile(new File(sourcePath));   
            
           
            zipOut.close();
       
    }

    private void addFile(File file) throws FileNotFoundException, IOException
    {
           if(file.isDirectory())
                for(File f:file.listFiles())
                    addFile(f);
           
           if(file.isFile())
               toArchive(file,file.getAbsolutePath().replace(basePath+"\\", ""));
    }

    private void toArchive(File file,String entryName) throws FileNotFoundException, IOException
    {
        System.out.println(file.getAbsoluteFile()+" "+entryName);
        ZipEntry entry = new ZipEntry(entryName);
        zipOut.putNextEntry(entry);
        
        fileIn = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fileIn,buff);
        
        int c;
        byte[] BUFFER = new byte[buff];
        while((c=bis.read(BUFFER, 0, buff)) > 0)        
            zipOut.write(BUFFER, 0, c);
       
       
       fileIn.close();
       
    }
    
    /**
     * 
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Archivize a = new Archivize(1024);
        a.archivize("Comments", "D:\\Studia\\Assembler","D:\\Studia\\p.zip", 5);
    }
    
}
