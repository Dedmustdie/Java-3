package Core.Habr;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ImgDownloader {

    private static String savePath;

    public ImgDownloader(String savePath) {
        this.savePath = savePath;
    }

    public void download(ArrayList<String> srcList) throws IOException {

        for (String url: srcList) {

            String imageExtension = getImageExtension(url);
            String imgName = getImageName(url);

            BufferedImage input = ImageIO.read(new URL(url));
            File output = new File(savePath + imgName + "." + imageExtension);

            ImageIO.write(input, imageExtension, output);
            System.out.println(output + "\n");
        }
    }

    static String getImageExtension(String imageUrl) {
        String afterPoint = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
        return afterPoint.contains("&") ? afterPoint.substring(0, afterPoint.indexOf("&")) : afterPoint;
    }

    static String getImageName(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/"), imageUrl.lastIndexOf("."));
    }
}
