package handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import options.Option;

public class CountWordsHandler implements Handler {

    private String info;
    private Option option;

    public CountWordsHandler() {
        info = "";
        option = Option.newBuilder("-w")
                .description("Read num of words")
                .longName("--words")
                .required(false)
                .build();
    }

    private void execute(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(Path.of(file).toFile()))) {
            int counter = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");
                counter += words.length;
            }
            info = counter + "";
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
