package org.TBandar.Decorator;

public class HiLightWithQuotes extends HiLighter {

    final static String OPENING_TOKEN = "'";
    final static String CLOSING_TOKEN = "'";

    @Override
    public String decorate(final String snippet, final String query) {
        return decorator(snippet, query, OPENING_TOKEN, CLOSING_TOKEN);
    }
}
