package benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);

        Options opt = new OptionsBuilder()
            .include(Benchmarks.class.getSimpleName())
            .mode(Mode.AverageTime)
            .resultFormat(ResultFormatType.CSV)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .shouldDoGC(true)
            .build();

        new Runner(opt).run();
    }
}