package org.TBandar;

import org.TBandar.Decorator.HiLighter;
import org.TBandar.Finder.SnippetFinder;

public class DecoratedSnippet {

    private HiLighter hiLighter;
    private SnippetFinder snippetFinder;

    public DecoratedSnippet forSnippet(SnippetFinder snippetFinder) {
        this.snippetFinder = snippetFinder;
        return this;
    }

    public DecoratedSnippet decorateWith(HiLighter hiLighter) {
        this.hiLighter = hiLighter;
        return this;
    }

    public String getDecoratedSnippet(String query) {
        String snippet = snippetFinder.find(query);
        return hiLighter.decorate(snippet, query);
    }

    public String getDecoratedSnippet(String query, int length) {
        String snippet = snippetFinder.find(query, length);
        return hiLighter.decorate(snippet, query);
    }
}
