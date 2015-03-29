/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.Map;

/**
 *
 * @author Dawid
 */
public class TreeStructure 
{
    private Tree root;
    
    public TreeStructure(Tree nodeRoot)
    {
        root = nodeRoot;
    }
    
    public void add(Tree node,String[] paths,int i)
    {       
        Tree nextNode = node.getChild(paths[i]);
        
        if(nextNode == null)        
            nextNode = node.addChild(paths[i]);
        
        i++;      
        if(i<paths.length)
            add(nextNode,paths,i);
    }
    
    public void view(Tree node,String prefix)
    {
        System.out.println(prefix+" "+node.getPath());
        
        Map<String,Tree> map = node.getChildrens();
        
        if(map.isEmpty())
            return;
        
        prefix = prefix+"    *";
        for(Map.Entry<String,Tree> entry:map.entrySet())        
            view(entry.getValue(),prefix);
        
    }
}
