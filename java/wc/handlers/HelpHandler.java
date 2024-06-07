package handlers;

import options.Option;

public class HelpHandler implements Handler {
    private Option option;

    public HelpHandler() {
        option = Option.newBuilder("-h")
                .description("Help")
                .longName("--help")
                .required(false)
                .build();
    }

    @Override
    public Option getOption() {
        return option;
    }

    @Override
    public String info(String file) {
        return option.toString();
    }
}
