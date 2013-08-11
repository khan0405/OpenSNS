package com.khan.opensns.vo;

public class ImageVo {
	public String imageSrc;
	public String thumbSrc;
	public ImageVo() {}
	public ImageVo(String imageSrc, String thumbSrc) {
		setImageSrc(imageSrc);
		setThumbSrc(thumbSrc);
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getThumbSrc() {
		return thumbSrc;
	}
	public void setThumbSrc(String thumbSrc) {
		this.thumbSrc = thumbSrc;
	}
	
}
