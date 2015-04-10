package uncompres;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import structure.Tree;
import structure.TreeNode;
import structure.TreeStructure;
import treegui.TreeGUIFrame;
import unpack.Uncompressable;

/**
 *
 * @author Dawid
 */

public class Unpack implements Uncompressable
{
    private String source;
    
    private Tree root;
    
    private String innerSeparator;
    
    private ZipFile zip;
    
    private TreeStructure tree;
    
    
    public Unpack(String zipFile) throws IOException
    {
        source = zipFile;
        root = new TreeNode("root",null);
        zip = new ZipFile(source,Charset.forName("Cp775"));
        //zip = new ZipInputStream(new BufferedInputStream(new FileInputStream(source)), Charset.forName("Cp775"));
        tree = new TreeStructure(root);
    }
    
    /**
     * This method prepares tree structure which contains files path
     * @throws IOException 
     */
    public void prepareFileUnpacking() throws IOException
    {       
        Enumeration<? extends ZipEntry> entries = zip.entries();
        String[] path;
        ZipEntry entry;
        
        while(entries.hasMoreElements())
        {           
          entry = entries.nextElement();
          path = entry.getName().split("/");// \ or / to solve
          tree.add(root,path,0);
        }     
    }
    
    public void showTree()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
              TreeGUIFrame frame = new TreeGUIFrame(tree.prepareGUIView(),Unpack.this);    
            }                    
        });        
    }
    /**
     * Implementation in future
     * @param paths 
     * @param unpackPath 
     */
    @Override
    public void uncompress(List<String> paths,String unpackPath) throws FileNotFoundException, IOException
    {   
        FileOutputStream fos;
        BufferedOutputStream bos;
        BufferedInputStream bis;
       
        
        for(String path:paths)
        {
            String replace = path.replace("\\", "/");
            ZipEntry entry = zip.getEntry(replace);
            
            
            
            File file = new File(unpackPath+System.getProperty("file.separator")+entry.getName());
            System.out.println(file.getAbsoluteFile());
            
            file.getParentFile().mkdirs();
            
            if(file.exists() == false)
              file.createNewFile();
            
            bis = new BufferedInputStream(zip.getInputStream(zip.getEntry(replace)));
           
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos, 1024);
            
            byte[] BUFFER = new byte[1024];
            int c;
            while((c = bis.read(BUFFER, 0, 1024)) != -1)
                bos.write(BUFFER, 0, c);
            
            bos.flush();
            bos.close();
           bis.close();
        } 
    }
    
    /**
     * 
     * @param args
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException
    {
        Unpack un = new Unpack("D:\\E.zip");
        un.prepareFileUnpacking();  
        
        un.showTree();
    }    
   
}
