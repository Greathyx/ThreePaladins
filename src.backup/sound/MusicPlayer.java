package sound;

import java.io.*;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	public boolean stop = false;

	public void play(String file, boolean loop)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		do {
			if (stop)
				break;
			// 获取音频输入�?
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
			// 获取音频编码对象
			AudioFormat audioFormat = audioInputStream.getFormat();
			// 设置数据输入
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			/*
			 * 从输入流中读取数据发送到混音�?
			 */
			int count;
			byte tempBuffer[] = new byte[1024];
			while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
				if (count > 0) {
					sourceDataLine.write(tempBuffer, 0, count);
				}
				if (stop) {
					sourceDataLine.drain();
					sourceDataLine.close();
				}
			}
			// 清空数据缓冲,并关闭输�?
			sourceDataLine.drain();
			sourceDataLine.close();
		} while (loop);
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
