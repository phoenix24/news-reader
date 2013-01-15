package org.TBandar.Finder;

public abstract class SnippetFinder {

    public static final int DEFAULT_SNIPPET_LENGTH = 50;
    protected String document;

    public SnippetFinder(String document) {
        this.document = document.toLowerCase();
    }

    public String find(String query) {
        return find(query, DEFAULT_SNIPPET_LENGTH);
    }

    public abstract String find(String query, int length);
}
