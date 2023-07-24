package vk.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;

public class ImageDownloader {

    public static int captcha = 1000; // Количество капчи

    public static void main(String[] args) throws IOException {

        for (int b = 0; b < captcha; b++) {
            Random random = new Random();
            String randomNumber = "";

            for (int i = 0; i < 12; i++) {
                int digit = random.nextInt(10);
                randomNumber += digit;
            }

            String imageUrl = "https://vk.com/captcha.php?sid=" + randomNumber;

            String destinationFile = "captcha/" + randomNumber + ".png";

            saveImage(imageUrl, destinationFile);
        }
    }

    private static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) { os.write(b, 0, length); }

        is.close();
        os.close();
    }
}