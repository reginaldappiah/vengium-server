package com.packers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.alex.store.Store;

public class AnimationPacker {
	private static final String ANIMATIONS_DIRECTORY = "C:/Users/Reginald/Desktop/317/";
	private static final String CACHE_DIRECTORY = "./data/cache/";

	@SuppressWarnings("resource")
	private static byte[] getFileData(String file) throws IOException {

		final DataInputStream stream = new DataInputStream(new FileInputStream(file));
		final byte[] data = new byte[stream.available()];

		stream.read(data);
		stream.close();
		return data;
	}

	public static void main(String[] args) throws IOException {
		final Store store = new Store(CACHE_DIRECTORY);

		for (final File file : new File(ANIMATIONS_DIRECTORY).listFiles()) {
			final int animationId = Integer.parseInt(file.getName().substring(0, file.getName().length() - 4));
			System.out.println("Animation packed:" + animationId + " successfully!");
			store.getIndexes()[20].putFile(animationId >>> 7, animationId & 0x7f, getFileData(file.getAbsolutePath()));
		}
	}
}
