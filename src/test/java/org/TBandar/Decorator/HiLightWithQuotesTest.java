package org.TBandar.Decorator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HiLightWithQuotesTest {

    private String query;
    private String snippet;
    private HiLighter hiLighter;

    @Before
    public void setUp() throws Exception {
        query = "micromax mobile cheap";
        hiLighter = new HiLightWithQuotes();
    }

    @Test
    public void testDecorateWithTestData1() throws Exception {
        snippet = "MicroMax Phones are good value for money, reliable, feature rich and cheap!";
        String expected = "'MicroMax' Phones are good value for money, reliable, feature rich and 'cheap'!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData2() throws Exception {
        snippet = "MicroMax Phones are ... mobile cheap!";
        String expected = "'MicroMax' Phones are ... 'mobile cheap'!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData3() throws Exception {
        snippet = "MicroMax Phones are mobile ... cheap!";
        String expected = "'MicroMax' Phones are 'mobile' ... 'cheap'!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void testDecorateWithNullSnippet() throws Exception {
        assertThat(hiLighter.decorate(null, query), is(""));
    }

    @Test(expected = NullPointerException.class)
    public void testDecorateWithNullQuery() throws Exception {
        assertThat(hiLighter.decorate(snippet, null), is(""));
    }
}
