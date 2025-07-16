package com.packers;



import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alex.store.Store;

public class SkeletonPacker {

 private static final String CACHE_DIRECTORY = "C:/Users/Amma/Desktop/Vengium Source/data/cache/";
 private static final String SKELETON_DAT_FILE = "C:/Users/Amma/Desktop/PackAnim/17071_seq.dat";
 private static final int SKELETON_ID = 17071;
 
 public static void main(String[] args) throws IOException {
  Store store =new Store(CACHE_DIRECTORY);
  store.getIndexes()[0].putFile(SKELETON_ID >>> 7, SKELETON_ID & 0x7f, getFileData(SKELETON_DAT_FILE));
 }

 private static byte[] getFileData(String file) throws IOException {
  DataInputStream stream = new DataInputStream(new FileInputStream(file));
  byte[] data = new byte[stream.available()];
  stream.read(data);
  return data;
 }
}
