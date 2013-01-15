package org.TBandar.Decorator;

public class HiLightWithQuotes extends HiLighter {

    final static String OPENING_TOKEN = "'";
    final static String CLOSING_TOKEN = "'";
    final static String DUPLICATE_TAGS = "' '";

    @Override
    public String decorate(final String snippet, final String query) {
        return decorator(snippet, query, OPENING_TOKEN, CLOSING_TOKEN);
    }

    @Override
    protected String getDuplicateTags() {
        return DUPLICATE_TAGS;
    }
}
