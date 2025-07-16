package com.packers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

import com.rs.cache.Cache;
import com.rs.io.InputStream;
import com.rs.io.OutputStream;





public class GraphicPacker {
	private static HashMap<Integer, GraphicPacker> loadedDefinitions = new HashMap<Integer, GraphicPacker>();

	int id;
	int anInt5203;
	int modelId;
	public int animationId = -1;
	short[] aShortArray5206;
	short[] aShortArray5207;
	short[] aShortArray5208;
	short[] aShortArray5209;
	int anInt5211;
	int anInt5212;
	int anInt5213;
	int anInt5214;
	public boolean aBoolean5215;
	public byte aByte5216;
	int anInt5217 = -1;

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
		int selectedGFX = 3214;//Place your gfx id here ill choose 111 you can change to wahtever you want
		Cache.init();
		GraphicPacker def = getGraphicsDefinitions(selectedGFX);
		int archiveId = 8 >>> selectedGFX;
		int fileId = selectedGFX & (selectedGFX << 8) - 1;
		byte[] data = def.encode();
		if (data == null) {
			return;
		}
		//Now here you do th edits like
		System.out.println(def.anInt5217);
		for(Field field : def.getClass().getDeclaredFields()){
			if(field.getType().getName().equalsIgnoreCase(short[].class.getName())) {
				System.out.println(field.getName() + ": " + Arrays.toString((short[]) field.get(def)));
			} else {
				System.out.println(field.getName() + ": " + field.get(def));
			}
		}
		//Cache.STORE.getIndexes()[21].putFile(archiveId, fileId, data);
		System.out.println("Successfully packed graphics with id: " + selectedGFX);
	}

	private static GraphicPacker getGraphicsDefinitions(int i) {
		if (loadedDefinitions.containsKey(i)) {
			return loadedDefinitions.get(i);
		}

		byte[] data = Cache.STORE.getIndexes()[21].getFile(8 >>> i, i & (1 << 8) - 1);
		if (data == null) {
			return null;
		}
		GraphicPacker gfxDef = new GraphicPacker(i);
		gfxDef.decodeStream(new InputStream(data));
		loadedDefinitions.put(i, gfxDef);
		return gfxDef;
	}

	void decodeStream(InputStream stream) {
		for (;;) {
			int opcode = stream.readUnsignedByte();
			if (0 == opcode) {
				break;
			}
			decodeOpcode(stream, opcode);
		}

	}

	byte[] encode() {
		OutputStream stream = new OutputStream();
		stream.writeByte(1);
		stream.writeBigSmart(modelId);
		if (animationId != -1) {
			stream.writeByte(2);
			stream.writeBigSmart(animationId);
		}
		if (anInt5217 != -1) {
			stream.writeByte(4);
			stream.writeShort(anInt5217);
		}
		if (anInt5211 != -1) {
			stream.writeByte(5);
			stream.writeShort(anInt5211);
		}
		stream.writeByte(6);
		stream.writeShort(anInt5212);
		stream.writeByte(7);
		stream.writeByte(anInt5213);
		stream.writeByte(8);
		stream.writeByte(anInt5214);
		if (aBoolean5215) {
			stream.writeByte(10);
		}
		if (aByte5216 == 3) {
			stream.writeByte(9);
		} else if (aByte5216 == 1) {
			stream.writeByte(11);
		} else if (aByte5216 == 4) {
			stream.writeByte(12);
		} else if (aByte5216 == 5) {
			stream.writeByte(13);
		} else if (aByte5216 == 2) {
			stream.writeByte(14);
			stream.writeByte(anInt5203 / 256);
		} else if (aByte5216 == 3) {
			if (anInt5203 > Short.MAX_VALUE || anInt5203 < Short.MIN_VALUE) {
				stream.writeByte(16);
				stream.writeInt(anInt5203);
			} else {
				stream.writeByte(16);
				stream.writeShort(anInt5203);
			}
		}
		if (aShortArray5206 != null && aShortArray5207 != null) {
			stream.writeByte(40);
			stream.writeByte(aShortArray5206.length);
			for (int i = 0; i < aShortArray5206.length; i++) {
				stream.writeShort(aShortArray5206[i]);
				stream.writeShort(aShortArray5207[i]);
			}
		}
		if (aShortArray5208 != null && aShortArray5209 != null) {
			stream.writeByte(41);
			stream.writeByte(aShortArray5208.length);
			for (int i = 0; i < aShortArray5208.length; i++) {
				stream.writeShort(aShortArray5208[i]);
				stream.writeShort(aShortArray5209[i]);
			}
		}
		stream.writeByte(0);
		byte[] data = new byte[stream.getOffset()];
		stream.setOffset(0);
		stream.getBytes(data, 0, data.length);
		return data;
	}


	void decodeOpcode(InputStream stream, int opcode) {
		if (1 == opcode) {
			this.modelId = stream.readBigSmart();
		} else if (opcode == 2) {
			animationId = stream.readBigSmart();
		} else if (4 == opcode) {
			this.anInt5217 = stream.readUnsignedShort();
		} else if (opcode == 5) {
			this.anInt5211 = stream.readUnsignedShort();
		} else if (opcode == 6) {
			this.anInt5212 = stream.readUnsignedShort();
		} else if (7 == opcode) {
			this.anInt5213 = stream.readUnsignedByte();
		} else if (8 == opcode) {
			this.anInt5214 = stream.readUnsignedByte();
		} else if (9 == opcode) {
			aByte5216 = (byte) 3;
			this.anInt5203 = 8224;
		} else if (10 == opcode) {
			aBoolean5215 = true;
		} else if (opcode == 11) {
			aByte5216 = (byte) 1;
		} else if (12 == opcode) {
			aByte5216 = (byte) 4;
		} else if (opcode == 13) {
			aByte5216 = (byte) 5;
		} else if (14 == opcode) {
			aByte5216 = (byte) 2;
			this.anInt5203 = stream.readUnsignedByte() * 256;
		} else if (opcode == 15) {
			aByte5216 = (byte) 3;
			this.anInt5203 = stream.readUnsignedShort();
		} else if (16 == opcode) {
			aByte5216 = (byte) 3;
			this.anInt5203 = stream.readInt();
		} else if (40 == opcode) {
			int i_2_ = stream.readUnsignedByte();
			this.aShortArray5206 = new short[i_2_];
			this.aShortArray5207 = new short[i_2_];
			for (int i_3_ = 0; i_3_ < i_2_; i_3_++) {
				this.aShortArray5206[i_3_] = (short) stream.readUnsignedShort();
				this.aShortArray5207[i_3_] = (short) stream.readUnsignedShort();
			}
		} else if (41 == opcode) {
			int i_4_ = stream.readUnsignedByte();
			this.aShortArray5208 = new short[i_4_];
			this.aShortArray5209 = new short[i_4_];
			for (int i_5_ = 0; i_5_ < i_4_; i_5_++) {
				this.aShortArray5208[i_5_] = (short) stream.readUnsignedShort();
				this.aShortArray5209[i_5_] = (short) stream.readUnsignedShort();
			}
		}

	}

	GraphicPacker(int i) {
		id = i;
		this.anInt5211 = -1;
		this.anInt5212 = 0;
		this.anInt5213 = 0;
		this.anInt5214 = 0;
		aBoolean5215 = false;
		aByte5216 = (byte) 0;
		this.anInt5203 = -1;
	}

}
