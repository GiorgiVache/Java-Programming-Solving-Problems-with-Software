import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource originalimage){
        int width = originalimage.getWidth();
        int height = originalimage.getHeight();
        ImageResource invertedimage = new ImageResource(width, height);
        for (Pixel p : originalimage.pixels()){
            int red = p.getRed();
            int green = p.getGreen();
            int blue = p.getBlue();
            int newred = 255 - red;
            int newgreen = 255 - green;
            int newblue = 255 - blue;
            invertedimage.getPixel(p.getX(), p.getY()).setRed(newred);
            invertedimage.getPixel(p.getX(), p.getY()).setGreen(newgreen);
            invertedimage.getPixel(p.getX(), p.getY()).setBlue(newblue);
        }
        return invertedimage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String imagename = image.getFileName();
            String invertedname = "inverted-" + imagename;
            ImageResource invertedimage = makeInversion (image);
            invertedimage.setFileName(invertedname);
            invertedimage.save();
        }
    }
}
