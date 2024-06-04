package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import options.OptionsMapper;

public class CountLinesHandler implements Handler{

    private String info;
    private List<String> options = Arrays.asList("-l","--lines");
    private OptionsMapper mapper;
    private Path path;

    public CountLinesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
    }

    private void execute() {
        Optional<String> has = options
            .stream()
            .filter(op-> {
                return this.mapper.hasOption(op);}
            )
            .findAny();

        if (!has.isPresent()) return;

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
