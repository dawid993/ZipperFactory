/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treegui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
    private JTextField unpackPlace;
  
    public TreeGUIFrame(JTree tree,Unpack un)
    {
        setLayout(new BorderLayout());
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        visibleTree = tree; 
        unpacker = un;
        unpackPlace = new JTextField(System.getProperty("user.home")+System.getProperty("file.separator"));
        
        visibleTree.getSelectionModel().addTreeSelectionListener(new TreeListener());
        JScrollPane pane = new JScrollPane(visibleTree);
        
        add(pane,BorderLayout.CENTER);
        add(unpackPlace,BorderLayout.SOUTH);
        
    }
    
    private class TreeListener implements TreeSelectionListener
    {
        @Override
        public void valueChanged(TreeSelectionEvent e)
        {
          DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) visibleTree.getLastSelectedPathComponent();
          Tree obj = (Tree)selectedNode.getUserObject();
          
          ArrayList<String> list = new ArrayList<String>();
          if(obj.isChildrensEmpty() == false)
          {  
              for(Map.Entry<String,Tree> element:obj.getChildrens().entrySet())              
               list.add(element.getValue().getPath()); 
          }          
          else
              list.add(obj.getPath());
          
          
          unpackPlace.setText(System.getProperty("user.home")+System.getProperty("file.separator")
          +obj.getPath());
          unpacker.uncompress(list,unpackPlace.getText());
        }
        
    }
}
