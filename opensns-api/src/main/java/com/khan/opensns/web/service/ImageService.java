package com.khan.opensns.web.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.khan.opensns.web.vo.ImageVo;

public interface ImageService {
	public ImageVo imageSave(String path, String thumb_path, String filename, MultipartFile image) throws IOException;
	public boolean convertToPng(String source, String destination, int quality);
	public boolean convertToPng(String source, String destination, String width, String height, int quality);
}
