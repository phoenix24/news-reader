package org.TBandar.Decorator;

public class HiLightWithMarkUp extends HiLighter {

    final static String OPENING_TOKEN = "[[HIGHLIGHT]]";
    final static String CLOSING_TOKEN = "[[ENDHIGHLIGHT]]";

    @Override
    public String decorate(final String snippet, final String query) {
        return decorator(snippet, query, OPENING_TOKEN, CLOSING_TOKEN);
    }
}
