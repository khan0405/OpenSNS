package com.khan.opensns.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface FileSaveService {

	public void writeFile(File newFile, byte[] data) throws IOException;
	
	public void writeFile(String filename, byte[] data) throws IOException;
	
	public void writeFile(String path, String filename, byte[] data) throws IOException;
	
	public void writeFile(File newFile, File file) throws IOException;
	
	public void writeFile(String filename, File file) throws IOException;
	
	public void writeFile(String path, String filename, File file) throws IOException;
	
	public void writeFile(File newFile, InputStream is) throws IOException;
	
	public void writeFile(String filename, InputStream is) throws IOException;
	
	public void writeFile(String path, String filename, InputStream is) throws IOException;
	
	public void writeFile(String filename, String orgUrl) throws MalformedURLException, IOException;
	
	public void writeFile(String path, String filename, String orgUrl) throws MalformedURLException, IOException;
}
