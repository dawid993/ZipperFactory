package uncompres;

import java.awt.EventQueue;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
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
        tree = new TreeStructure(root);
    }
    
    /**
     * This method prepares tree structure which contains files path
     * @throws IOException 
     */
    public void prepareFileUnpacking() throws IOException
    {   
        Enumeration< ? extends ZipEntry> enums = zip.entries(); 
        String[] path;
        
        while(enums.hasMoreElements())
        {
          path = enums.nextElement().getName().split("/");// \ or / to solve
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
     * @param filePath 
     */
    @Override
    public void uncompress(String path)
    {
        //System.out.println(path);
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
