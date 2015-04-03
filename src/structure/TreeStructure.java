package structure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This class is like container for tree.
 * With property of a tree we remember only a root of the tree to access all elements
 * @author Dawid
 */
public class TreeStructure 
{
    private Tree root;
    
    public TreeStructure(Tree nodeRoot)
    {
        root = nodeRoot;        
    }
    
    /**
     * Recursively adding node to tree
     * @param node Adding child to this node
     * @param paths path of file
     * @param i iterator which is using to exit recursion
     */
    public void add(Tree node,String[] paths,int i)
    {       
        Tree nextNode = node.getChild(paths[i]);
        
        if(nextNode == null)        
            nextNode = node.addChild(paths[i]);
        
        i++;      
        if(i<paths.length)
            add(nextNode,paths,i);
    }
    
    /**
     * Textual view of tree
     * @param node from wich node start viewing
     * @param prefix to make view more readable
     */
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
    
    /**
     * Preparing graphical view of tree
     * @return JTree object containing nodes
     */
    public JTree prepareGUIView()
    {
        DefaultMutableTreeNode core = generateGUIView(root); 
        return new JTree(core);
    }
    
    /**
     * This method is responsile for sorting files by this way:
     * Direcotires->Directories name(alphabetical)->Files->Files name(alphabetical)
     * We are using node and this childrens map(HashMap)
     * Need to extract direcotires and files to specific map
     * Later merg this list in order first directories and files
     * @param node 
     */
    public void sortTreeNodes(Tree node)
    {
       if(node.isChildrensEmpty())
           return;
       
       Map<String,Tree> leafs = new TreeMap<String,Tree>();
       Map<String,Tree> nodes = new TreeMap<String,Tree>();
       
       for(Map.Entry<String,Tree> entry:node.getChildrens().entrySet())
       {
           if(entry.getValue().isChildrensEmpty())
               leafs.put(entry.getKey(), entry.getValue());
           else if(!entry.getValue().isChildrensEmpty())
               nodes.put(entry.getKey(),entry.getValue());           
       }
       
      Map<String,Tree> newMap = new LinkedHashMap<String,Tree>();
      
      for(Map.Entry<String,Tree> entry:nodes.entrySet())
          newMap.put(entry.getKey(), entry.getValue());
      
      for(Map.Entry<String,Tree> entry:leafs.entrySet())
          newMap.put(entry.getKey(), entry.getValue());
      
     TreeNode n = (TreeNode) node;
     n.setMap(newMap);  
     
     
    }
    
    /**
     * Recursively ading to branches of JTree.
     * @param node prepared node
     * @return 
     */
    private DefaultMutableTreeNode generateGUIView(Tree node)
    {
        sortTreeNodes(node);
        DefaultMutableTreeNode dnode = new DefaultMutableTreeNode(node.getName());
        
        Map<String,Tree> map = node.getChildrens();
        
        for(Map.Entry<String,Tree> entry:map.entrySet())
            dnode.add(generateGUIView(entry.getValue()));
        
        return dnode;
    }
}
