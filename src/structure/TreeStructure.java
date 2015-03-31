package structure;

import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

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
    
    public JTree prepareGUIView()
    {
        DefaultMutableTreeNode core = generateGUIView(root); 
        return new JTree(core);
    }
    
    private DefaultMutableTreeNode generateGUIView(Tree node)
    {
        DefaultMutableTreeNode dnode = new DefaultMutableTreeNode(node.getName());
        
        Map<String,Tree> map = node.getChildrens();
        
        for(Map.Entry<String,Tree> entry:map.entrySet())
            dnode.add(generateGUIView(entry.getValue()));
        
        return dnode;
    }
}
