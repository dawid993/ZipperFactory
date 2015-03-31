package structure;

import java.util.Map;
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
     * Recursively ading to branches to JTree.
     * @param node prepared node
     * @return 
     */
    private DefaultMutableTreeNode generateGUIView(Tree node)
    {
        DefaultMutableTreeNode dnode = new DefaultMutableTreeNode(node.getPath());
        
        Map<String,Tree> map = node.getChildrens();
        
        for(Map.Entry<String,Tree> entry:map.entrySet())
            dnode.add(generateGUIView(entry.getValue()));
        
        return dnode;
    }
}
