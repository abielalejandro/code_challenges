package handlers;

import java.util.ArrayList;
import java.util.List;
import options.OptionsMapper;

public abstract class Handler {
    
    List<String> options = new ArrayList<>();

    public boolean hasArg(OptionsMapper mapper) {
        return options
        .stream()
        .filter(op-> {
            return mapper.hasOption(op);}
        )
        .findAny()
        .isPresent();

    }

    public abstract String info();
}
