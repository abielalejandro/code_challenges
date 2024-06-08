package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import options.Option;

public class CountBytesHandler implements Handler {

    private String info;
    private Option option;

    public CountBytesHandler() {
        info = "";
        this.option = Option.newBuilder("-c")
                .description("Count num of bytes")
                .longName("--bytes")
                .required(false)
                .build();
    }

    private void execute(String file) {
        info = "0";

        try {
            byte[] bytes = Files.readAllBytes(Path.of(file));
            info = bytes.length + "";
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
