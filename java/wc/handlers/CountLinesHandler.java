package handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import options.Option;

public class CountLinesHandler implements Handler {

    private String info;
    private Option option;

    public CountLinesHandler() {
        info = "";
        option = Option.newBuilder("-l")
                .description("Read num of lines")
                .longName("--lines")
                .required(false)
                .build();

    }

    private void execute(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(file).toFile()))) {
            int lines = 0;
            while (br.readLine() != null) {
                lines += 1;
            }
            info = lines + "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Option getOption() {
        return option;
    }

    @Override
    public String info(String file) {
        execute(file);
        return info;
    }

}
