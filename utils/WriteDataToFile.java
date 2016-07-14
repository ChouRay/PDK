package com.zl.videoplayer.help;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import android.os.Environment;
import android.util.Log;

public class WriteDataToFile {
	private final static String TAG = "WriteDataToFile";
	final static String FILE_PITCH = "/pitch.txt";
	final static String FILE_YAW = "/yaw.txt";
	final static String FILE_UN_PITCH = "/un-pitch.txt";
	final static String FILE_UN_YAW = "/un-yaw.txt";

	public static void writePitchFile(float pitch) {

		try {
			File sdCardDir = Environment.getExternalStorageDirectory();

			File pitchFile = new File(sdCardDir.getCanonicalPath() + FILE_PITCH);
			if (!pitchFile.exists()) {
				pitchFile.createNewFile();
			}

			RandomAccessFile raf_pitch = new RandomAccessFile(pitchFile, "rw");

			raf_pitch.seek(pitchFile.length());
			raf_pitch.writeBytes(Float.toString(pitch) + "\r\n");
			raf_pitch.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void writeYawFile(float yaw) {

		try {
			File sdCardDir = Environment.getExternalStorageDirectory();

			File yawFile = new File(sdCardDir.getCanonicalPath() + FILE_YAW);
			if (!yawFile.exists()) {
				yawFile.createNewFile();
			}

			RandomAccessFile raf_yaw = new RandomAccessFile(yawFile, "rw");

			raf_yaw.seek(yawFile.length());
			raf_yaw.writeBytes(Float.toString(yaw) + "\r\n");
			raf_yaw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void writeUnPitchFile(float pitch) {

		try {
			File sdCardDir = Environment.getExternalStorageDirectory();

			File pitchFile = new File(sdCardDir.getCanonicalPath() + FILE_UN_PITCH);
			if (!pitchFile.exists()) {
				pitchFile.createNewFile();
			}

			RandomAccessFile raf_pitch = new RandomAccessFile(pitchFile, "rw");

			raf_pitch.seek(pitchFile.length());
			raf_pitch.writeBytes(Float.toString(pitch) + "\r\n");
			raf_pitch.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void writeUnYawFile(float yaw) {

		try {
			File sdCardDir = Environment.getExternalStorageDirectory();

			File yawFile = new File(sdCardDir.getCanonicalPath() + FILE_UN_YAW);
			if (!yawFile.exists()) {
				yawFile.createNewFile();
			}

			RandomAccessFile raf_yaw = new RandomAccessFile(yawFile, "rw");

			raf_yaw.seek(yawFile.length());
			raf_yaw.writeBytes(Float.toString(yaw) + "\r\n");
			raf_yaw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
