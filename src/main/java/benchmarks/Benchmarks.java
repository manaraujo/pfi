package benchmarks;

import algoritms.rabbit.Rabbit;
import algoritms.salsa20.Salsa20;
import java.io.IOException;

import org.openjdk.jmh.annotations.*;
import utils.Utils;

public class Benchmarks {

  private static final String inputImagePath = "src/test/resources/imageInput";
  private static final String extension = "bmp";

  @Benchmark
  public void rabbitImage() throws IOException {
    Rabbit rabbitCipher = new Rabbit();
    int[] message = Utils.getImageArray(inputImagePath, extension);
    String key = "secret key 12345";
    String iv = "iv 12345";
    rabbitCipher.execute(Utils.intToByte(message), key.getBytes(), iv.getBytes());
  }

  @Benchmark
  public void rabbitText() {
    Rabbit rabbitCipher = new Rabbit();
    String message = "La temperatura actual de la cámara frigorífica es de -2°C";
    String key = "secret key 12345";
    String iv = "iv 12345";
    rabbitCipher.execute(message.getBytes(), key.getBytes(), iv.getBytes());
  }

  @Benchmark
  public void salsa20Image() throws IOException {
    Salsa20 salsa20 = new Salsa20();
    int[] message = Utils.getImageArray(inputImagePath, extension);
    int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
    salsa20.execute(k, n, message, 0);
  }

  @Benchmark
  public void salsa20Text() {
    Salsa20 salsa20 = new Salsa20();
    int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
    String message = "La temperatura actual de la cámara frigorífica es de -2°C";
    salsa20.execute(k, n, Utils.stringToInt(message), 0);
  }
}
