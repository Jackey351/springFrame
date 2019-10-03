package com.yanfei.springFrame.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Global {
	
//	public static String accessToken;
	public static final String appid = "***";
	public static final String secret = "***";
	
	public static Date getCurrentTime() {
		// 消除数据库毫秒进位导致的差异
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(simpleDateFormat.format(new Date((new Date().getTime() / 1000) * 1000)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(simpleDateFormat.format(new Date((new Date().getTime() / 1000) * 1000)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	public static boolean isMobilePhoneNumValied(String mobilePhoneNum) {
		if (!RequestUtil.checkText(mobilePhoneNum, "1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}")) {
			return false;
		}
		return true;
	}
	
	public static boolean isPhoneNumValied(String phoneNum) {
		if (!RequestUtil.checkText(phoneNum, "1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}")
				&& !RequestUtil.checkText(phoneNum, "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$")) {
			return false;
		}
		return true;
	}
	
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	
	public static boolean writeFile(String filePath, String content) {
		File writename = new File(filePath);
		try {
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(content);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} // 创建新文件
		return true;
	}
	
	public static boolean copyFile(String srcPath, String destDir) {
		boolean flag = false;
		
		File srcFile = new File(srcPath);
		if (!srcFile.exists() || !srcFile.isFile()) { // 源文件不存在
			return false;
		}
		
		// 获取待复制文件的文件名
		String fileName = srcFile.getName();
		String destPath = destDir + File.separator + fileName;
		File destFile = new File(destPath);
		
		File destFileDir = new File(destDir);
		if (!destFileDir.exists() && !destFileDir.mkdirs()) { // 目录不存在并且创建目录失败直接返回
			return false;
		}
		
		try {
			FileInputStream fis = new FileInputStream(srcPath);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int c;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fos.flush();
			fis.close();
			fos.close();
			
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static boolean deleteFile(String path) {
		boolean flag = false;
		
		File file = new File(path);
		if (!file.exists()) { // 文件不存在直接返回
			return flag;
		}
		
		flag = file.delete();
		
		return flag;
	}
	
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	
	public static List<File> getFileList(String strPath) {
		List<File> filelist = new ArrayList<File>();
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) { // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				} else {
					filelist.add(files[i]);
				}
			}
		}
		return filelist;
		
	}
	
	public static boolean cutGeneralFile(String srcPath, String destDir) {
		if (copyFile(srcPath, destDir) && deleteFile(srcPath)) { // 复制和删除都成功
			return true;
		}
		return false;
	}
	
	public static String readFile(File file) {
		Long filelength = file.length(); // 获取文件长度
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(filecontent);// 返回文件内容,默认编码
	}
	
	public static Date dateAddYear(Date date, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(sdf.parse(sdf.format(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rightNow.add(Calendar.YEAR, num);// 日期加1年
		Date newDate = rightNow.getTime();
		return newDate;
	}
	
	public static enum DataType {
		NUMBER("数值型", 0), SWITCH("开关型", 1);
		
		private final String typeName;
		private final int value;
		
		private DataType(String typeName, int value) {
			this.typeName = typeName;
			this.value = value;
		}
		
		public static String getTypeName(int value) {
			for (DataType t : DataType.values()) {
				if (t.value == value) {
					return t.typeName;
				}
			}
			return null;
		}
		
		public static boolean contains(int value) {
			for (DataType t : DataType.values()) {
				if (t.value == value) {
					return true;
				}
			}
			return false;
		}
	}
	
}
