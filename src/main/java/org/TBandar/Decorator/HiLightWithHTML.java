package org.TBandar.Decorator;

public class HiLightWithHTML extends HiLighter {

    final static String OPENING_TOKEN = "<b>";
    final static String CLOSING_TOKEN = "</b>";
    final static String DUPLICATE_TAGS = "</b> <b>";

    @Override
    public String decorate(final String snippet, final String query) {
        return decorator(snippet, query, OPENING_TOKEN, CLOSING_TOKEN);
    }

    @Override
    protected String getDuplicateTags() {
        return DUPLICATE_TAGS;
    }
}
