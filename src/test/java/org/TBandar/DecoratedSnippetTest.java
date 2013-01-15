package org.TBandar;

import org.TBandar.Decorator.HiLightWithHTML;
import org.TBandar.Decorator.HiLightWithMarkUp;
import org.TBandar.Decorator.HiLightWithQuotes;
import org.TBandar.Finder.SnippetWordDistace;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DecoratedSnippetTest {

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
    public void testSnippetDecorateWithMarkUp() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithMarkUp())
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet(query);

        String expected = "Little star's [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]] sure is fantastic.";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetDecorateWithHTML() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithHTML())
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet(query);

        String expected = "Little star's <b>deep dish pizza</b> sure is fantastic.";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetDecorateWithQuotes() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithQuotes())
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet(query);

        String expected = "Little star's 'deep dish pizza' sure is fantastic.";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetLength150DecorateWithHTML() throws Exception {

        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithHTML())
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet(query, 150);

        String expected = "icromax, offer <b>cheap mobile phones</b> making with great product features. " +
                "Which are durable and provide competing mobile phone features. <b>Micromax</b> makes,";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetLength150DecorateWithMarkUp() throws Exception {

        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithMarkUp())
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet(query, 200);

        String expected = "ap [[HIGHLIGHT]]mobile phones[[ENDHIGHLIGHT]] making with great product features. " +
                "Which are durable and provide competing mobile phone features. [[HIGHLIGHT]]Micromax[[ENDHIGHLIGHT]] " +
                "makes, offers a good value for money, phones reliable, feature rich and [[HIGHLIGHT]]cheap[[ENDHIGHLIGHT]]!";
        assertThat(actual, is(expected));
    }
}
