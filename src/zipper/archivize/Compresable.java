/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zipper.archivize;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Dawid
 */
public interface Compresable
{
    public void archivize() throws FileNotFoundException,IOException;
    
    public enum CompressionLevel
    {
        _0(0),_1(1),_2(2),_3(3),_4(4),_5(5),_6(6),_7(7),_8(8),_9(9);
        
        private int value;        
        CompressionLevel(int x)
        {
            value = x;
        }
        
        public int getValue()
        {
            return value;
        }
    }
}
