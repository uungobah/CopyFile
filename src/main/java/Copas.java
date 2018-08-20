import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;


public class Copas {

    public static void main(String[] args) throws IOException{
        /*Copy File using Stream*/
        File source = new File("C:\\Users\\Asus UX430U\\Desktop\\Source\\file1.txt");
        File dest = new File("C:\\Users\\Asus UX430U\\Desktop\\Dest\\file1.txt");
        long start =System.nanoTime();
        long end;

        copyFileUsingStream(source,dest);
        System.out.println("Time Taken by File Stream : " + (System.nanoTime() - start));

        /*Copy File using File Channel*/
        File sourceChannel = new File("C:\\Users\\Asus UX430U\\Desktop\\Source\\file2.txt");
        File destChannel = new File("C:\\Users\\Asus UX430U\\Desktop\\Dest\\file2.txt");
        start = System.nanoTime();
        copyFileUsingFileChannel(sourceChannel,destChannel);
        end = System.nanoTime();
        System.out.println("Time taken by FIle CHannel : "+ (end-start));

        File sourceApacheCommons = new File("C:\\Users\\Asus UX430U\\Desktop\\Source\\file3.txt");
        File destApacheCommons = new File("C:\\Users\\Asus UX430U\\Desktop\\Dest\\file3.txt");
        start = System.nanoTime();
        copyFileUsingApacheCommonsIO(sourceApacheCommons,destApacheCommons);
        end = System.nanoTime();
        System.out.println("Time taken by Apache Commons IO : "+(end-start));
    }

    private static void copyFileUsingStream (File source, File dest) throws IOException{
        InputStream input = null;
        OutputStream output = null;

        try{
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);

            byte [] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0){
                output.write(buf, 0, bytesRead);
            }

        }finally {
            input.close();
            output.close();
        }

    }

    private static void copyFileUsingFileChannel (File source, File dest) throws IOException{
        FileChannel input = null;
        FileChannel output = null;

        try {
            input = new FileInputStream(source).getChannel();
            output = new FileOutputStream(dest).getChannel();

            output.transferFrom(input,0,input.size());
        }finally {
            input.close();
            output.close();
        }
    }

   /* private static void copyFileUsingJava7Files (File source, File dest)throws IOException{
        Files.copy(source.toPath(),dest.toPath());
    }*/

    private static void copyFileUsingApacheCommonsIO(File source, File dest)
        throws IOException {
        FileUtils.copyFile(source, dest);
    }

}
