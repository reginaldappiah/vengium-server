package com.packers;


import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.alex.store.Store;
import com.rs.Settings;


public class SkeletonDumper {
	
 private static final String CACHE_DIRECTORY = "C:\\Users\\Reginald\\Desktop\\RSPS\\Cache\\838cache\\";
 
 public static void main(String[] args) throws IOException {
	 

	 
  Store store = new Store(CACHE_DIRECTORY);
  for(int skeletonId = 0; skeletonId < 3000000; skeletonId++) {//look i have better way
   byte[] data = store.getIndexes()[0].getFile(skeletonId >>> 7, skeletonId & (1 << 7) - 1);
   if(data == null)
    continue;
   writeFile("C:\\Users\\Reginald\\Desktop\\838skeletons2\\" + skeletonId + ".dat", data);
  }
 }

 private static void writeFile(String path,  byte[] data) throws IOException {
  DataOutputStream stream = new DataOutputStream(new FileOutputStream(path));
  stream.write(data);
  stream.flush();
  stream.close();
 }
}