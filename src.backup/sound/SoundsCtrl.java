package sound;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * 声音控制�?
 */
public class SoundsCtrl extends Thread {
	/**
	 * 文件�?
	 */
	private String filename;

	/**
	 * 声音播放构�?�函�?
	 * @param filename
	 * 文件�?
	 */
	public SoundsCtrl(String filename) {
		this.filename = filename;	
	}

	public void run() {
		/**
		 * 音频文件
		 */
		File soundFile = new File(filename);
		
		/**
		 * 音频输入�?
		 */
		AudioInputStream audioInputStream = null;
		
		//获得音频输入�?
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		// 指定声音流中特定数据安排
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			// 从混频器获得源数据行
			auline = (SourceDataLine) AudioSystem.getLine(info);
			// 打开具有指定格式的行
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		// 允许数据行执行数�? I/O 
		auline.start();
		
		int nBytesRead = 0;
		// 这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				// 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中�?
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					// 通过此源数据行将音频数据写入混频器�??
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

}
