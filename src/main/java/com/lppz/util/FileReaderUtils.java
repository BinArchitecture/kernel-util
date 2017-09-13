package com.lppz.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileReaderUtils {
	public static File buildXmlfile(String xml,File file) throws IOException{
		ByteArrayInputStream ins = new ByteArrayInputStream(xml.getBytes());
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
	      os.write(buffer, 0, bytesRead);
		}	
		os.close();
		ins.close();
		return file;
	}
}
