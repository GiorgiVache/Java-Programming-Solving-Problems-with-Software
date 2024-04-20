import edu.duke.*;
import java.io.*;

public class GrayscaleConverter {
    public ImageResource convert_to_grayscale (ImageResource originalimage){
        int width = originalimage.getWidth();
        int height = originalimage.getHeight();
        ImageResource grayimage = new ImageResource(width, height);
        for (Pixel p : originalimage.pixels()){
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            int RGB = (red + green + blue)/3;
            grayimage.getPixel(p.getX(), p.getY()).setRed(RGB);
            grayimage.getPixel(p.getX(), p.getY()).setGreen(RGB);
            grayimage.getPixel(p.getX(), p.getY()).setBlue(RGB);
        }
        return grayimage;
    }
    
    public void testgrayscale(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String imagename = image.getFileName();
            String grayname = "gray-" + imagename;
            ImageResource grayimage = convert_to_grayscale (image);
            grayimage.setFileName(grayname);
            grayimage.save();
        }
    }
}
