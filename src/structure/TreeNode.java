
package structure;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dawid
 * 
 * This class is implementation of Tree interface
 * which provides tree hierarchy in archive files.
 * It's used to get relational hierarchy of flat entry specification.
 */
public class TreeNode implements Tree
{
    private String name;
    
    private String path;
    
    private TreeNode parent;
    
    private Map<String,Tree> childrens;
    
    
    /**
     * Its create new node object which can contains reference to his parent and map of his childrens.
     * @param fileName Name of file whih this node represents.
     * @param entryParent Parent of this node. If this node is root then node is simply null value.
     */
    public TreeNode(String fileName,TreeNode entryParent)
    {
        name = fileName;
        parent = entryParent;
        childrens = new HashMap<String,Tree>();
        
        if(parent != null)
             path = parent.getPath()+"/"+fileName;
        else path=name;
    }
    
    /**
     * Geting full path to file in archive
     * @return path
     */
    @Override
    public String getPath()
    {
        return path.replace("root\\", "");//replacing artificial node, parent of everything
    }
    
    /**
     * Returning name of file in archive
     * @return name
     */
    @Override
    public String getName()
    {
        return name;
    }
    
    /**
     * Ading new child to this node
     * @param key Key to map
     * @return node reference which had been created
     */
    @Override
    public Tree addChild(String key)
    {
        childrens.put(key, new TreeNode(key, this));
        return getChild(key);
    }   
    
    
    /**
     * Returning child which is described by the key
     * @param key acces to map
     * @return Node children
     */
    @Override
    public Tree getChild(String key)
    {
        return childrens.get(key);
    }
    
    /**
     * Returning all childrens of this node
     * @return childrens
     */
    @Override
    public Map<String,Tree> getChildrens()
    {
        return childrens;
    }
    
    @Override
    public boolean isChildrensEmpty()
    {
        return childrens.isEmpty();
    }
    
    public void setMap(Map<String,Tree> map)
    {
        childrens = map;
    }
    
}
