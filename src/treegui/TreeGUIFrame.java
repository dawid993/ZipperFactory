/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treegui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 *
 * @author Dawid
 */
public class TreeGUIFrame extends JFrame
{
    private JTree visibleTree;
  
    public TreeGUIFrame(JTree tree)
    {
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        visibleTree = tree;             
        
        JScrollPane pane = new JScrollPane(visibleTree);
        
        add(pane);
        
    }
}
