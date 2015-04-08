/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treegui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import structure.Tree;
import uncompres.Unpack;

/**
 *
 * @author Dawid
 */
public class TreeGUIFrame extends JFrame
{
    private JTree visibleTree;
    private Unpack unpacker;
  
    public TreeGUIFrame(JTree tree,Unpack un)
    {
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        visibleTree = tree; 
        unpacker = un;
        
        visibleTree.getSelectionModel().addTreeSelectionListener(new TreeListener());
        JScrollPane pane = new JScrollPane(visibleTree);
        
        add(pane);
        
    }
    
    private class TreeListener implements TreeSelectionListener
    {
        @Override
        public void valueChanged(TreeSelectionEvent e)
        {
          DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) visibleTree.getLastSelectedPathComponent();
          Tree obj = (Tree)selectedNode.getUserObject();
          unpacker.uncompress(obj.getPath());
        }
        
    }
}
