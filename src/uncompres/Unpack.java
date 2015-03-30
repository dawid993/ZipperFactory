
package uncompres;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import structure.Tree;
import structure.TreeNode;
import structure.TreeStructure;

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
        
        String[] path = enums.nextElement().getName().split("/");
        TreeNode root = new TreeNode(path[0],null);
        TreeStructure tree = new TreeStructure(root);
        
        if(path.length>1)
            tree.add(root,path,1);
        
        while(enums.hasMoreElements())
        {
            path = enums.nextElement().getName().split("/");
                        
            //for(String s:path)
             //   System.out.print(s+" ");

           // System.out.println("");
            
            tree.add(root,path,1);
        }
        
        tree.view(root, "");
        
    }  
}
