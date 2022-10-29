package demostrations.imageEncryption;

import java.io.IOException;
import algoritms.rabbit.Rabbit;
import demostrations.imageEncryption.client.ImageClient;
import utils.Utils;

public class ImageEncryptionWithRabbit {

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
        int[] message = Utils.getImageArray(inputImagePath, extension);

        byte[] key = "secret key 12345".getBytes();
        byte[] iv = "iv 12345".getBytes();

        Rabbit rabbit = new Rabbit();

        int[] crypt = Utils.byteToInt(rabbit.execute(Utils.intToByte(message), key, iv));

        for(int i = 0; i < HEADER_LENGTH; i++)
            crypt[i] = message[i];

        byte[] byteImage = Utils.intToByte(message);
        Utils.arrayToImage(byteImage, outputImagePath, extension);
        byteImage = Utils.intToByte(crypt);
        Utils.arrayToImage(byteImage, outputEncryptedImageImagePath, extension);

        client.updateImages(outputImagePath + "." + extension, outputEncryptedImageImagePath + "." + extension);
    }
}
