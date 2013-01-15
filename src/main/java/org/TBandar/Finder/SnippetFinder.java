package org.TBandar.Finder;

public abstract class SnippetFinder {

    public static final int DEFAULT_SNIPPET_LENGTH = 100;

    protected final String document;
    protected final String oDocument;

    public SnippetFinder(String document) {
        this.document = document.toLowerCase();
        this.oDocument = document;
    }

    public String find(String query) {
        return find(query, DEFAULT_SNIPPET_LENGTH);
    }

    public abstract String find(String query, int length);
}
