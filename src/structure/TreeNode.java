
package structure;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dawid
 */
public class TreeNode 
{
    private String name;
    
    private String path;
    
    private TreeNode parent;
    
    private Map<String,TreeNode> childrens;
    
    
    public TreeNode(String fileName,TreeNode entryParent)
    {
        name = fileName;
        parent = entryParent;
        childrens = new HashMap<String,TreeNode>();
        
        if(parent != null)
             path = parent.getPath()+File.separator+fileName;
        else path=name+File.separator;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public String getName()
    {
        return name;
    }
    
    public TreeNode addChild(String key)
    {
        childrens.put(key, new TreeNode(key, this));
        return getChild(key);
    }   
    
    public TreeNode getChild(String key)
    {
        return childrens.get(key);
    }
    
    public void showChildrens()
    {
        for(Map.Entry<String,TreeNode> entry:childrens.entrySet())
        {
            System.out.println(entry.getValue().getName());
        }
    }
    
}
