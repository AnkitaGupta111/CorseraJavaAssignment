package solving_problem_with_software;

import javax.imageio.ImageIO;
import edu.duke.ResourceException;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class BatchInversions {


   BufferedImage makeInversion(BufferedImage inImage)
   {

       int width=inImage.getWidth();
       int height=inImage.getHeight();
       BufferedImage outImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       for(int i=0;i<width;i++)
       {
           for (int j=0;j<height;j++)
           {
               Color inImagePixelColor=new Color(inImage.getRGB(i,j));
               int red=inImagePixelColor.getRed();
               int blue=inImagePixelColor.getBlue();
               int green=inImagePixelColor.getGreen();

               Color invertedImgePixelColor=new Color(255-red,255-green,255-blue);
               outImage.setRGB(i,j,invertedImgePixelColor.getRGB());
           }
       }
       return outImage;

   }

   void selectAndConvert()
   {
      File folder=new File("src/solving_problem_with_software/makeGrayBatch/images");
      if(folder==null)
          throw new ResourceException("invalid source folder path");

      for (File file:folder.listFiles())
      {
          BufferedImage image;

          if(file.isFile()) {
              try {
          image =ImageIO.read(file);

          } catch (IOException e)
              {
                 throw  new ResourceException("not a valid image");
              }

              BufferedImage inversionImage=makeInversion(image);

              try {
                  File outputFile=new File("src/solving_problem_with_software/invertedImage/inverted-"+file.getName());
                  ImageIO.write(inversionImage,"jpg",outputFile);
              }
              catch (IOException e)
              {
                  throw new ResourceException(e.getMessage());
              }
              }

      }


   }
    public static void main(String[] args) {
        BatchInversions inverter=new BatchInversions();
        inverter.selectAndConvert();
    }

}
