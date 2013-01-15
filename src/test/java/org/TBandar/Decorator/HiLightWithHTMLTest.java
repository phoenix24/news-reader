package org.TBandar.Decorator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HiLightWithHTMLTest {
    
    private String query;
    private String snippet;
    private HiLighter hiLighter;

    @Before
    public void setUp() throws Exception {
        query = "micromax mobile cheap";
        hiLighter = new HiLightWithHTML();
    }

    @Test
    public void testDecorateWithTestData1() throws Exception {
        snippet = "MicroMax Phones are good value for money, reliable, feature rich and cheap!";
        String expected = "<b>MicroMax</b> Phones are good value for money, reliable, feature rich and <b>cheap</b>!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData2() throws Exception {
        snippet = "MicroMax Phones are ... mobile cheap!";
        String expected = "<b>MicroMax</b> Phones are ... <b>mobile cheap</b>!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData3() throws Exception {
        snippet = "MicroMax Phones are mobile ... cheap!";
        String expected = "<b>MicroMax</b> Phones are <b>mobile</b> ... <b>cheap</b>!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }
}
