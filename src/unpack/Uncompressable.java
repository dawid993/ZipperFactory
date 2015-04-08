/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unpack;

import java.util.List;

/**
 *
 * @author Dawid
 */
public interface Uncompressable
{
    public void uncompress(List<String> filePath,String destPath);
    
}
