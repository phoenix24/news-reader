package org.TBandar;

import org.TBandar.Decorator.HiLighter;
import org.TBandar.Finder.SnippetFinder;

/**
 * Simple Helper class that aids in fetching a decorated
 * relevant snippet.
 */
public class DecoratedSnippet {

    private HiLighter hiLighter;
    private SnippetFinder snippetFinder;

    /**
     * Snippet finder algorithm to be used.
     * @param snippetFinder
     * @return
     */
    public DecoratedSnippet forSnippet(SnippetFinder snippetFinder) {
        this.snippetFinder = snippetFinder;
        return this;
    }

    /**
     * Hilighter to be used.
     * @param hiLighter
     * @return
     */
    public DecoratedSnippet decorateWith(HiLighter hiLighter) {
        this.hiLighter = hiLighter;
        return this;
    }

    /**
     * Return the decorated snippet of default length.
     * @param query terms for which the snippet is to be found, decorated.
     * @return
     */
    public String getDecoratedSnippet(String query) {
        String snippet = snippetFinder.find(query);
        return hiLighter.decorate(snippet, query);
    }

    /**
     * Return the decorated snippet of given length.
     * @param query terms for which the snippet is to be found, decorated.
     * @param length length of the snippet required.
     * @return
     */
    public String getDecoratedSnippet(String query, int length) {
        String snippet = snippetFinder.find(query, length);
        return hiLighter.decorate(snippet, query);
    }
}
