/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uncompres;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import structure.TreeNode;
import structure.TreeStructure;

/**
 *
 * @author Dawid
 */
public class debug
{
 public static void main(String[] args) throws IOException
    {
        ZipFile zip = new ZipFile("D:\\E.zip"); 
        Enumeration< ? extends ZipEntry> enums = zip.entries();
        int i=0;
        while(enums.hasMoreElements())
        {
            ZipEntry e = enums.nextElement();
            
            System.out.println(e);
            if(!e.isDirectory())
            i++;
        }
        
        System.out.println(i);
       
        
    }     
}
