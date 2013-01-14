package org.TBandar.Decorator;

import com.google.common.base.Splitter;
import static com.google.common.base.Preconditions.checkNotNull;

public abstract class HiLighter {

    public static final String DEFAULT_OPENING_TAG = "";
    public static final String DEFAULT_CLOSING_TAG = "";

    /**
     * return me decorated snippet.
     */
    public String decorate(String snippet, String query) {
        return decorator(snippet, query, DEFAULT_OPENING_TAG, DEFAULT_CLOSING_TAG);
    }

    /**
     * TODO: Not sure, if i must deal with all these cases I've assumed.
     * @param snippet text snippet to be hilighted.
     * @param query query words which are to be hilighted.
     * @param oTag opening hilight tag.
     * @param cTag closing hilight tag.
     * @return
     */
    protected String decorator(final String snippet, final String query,
                               final String oTag, final String cTag) throws NullPointerException {
        checkNotNull(query);
        checkNotNull(snippet);

        String lquery = query.toLowerCase();
        String lsnippet = snippet.toLowerCase();
        String hilighted = new String(snippet);
        String lhilighted = new String(lsnippet);

        Iterable<String> tokens = Splitter.on(' ')
                                            .trimResults()
                                            .omitEmptyStrings()
                                            .split(lquery);

        for (String token : tokens) {
            int index = -1;
            if ((index = lhilighted.indexOf(token)) > -1) {
                hilighted = hilighted.substring(0, index)
                            + oTag
                            + hilighted.substring(index, index + token.length())
                            + cTag
                            + hilighted.substring(index + token.length());
                lhilighted = hilighted.toLowerCase();
            }
        }
        hilighted = hilighted.replaceAll(oTag + ' ' + cTag, " ");
        return hilighted;
    }
}
