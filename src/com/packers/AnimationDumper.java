package com.packers;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.alex.store.Store;

public class AnimationDumper {


private static final String CACHE_DIRECTORY = "C:/Users/Amma/Desktop/RSPS Gadgets/rscd/data/";
	
 public static void main(String[] args) throws IOException {
  Store store = new Store(CACHE_DIRECTORY);
  for(int animationId = 0; animationId < 30000; animationId++) {
   byte[] data = store.getIndexes()[20].getFile(animationId >>> 7, animationId & 0x7f);
   if(data == null)
    continue;
   writeFile("C:/Users/Amma/Desktop/Packages/PackAnim/788animations/" + animationId + ".dat", data);
  }
 }

 private static void writeFile(String path,  byte[] data) throws IOException {
  DataOutputStream stream = new DataOutputStream(new FileOutputStream(path));
  stream.write(data);
  stream.flush();
  stream.close();
 }
} 