package file;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import handlers.Handler;
import options.Option;
import options.OptionsMapper;

public class FileManager {

    private List<Handler> handlers;
    private OptionsMapper mapper;
    private String file;

    public FileManager(String file, OptionsMapper mapper) {
        handlers = new ArrayList<>();
        this.file = file;
        this.mapper = mapper;
    }

    private boolean hasArg(Option option) {
        return mapper.hasOption(option.getLongName()) || mapper.hasOption(option.getShortName());
    }

    private boolean hasArg(String arg) {
        return mapper.hasOption(arg);
    }

    private boolean canPrint(Handler h) {
        if (h.getOption().getShortName().equals("-h")
                || h.getOption().getShortName().equals("--help"))
            return false;
        if (!mapper.hasOptions())
            return true;
        return hasArg(h.getOption());
    }

    public FileManager addHandler(Handler h) {
        this.handlers.add(h);
        return this;
    }

    public void printHelp() {
        System.out.println("Help for wc command");
        System.out.println("wc <options> <FILE>");
        System.out.println("options:");
        for (Handler h : handlers) {
            System.out.println(h.getOption().toString());
        }
    }

    public void info() {

        if (hasArg("-h") || hasArg("--help")) {
            printHelp();
            return;
        }

        if (file.isEmpty()) {
            throw new IllegalArgumentException("It is not a file");
        }

        if (!Path.of(file).toFile().isFile()) {
            throw new IllegalArgumentException(String.format("%s It is not a file", file));
        }

        StringBuilder sb = new StringBuilder("");
        for (Handler h : handlers) {
            if (!canPrint(h))
                continue;
            sb.append(h.info(file)).append(" ");
        }
        // sb.append(file);
        System.err.println(sb.toString());
    }

}
