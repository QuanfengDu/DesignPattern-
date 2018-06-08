/*
 * Author: Quanfeng Du 
 * Data: June 6, 2018
 * Objective: demo the Factory Method pattern
 * factory method pattern is a creational pattern that uses 
 * factory methods to deal with the problem of creating objects
 * without having to specify the exact class of the object that 
 * will be created.
 */
package DesignPattern;

interface ImageReader{
	DecodedImage getDecodeImage();
}

class DecodedImage {
	private String image;
	public DecodedImage(String image) {
		this.image=image;
	}
	
	@Override
	public String toString() {
		return image+": is decoded";
	}
}
class GitReader implements ImageReader{
	private DecodedImage decodedImage;
	public GitReader(String image) {
		this.decodedImage=new DecodedImage(image);
	}
	
	@Override 
	public DecodedImage getDecodeImage() {
		return decodedImage;
	}
}

class JpegReader implements ImageReader{
	private DecodedImage decodedImage;
	public JpegReader(String image) {
		this.decodedImage=new DecodedImage(image);
	}
	@Override 
	public DecodedImage getDecodeImage() {
		return decodedImage;
	}
}


public class FactoryMethod {
	public static void main(String[] args) {
		DecodedImage decodedImage;
		ImageReader reader=null;
		String image=args[0];
		String format=image.substring(image.indexOf('.')+1, (image.length()));
		if(format.equals("gif"))
			reader=new GitReader(image);
		if(format.equals("jpeg"))
			reader=new JpegReader(image);
		decodedImage=reader.getDecodeImage();
		
	}
}
