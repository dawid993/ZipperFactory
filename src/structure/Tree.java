package structure;

import java.util.Map;

/**
 * @author Dawid
 * 
 * Interface provides funcionality of Tree.
 * It's specially create to get archive hierarchy.
 * 
 * For Example
 * 
 *  Folder1
 *          Folder2
 *                  File1
 *                  File2
 *          Folder3
 *                  File3
 *          File4
 *          Folder5
 *                  Folder6
 *                          File5
 *                          File6
 * 
 * Nodes storage information about his name and his full path in archive.
 * 
 * Example
 *   Name: File5 
 *   Path: Folder1\Folder5\Folder6\File5
 * 
 * 
 *
 */
public interface Tree
{
    /**
     * Adding new child to node
     * @param key Key which is need to get value from node specified by this key.
     * @return Object of class which implements Tree interface
     */
    public Tree addChild(String key);
    
    /**
     * Getting child from node
     * @param key Key which is asigned to value
     * @return Object which implements Tree interface
     */
    public Tree getChild(String key);
    
    /**
     * Returning all of childrens from node
     * @return Map which contains childrens node
     */
    public Map<String,Tree> getChildrens();
    
    /**
     * Geting name of children
     * @return name of children
     */
    public String getName();
    
    
    /**
     * Geting full path to children
     * @return full path to children
     */
    public String getPath();
    
    public boolean isChildrensEmpty();
  
}
