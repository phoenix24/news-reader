package org.TBandar.Finder;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FindUsingWordDistanceTest {

    private String query;
    private String document;

    @Before
    public void setUp() throws Exception {
        query = "micromax mobile phones cheap";
        document = "With the abudance of cheap mobile phones in the market " +
                   "few companies provide products worth their money. Micromax, offer cheap mobile phones making " +
                   "with great product features. Which are durable and provide competing mobile phone features. " +
                   "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap! " +
                   "and have been increasingly winning mobile market share, and customer loyalty.";
    }

    @Test
    public void testHilightSnippetWithDefaultLength() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "mobile phones making with great product features. " +
                   "Which are durable and provide competing mobile phone features. " +
                   "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap!";

        assertThat("find snippet, using default snippet length",
                    snippet.find(query),
                    is(expected));

        assertThat("length of the snippet is 50.",
                    snippet.find(query).length(),
                    is(50));
    }

    @Test
    public void testHilightSnippetWithLength30() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "mobile phones making with great product features. " +
                "Which are durable and provide competing mobile phone features. " +
                "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap!";

        assertThat("find snippet, length 30.",
                snippet.find(query, 30),
                is(expected));

        assertThat("length of the snippet is 30.",
                snippet.find(query, 30).length(),
                is(30));
    }

    @Test
    public void testHilightSnippetWithLength200() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "mobile phones making with great product features. " +
                "Which are durable and provide competing mobile phone features. " +
                "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap!";

        assertThat("find snippet, length 200.",
                snippet.find(query, 200),
                is(expected));

        assertThat("length of the snippet is 200.",
                snippet.find(query, 200).length(),
                is(200));
    }

    @Test
    public void testHilightSnippet1() {
        SnippetFinder snippet = new FindUsingWordDistace("Little star's deep dish pizza sure is fantastic.");
        String expected = "Little star's deep dish pizza sure is fantastic.";

        assertThat("find snippet, using default snippet length",
                    snippet.find("deep dish pizza"),
                    is(expected));
    }
}
