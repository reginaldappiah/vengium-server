package com.packers;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alex.store.Store;

public class MultiAnimationPacker {

 private static final String CACHE_DIRECTORY = "data/cache/";
 private static final String ANIMATIONS_DIRECTORY = "C:/Users/Amma/Desktop/ANIMATION/";
 
 public static void main(String[] args) throws IOException {
  Store store =new Store(CACHE_DIRECTORY);
  for(File file : new File(ANIMATIONS_DIRECTORY).listFiles()) {
   int animationId = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
   
   store.getIndexes()[20].putFile(animationId >>> 7, animationId & 0x7f, getFileData(file.getAbsolutePath()));
   System.out.println("Packed animation with id: " + animationId);
  }
  
 }

 private static byte[] getFileData(String file) throws IOException {
  DataInputStream stream = new DataInputStream(new FileInputStream(file));
  byte[] data = new byte[stream.available()];
  stream.read(data);
  return data;
 }
}
