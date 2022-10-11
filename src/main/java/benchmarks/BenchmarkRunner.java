package benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);

        Options opt = new OptionsBuilder()
            .include(Benchmarks.class.getSimpleName())
            .resultFormat(ResultFormatType.CSV)

            .mode(Mode.All)
            .timeUnit(TimeUnit.NANOSECONDS)

            .forks(1)

            .warmupIterations(0)
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))

            .addProfiler(GCProfiler.class)
//            .shouldDoGC(true)
            .build();

        new Runner(opt).run();
    }
}