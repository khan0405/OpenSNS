package com.khan.opensns.api.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khan.opensns.service.FileSaveService;
import com.khan.opensns.service.ImageService;
import com.khan.opensns.vo.ImageVo;

@Service(value="imageService")
public class ImageServiceImpl implements ImageService {

	private static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	private String CONVERT_PROG = "convert";
	
	@Autowired
	FileSaveService fileSaveService;

	@Value(value="#{setting['image.base']}")
	private String BASE_DIR;

	@Value(value="#{setting['image.tmp']}")
	private String TMP_DIR;

	@Value(value="#{setting['magick.dir']}")
	private String MAGICK_DIR; 
	
	public ImageVo imageSave(String path, String thumb_path, String filename, MultipartFile image) throws IOException {

		File tmpdir = new File(BASE_DIR + TMP_DIR + path);
		
		if (!tmpdir.exists())
			tmpdir.mkdirs();
		
		File source = new File(tmpdir, "_" + filename);
		File convert = new File(tmpdir, filename);
		File convertThumb = new File(tmpdir, "thumb_" + filename);
		
		image.transferTo(source);
		
		boolean result = convertToPng(source.getAbsolutePath(), convert.getAbsolutePath(), 100);
		if (!result)
			throw new IOException("File convert error");
		
		result = convertToPng(convert.getAbsolutePath(), convertThumb.getAbsolutePath(), "100", "100", 100);
		if (!result)
			throw new IOException("File convert error");
		
		fileSaveService.writeFile(new File(BASE_DIR + path, filename), convert); 
		fileSaveService.writeFile(new File(BASE_DIR + thumb_path, filename), convertThumb); 
		
		ImageVo vo = new ImageVo(path + "/" + filename, thumb_path + "/" + filename);
		
		return vo;
	}
	
	@Override
	public boolean convertToPng(String source, String destination, int quality) {
		return convertToPng(source, destination, "100%", "100%", quality);
	}
	
	@Override
	public boolean convertToPng(String source, String destination, String width, String height, int quality) {
		List<String> command = null;
		try {
			if (quality < 0 || quality > 100)
				quality = 75;

			command = new ArrayList<String>(10);
			command.add(MAGICK_DIR + "/" + CONVERT_PROG);
			command.add("-geometry");
			command.add(width + "x" + height);
			command.add("-quality");
			command.add(Integer.toString(quality));
			command.add(source);
			command.add(destination);
			
			log.info(command.toString());
		} catch (Exception e) {
			log.error("Image convert failed....", e);
		}
		
		return exec((String[]) command.toArray(new String[1]));
	}

	private boolean exec(String[] command) {
		Process proc = null;
		int exitStatus = -1;
		try {
			proc = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			log.error("Image convert failed....", e);
			return false;
		}
		while (true) {
			try {
				exitStatus = proc.waitFor();
				break;
			} catch (java.lang.InterruptedException e) {
				log.debug("Interrupted: Ignoring and waiting");
			}
		}
		if (exitStatus != 0) {
			log.debug("Error executing command: " + exitStatus);
		}
		return (exitStatus == 0);
	}
}