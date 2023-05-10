package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            PrintStream fileStream;
            fileStream = new PrintStream(new FileOutputStream("output.txt"));

            JCudaAccessTime cudaBench = new JCudaAccessTime();
            double startTime, endTime, averageElapsedTime, score;
            double constant=1000;

            cudaBench.initialize();
            startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++)
            {
                cudaBench.run();
            }
            endTime = System.nanoTime();
            averageElapsedTime = (endTime - startTime) / 1000000.0 / 100000;
            score = constant / averageElapsedTime;
            fileStream.println(score);
            cudaBench.cleanup();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
