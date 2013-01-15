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

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSnippetDecorateWithMarkUp() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithMarkUp())
                .forQuery(query)
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet();

        String expected = "Little star's [[HIGHLIGHT]]deep dish pizza[[ENDHIGHLIGHT]] sure is fantastic.";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetDecorateWithHTML() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithHTML())
                .forQuery(query)
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet();

        String expected = "Little star's <b>deep dish pizza</b> sure is fantastic.";
        assertThat(actual, is(expected));
    }

    @Test
    public void testSnippetDecorateWithQuotes() throws Exception {

        String query = "deep dish pizza";
        String document = "Little star's deep dish pizza sure is fantastic.";
        String actual = new DecoratedSnippet()
                .decorateWith(new HiLightWithQuotes())
                .forQuery(query)
                .forSnippet(new SnippetWordDistace(document))
                .getDecoratedSnippet();

        String expected = "Little star's 'deep dish pizza' sure is fantastic.";
        assertThat(actual, is(expected));
    }
}
