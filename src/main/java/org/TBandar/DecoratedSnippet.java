package org.TBandar;

import org.TBandar.Decorator.HiLighter;
import org.TBandar.Finder.SnippetFinder;

public class DecoratedSnippet {

    private String query;
    private HiLighter hiLighter;
    private SnippetFinder snippetFinder;

    public DecoratedSnippet() {

    }

    public DecoratedSnippet forSnippet(SnippetFinder snippetFinder) {
        this.snippetFinder = snippetFinder;
        return this;
    }

    public DecoratedSnippet forQuery(String query) {
        this.query = query;
        return this;
    }

    public DecoratedSnippet decorateWith(HiLighter hiLighter) {
        this.hiLighter = hiLighter;
        return this;
    }

    public String getDecoratedSnippet() {
            String snippet = snippetFinder.find(query);
        return hiLighter.decorate(snippet, query);
    }
}
