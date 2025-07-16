package com.packers;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alex.store.Store;

public class MultiSkeletonPacker {

 private static final String CACHE_DIRECTORY = "data/cache/";
 private static final String SKELETONS_DIRECTORY = "C:/Users/Amma/Desktop/PackSkeletonscache0/";
 
 public static void main(String[] args) throws IOException {
  Store store =new Store(CACHE_DIRECTORY);
  for(File file : new File(SKELETONS_DIRECTORY).listFiles()) {
   int skeletonId = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
   
   store.getIndexes()[0].putFile(skeletonId >>> 7, skeletonId & 0x7f, getFileData(file.getAbsolutePath()));
   System.out.println("Packed skeletons with id: " + skeletonId);
  }
  
 }

 private static byte[] getFileData(String file) throws IOException {
  DataInputStream stream = new DataInputStream(new FileInputStream(file));
  byte[] data = new byte[stream.available()];
  stream.read(data);
  return data;
 }
}