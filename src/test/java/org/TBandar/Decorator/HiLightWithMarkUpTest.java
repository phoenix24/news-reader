package org.TBandar.Decorator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HiLightWithMarkUpTest {

    private String query;
    private HiLighter hiLighter;
    private String snippet;

    @Before
    public void setUp() throws Exception {
        query = "micromax mobile cheap";
        hiLighter = new HiLightWithMarkUp();
    }

    @Test
    public void testDecorateWithTestData1() throws Exception {
        snippet = "MicroMax Phones are good value for money, reliable, feature rich and cheap!";
        String expected = "[[HIGHLIGHT]]MicroMax[[ENDHIGHLIGHT]] Phones are good value for money, " +
                          "reliable, feature rich and [[HIGHLIGHT]]cheap[[ENDHIGHLIGHT]]!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData2() throws Exception {
        snippet = "MicroMax Phones are ... mobile cheap!";
        String expected = "[[HIGHLIGHT]]MicroMax[[ENDHIGHLIGHT]] Phones are ... " +
                          "[[HIGHLIGHT]]mobile[[ENDHIGHLIGHT]] [[HIGHLIGHT]]cheap[[ENDHIGHLIGHT]]!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

    @Test
    public void testDecorateWithTestData3() throws Exception {
        snippet = "MicroMax Phones are mobile ... cheap!";
        String expected = "[[HIGHLIGHT]]MicroMax[[ENDHIGHLIGHT]] Phones are [[HIGHLIGHT]]mobile[[ENDHIGHLIGHT]]" +
                          " ... [[HIGHLIGHT]]cheap[[ENDHIGHLIGHT]]!";
        assertThat(hiLighter.decorate(snippet, query), is(expected));
    }

}
