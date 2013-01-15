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
                   "with great product features. Which are durable and provide competing mobile phone features." +
                   "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap!" +
                   "and have been increasingly winning mobile market share, and customer loyalty.";
    }

    @Test
    public void testHilightSnippet() {

        String expected;
        SnippetFinder snippet = new FindUsingWordDistace(document);

        expected = "Micromax, their phones are good value for money, reliable, feature rich and cheap!";
        assertThat("find snippet, using default snippet length",
                    snippet.find(query),
                    is(expected));

        expected = "Micromax, their phones are good value for money, reliable, feature rich and cheap!";
        assertThat("find snippet, for snippet length == 30.",
                    snippet.find(query, 30),
                    is(expected));

        expected = "Micromax, their phones are good value for money, reliable, feature rich and cheap!";
        assertThat("find snippet, for snippet length == 150.",
                    snippet.find(query, 150),
                    is(expected));
    }

    @Test
    public void testHilightSnippet1() {

        String expected;
        SnippetFinder snippet = new FindUsingWordDistace("Little star's deep dish pizza sure is fantastic.");

        expected = "Micromax, their phones are good value for money, reliable, feature rich and cheap!";
        assertThat("find snippet, using default snippet length",
                snippet.find("deep dish pizza"),
                is(expected));
    }
}
