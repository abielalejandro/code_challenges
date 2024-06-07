package handlers;

import options.Option;

public interface Handler {
    public Option getOption();

    public String info(String file);
}
