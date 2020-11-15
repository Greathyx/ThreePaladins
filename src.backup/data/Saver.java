package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;


/**
 * 存档
 * @author qzh
 *
 */

public class Saver {
	/**
	 * 存档文件
	 */
	static File saveFile = new File("Save.txt");
	
	/**
	 * 文件读取�?
	 */
	BufferedReader fr;
	
	/**
	 * writer
	 */
	BufferedWriter fw;
	
	/**
	 * 游戏数据
	 */
	Data data;
	
	/**
	 * 构�?�函�?
	 * @throws IOException 
	 */
	public Saver(Data data) throws IOException{
		this.data = data;
		fr = new BufferedReader(new FileReader(saveFile));

		
	}
	
	/**
	 * 读取存档
	 * @throws IOException 
	 */
	public void load() throws IOException{
		//临时字符�?
		String tem;
		String[] splited;
		while((tem=fr.readLine())!=null){
			splited = tem.split("=");
			if(splited[0].equals("Gold")){
				data.gold = DatatypeConverter.parseInt(splited[1]);
			}
			else if(splited[0].equals("Level")){
				data.level = DatatypeConverter.parseInt(splited[1]);
			}
			else if(splited[0].equals("Fencer")){
				data.levelOfFencer = DatatypeConverter.parseInt(splited[1]);
			}
			else if(splited[0].equals("Archer")){
				data.levelOfArcher = DatatypeConverter.parseInt(splited[1]);
			}
			else if(splited[0].equals("Priest")){
				data.levelOfPriest = DatatypeConverter.parseInt(splited[1]);
			}
		}
		fr.close();
	}
	
	/**
	 * 保存
	 * @throws IOException 
	 */
	public void save() throws IOException{
		fw = new BufferedWriter(new FileWriter(saveFile));
		fw.write("Gold="+data.gold);
		fw.newLine();
		fw.write("Level="+data.level);
		fw.newLine();
		fw.write("Fencer="+data.levelOfFencer);
		fw.newLine();
		fw.write("Archer="+data.levelOfArcher);
		fw.newLine();
		fw.write("Priest="+data.levelOfPriest);
		fw.newLine();
		fw.close();
	}
}
