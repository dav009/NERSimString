package utils;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * User: Boris Kozlov
 * Date: 16/06/12
 * Time: 16:08
 */
public class MappedMemoryTree
{
    static int length = 0x8FFFFFF; // 128 Mb

    public static void main(String[] args) throws Exception
    {
        MappedByteBuffer out =
                new RandomAccessFile("test.dat", "rw").getChannel()
                        .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length; i++)
            out.put((byte)'x');
        System.out.println("Finished writing");
        for(int i = length/2; i < length/2 + 6; i++)
            System.out.print((char)out.get(i));
    }
}
