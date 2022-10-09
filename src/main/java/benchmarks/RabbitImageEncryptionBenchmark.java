package benchmarks;

import algoritms.rabbit.Rabbit;
import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import utils.Utils;

public class RabbitImageEncryptionBenchmark {

    private static final String inputImagePath = "src/test/resources/imageInput";
    private static final String extension = "bmp";

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void execute() throws IOException {
        Rabbit rabbitCipher = new Rabbit();
        int[] message = Utils.getImageArray(inputImagePath, extension);
        byte[] key = "secret key 12345".getBytes();
        byte[] iv = "iv 12345".getBytes();
        rabbitCipher.execute(Utils.intToByte(message), key, iv);
    }
}