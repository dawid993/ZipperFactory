package uncompres;

import java.awt.EventQueue;
import java.io.IOException;
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
        zip = new ZipFile(source);
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
          path = enums.nextElement().getName().split("/");
          tree.add(root,path,0);
        }    
        
        tree.view(root, "");        
    }
    
    public void showTree()
    {
        TreeGUIFrame frame = new TreeGUIFrame(tree.prepareGUIView());   
    }
    /**
     * Implementation in future
     * @param filePath 
     */

    @Override
    public void uncompress(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param args
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException
    {
        final Unpack un = new Unpack("D:\\p.zip");
        un.prepareFileUnpacking();
        
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() 
            {
               un.showTree();
            }            
        });
    }    
}
