/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zipper.archivize;

/**
 *
 * @author Dawid
 */
public interface Compresable
{
    public void archivize(String comment,String sourcePath,String targetPath);
}
