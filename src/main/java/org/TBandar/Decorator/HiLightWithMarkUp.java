package org.TBandar.Decorator;

public class HiLightWithMarkUp extends HiLighter {

    final static String OPENING_TOKEN = "[[HIGHLIGHT]]";
    final static String CLOSING_TOKEN = "[[ENDHIGHLIGHT]]";
    final static String DUPLICATE_TAGS = "\\[\\[ENDHIGHLIGHT\\]\\] \\[\\[HIGHLIGHT\\]\\]";

    @Override
    public String decorate(final String snippet, final String query) {
        return decorator(snippet, query, OPENING_TOKEN, CLOSING_TOKEN);
    }

    @Override
    protected String getDuplicateTags() {
        return DUPLICATE_TAGS;
    }
}
