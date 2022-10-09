package demostrations.imageEncryption;

import demostrations.imageEncryption.client.ImageClient;
import algoritms.salsa20.Salsa20;
import utils.Utils;

import java.io.IOException;

public class ImageEncryptionWithSalsa20 {

    private static final ImageClient client = new ImageClient("Cipher Demonstration", 0, 0);

    private static final String inputImagePath = "src/test/resources/imageInput";
    private static final String outputImagePath = "src/test/resources/image";
    private static final String outputEncryptedImageImagePath = "src/test/resources/encryptedImage";
    private static final String extension = "bmp";
    private static final int HEADER_LENGTH = 54;


    public static void main(String[] args) throws IOException {
        client.setVisible(true);
        cryptImage();
    }

    private static void cryptImage() throws IOException {
        Salsa20 salsa20 = new Salsa20();
        int[] message = Utils.getImageArray(inputImagePath, extension);
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};

        int[] crypt = salsa20.execute(k, n, message, 0);
        int[] decrypt = salsa20.execute(k, n, crypt, 0);

        for(int i = 0; i < HEADER_LENGTH; i++)
            crypt[i] = message[i];

        byte[] byteImage = Utils.intToByte(decrypt);
        Utils.arrayToImage(byteImage, outputImagePath, extension);
        byteImage = Utils.intToByte(crypt);
        Utils.arrayToImage(byteImage, outputEncryptedImageImagePath, extension);

        client.updateImages(outputImagePath + "." + extension, outputEncryptedImageImagePath + "." + extension);
    }
}
