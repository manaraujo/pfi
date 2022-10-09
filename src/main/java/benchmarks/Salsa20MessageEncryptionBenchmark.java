package benchmarks;

import algoritms.salsa20.Salsa20;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import utils.Utils;

public class Salsa20MessageEncryptionBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void execute() {
        Salsa20 salsa20 = new Salsa20();
        int[] k = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] n = {101, 102, 103, 104, 105, 106, 107, 108};
        String message = "La temperatura actual de la cámara frigorífica es de -2°C";
        salsa20.execute(k, n, Utils.stringToInt(message), 0);
    }
}