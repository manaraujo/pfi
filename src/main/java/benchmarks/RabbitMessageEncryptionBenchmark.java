package benchmarks;

import algoritms.rabbit.Rabbit;
import org.openjdk.jmh.annotations.*;

public class RabbitMessageEncryptionBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void execute() {
        Rabbit rabbitCipher = new Rabbit();
        String message = "La temperatura actual de la cámara frigorífica es de -2°C";
        String key = "secret key 12345";	  //16 bytes
        String iv = "iv 12345";				      //8 bytes
        rabbitCipher.execute(message.getBytes(), key.getBytes(), iv.getBytes());
    }
}