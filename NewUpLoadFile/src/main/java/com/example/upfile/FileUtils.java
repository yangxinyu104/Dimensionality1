package com.example.upfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;

public class FileUtils {


    public static byte[] getBlock(long offset, File file, int blockSize) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        byte[] result = new byte[blockSize];
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile(file, "r");
            accessFile.seek(offset);
            int readSize = accessFile.read(result);
            if (readSize == -1) {
                return null;
            } else if (readSize == blockSize) {
                return result;
            } else {
                byte[] tmpByte = new byte[readSize];
                System.arraycopy(result, 0, tmpByte, 0, readSize);
                return tmpByte;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

}