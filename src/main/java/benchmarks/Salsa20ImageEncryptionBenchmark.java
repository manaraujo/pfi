package benchmarks;

import algoritms.salsa20.Salsa20;
import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import utils.Utils;

public class Salsa20ImageEncryptionBenchmark {

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
        Salsa20 salsa20 = new Salsa20();
        int[] message = Utils.getImageArray(inputImagePath, extension);
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        salsa20.execute(k, n, message, 0);
    }
}