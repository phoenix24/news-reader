package org.TBandar.Finder;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
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
                "and have been increasingly winning mobile market share and customer loyalty.";
    }

    @Test
    public void testHilightSnippetWithDefaultLength() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "ap mobile phones in the market few companies provide products worth their money. " +
                "Micromax, offer cheap";

        assertThat("find snippet, using default snippet length",
                snippet.find(query),
                is(expected));

        assertThat("length of the snippet is 100.",
                Math.abs(100 - snippet.find(query).length()),
                lessThanOrEqualTo(10));
    }

    @Test
    public void testHilightSnippetWithLength30() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "Micromax, offer cheap mobile phones";

        assertThat("find snippet, length 30.",
                snippet.find(query, 30),
                is(expected));

        assertThat("length of the snippet is 30.",
                Math.abs(30 - snippet.find(query, 30).length()),
                lessThanOrEqualTo(10));
    }

    @Test
    public void testHilightSnippetWithLength150() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "icromax, offer cheap mobile phones making with great product features. " +
                "Which are durable and provide competing mobile phone features. Micromax makes,";

        assertThat("find snippet, length 200.",
                snippet.find(query, 150),
                is(expected));

        assertThat("length of the snippet is 200.",
                Math.abs(150 - snippet.find(query, 150).length()),
                lessThanOrEqualTo(10));
    }

    @Test
    public void testHilightSnippetWithLength200() {
        SnippetFinder snippet = new FindUsingWordDistace(document);
        String expected = "ap mobile phones making with great product features. " +
                "Which are durable and provide competing mobile phone features. " +
                "Micromax makes, offers a good value for money, phones reliable, feature rich and cheap!";

        assertThat("find snippet, length 200.",
                snippet.find(query, 200),
                is(expected));

        assertThat("length of the snippet is 200.",
                Math.abs(200 - snippet.find(query, 200).length()),
                lessThanOrEqualTo(10));
    }

    @Test
    public void testHilightSnippet1() {
        SnippetFinder snippet = new FindUsingWordDistace("Little star's deep dish pizza sure is fantastic.");
        String expected = "Little star's deep dish pizza sure is fantastic.";

        assertThat("find snippet, using default snippet length",
                snippet.find("deep dish pizza"),
                is(expected));

        assertThat("length of the snippet is 100.",
                snippet.find(query, 100).length(),
                equalTo(expected.length()));
    }
}
