
package uncompres;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import structure.TreeNode;

/**
 *
 * @author Dawid
 */
public class Unpack 
{
    public static void main(String[] args) throws IOException
    {
        ZipFile zip = new ZipFile("D:\\p.zip"); 
        Enumeration< ? extends ZipEntry> enums = zip.entries();
        
        String[] path = enums.nextElement().getName().split("\\\\");
        TreeNode root = new TreeNode(path[0],null);
        Unpack.add(root,path,1);
        
        while(enums.hasMoreElements())
        {
            path = enums.nextElement().getName().split("\\\\");
                        
            //for(String s:path)
             //   System.out.print(s+" ");

           // System.out.println("");
            
            Unpack.add(root,path,1);
        }
        
        root.showChildrens();
        
    }
    
    public static void add(TreeNode node,String[] paths,int i)
    {       
        TreeNode nextNode = node.getChild(paths[i]);
        
        if(nextNode == null)        
            nextNode = node.addChild(paths[i]);
        i++;    
        if(i<paths.length)
            add(nextNode,paths,i);    
        
    }
    
    public static void view(TreeNode node)
    {
        
    }
}
