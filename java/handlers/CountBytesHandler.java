package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import options.OptionsMapper;

public class CountBytesHandler extends Handler{

    private String info;
    private OptionsMapper mapper;
    private Path path;

    public CountBytesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
        options =Arrays.asList("-c","--bytes");
    }

    private void execute() {

        if (!hasArg(mapper)) return;

        try {
            byte[] bytes = Files.readAllBytes(path);
            info = bytes.length+"";
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
