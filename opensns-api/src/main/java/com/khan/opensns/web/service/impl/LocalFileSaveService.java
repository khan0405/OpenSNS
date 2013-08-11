package com.khan.opensns.web.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khan.opensns.service.FileSaveService;
import com.khan.opensns.util.FileUtil;

public class LocalFileSaveService implements FileSaveService {
	
	private String baseDir = "";
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(LocalFileSaveService.class);

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}
	
	public String getBaseDir() {
		return baseDir;
	}
	
	@Override
	public void writeFile(File newFile, byte[] data) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(newFile);
			fos.write(data);
			fos.flush();
		} finally {
			if (fos != null)
				fos.close();
		}
	}

	@Override
	public void writeFile(String filename, byte[] data) throws IOException {
		String path = FileUtil.getPath(filename);
		createBaseDir(path);
		
		writeFile(new File(filename), data);
	}

	@Override
	public void writeFile(String path, String filename, byte[] data) throws IOException {
		File pFile = new File(path);
		
		if (!pFile.exists())
			pFile.mkdirs();
		
		writeFile(new File(path, filename), data);
	}

	@Override
	public void writeFile(File newFile, File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		writeFile(newFile, is);
	}

	@Override
	public void writeFile(String filename, File file) throws IOException {
		String path = FileUtil.getPath(filename);
		createBaseDir(path);
		writeFile(new File(filename), file);
	}

	@Override
	public void writeFile(String path, String filename, File file) throws IOException {
		createBaseDir(path);

		writeFile(new File(path, filename), file);
	}

	@Override
	public void writeFile(File newFile, InputStream is) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(newFile);
			
			byte[] b = new byte[1000];
			
			while (is.read(b) != -1) {
				fos.write(b);
			}
			
			fos.flush();
		} finally {
			is.close();
			if (fos != null)
				fos.close();
		}
	}

	@Override
	public void writeFile(String filename, InputStream is) throws IOException {
		String path = FileUtil.getPath(filename);
		createBaseDir(path);
		writeFile(new File(filename), is);
	}

	@Override
	public void writeFile(String path, String filename, InputStream is) throws IOException {
		createBaseDir(path);
		writeFile(new File(path, filename), is);
	}

	@Override
	public void writeFile(String filename, String orgUrl) throws MalformedURLException, IOException {
		String path = FileUtil.getPath(filename);
		createBaseDir(path);
		
		URL url = new URL(orgUrl);

		URLConnection connection = url.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();

		writeFile(new File(filename), is);
	}

	@Override
	public void writeFile(String path, String filename, String orgUrl) throws MalformedURLException, IOException {
		createBaseDir(path);
		URL url = new URL(orgUrl);

		URLConnection connection = url.openConnection();
        connection.connect();
        InputStream is = connection.getInputStream();

		writeFile(new File(path, filename), is);
	}
	
	
	private void createBaseDir(String path) {
		File pFile = new File(path);
		
		if (!pFile.exists())
			pFile.mkdirs();
	}

	
	public static void main(String[] args) {
		File file = new File("./");
		System.out.println(file.getAbsolutePath());
	}
}
