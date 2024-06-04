package options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionsMapper {
    private List<Option> options;
    private Map<String, String> mapper;

    public OptionsMapper() {
        mapper = new HashMap<String, String>();
        options = new ArrayList<>();

        Option help = Option.newBuilder("-h")
                .description("Help")
                .longName("--help")
                .required(false)
                .build();
        options.add(help);

        Option countBytes = Option.newBuilder("-c")
                .description("Read num of bytes")
                .longName("--bytes")
                .required(false)
                .build();

        options.add(countBytes);

        Option countLines = Option.newBuilder("-l")
                .description("Read num of lines")
                .longName("--lines")
                .required(false)
                .build();

        options.add(countLines);

    }

    public OptionsMapper add(Option option) {
        options.add(option);
        return this;
    }

    public void handleArgs(String[] args) {
        if (args.length <= 0) {
            return;
        }

        for (String arg : args) {
            Optional<Option> o = options
                .stream()
                .filter((opt)->{
                    return opt.getLongName().equals(arg) || opt.getShortName().equals(arg);
                })
                .findFirst();

            if (o.isPresent()) {
                mapper.put(arg, arg);
            }
        }
    }

    public boolean hasOptions() {
        return mapper.size()>0;
    }

    public boolean hasOption(String option) {
        return null!=mapper.get(option);
    }

    public void printHelp() {
        System.out.println("Help for wc command");
        System.out.println("wc <options> <FILE>");
        System.out.println("options:");
        for (Option opt : options) {
            System.out.println(opt);
        }
    }

}
