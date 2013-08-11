package com.khan.opensns.util;

import org.springframework.util.StringUtils;

public class FileUtil {
	
	public static final String getPath(String fullPath) {
		String path = StringUtils.cleanPath(fullPath);
		if (path.lastIndexOf("/") != -1)
			path = path.substring(0, path.lastIndexOf("/") - 1);
		return path;
	}
	
	public static final String getFilename(String fullPath) {
		String filename = StringUtils.cleanPath(fullPath);
		if (filename.lastIndexOf("/") != -1)
			filename = filename.substring(filename.lastIndexOf("/"));
		return filename;
	}
}
