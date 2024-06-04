package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import options.OptionsMapper;

public class CountLinesHandler extends Handler{

    private String info;
    private OptionsMapper mapper;
    private Path path;

    public CountLinesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
        options = Arrays.asList("-l","--lines");
    }

    private void execute() {
        if (!hasArg(mapper)) return;
        try {
            long lines = 
                Files.lines(path)
                .count();
            info = lines+"";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String info() {
        execute();
        return info;
    }
    
}
