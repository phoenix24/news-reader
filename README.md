Simple api to return hilighted snippet using a hilighter!

It implements a few hilihghers such as Hilight with Markup, or Quotes, or HTML.
And, a simple window based algorithm to find snippets that fit this window.

If you specify the length of a snippet, it tries to return a snippet of such length;
but otherwise returns a snippet of default length - 100 characters.

Snippet Finder is the most important class, that currently implements a very naive algorithm.
I should enhance the snippet relevance, with either a TF-IDF or PageRank type algorithm, either ways,
must study some NLP.

Algorithmic enhancements (or new implementations) to snippet finder may simply implement snippet-finder
thereby encapsulating different algorithms for snippet finding in their own classes and increasing loose-coupling.

Also a helper class, DecoratedSnippet is what must be used by developers;
Because using it, one may specify choice of snippet finding algorithm or a hilighter.

I think the api is thread safe, and a lot of performance improvements can still be made.
Also, I think with little work and using jackson json annotation, it can be easily converted in to webservice.

Project follows standard code layout as said by maven build, Please start by looking at the test-suite.

project tree
--
➜  snippet-finder git:(master) ✗ tree
.
|-- README.md
|-- pom.xml
|-- snippet-finder.iml
`-- src
    |-- main
    |   |-- java
    |   |   `-- org
    |   |       `-- TBandar
    |   |           |-- DecoratedSnippet.java
    |   |           |-- Decorator
    |   |           |   |-- HiLightWithHTML.java
    |   |           |   |-- HiLightWithMarkUp.java
    |   |           |   |-- HiLightWithQuotes.java
    |   |           |   `-- HiLighter.java
    |   |           |-- Finder
    |   |           |   |-- SnippetFinder.java
    |   |           |   `-- SnippetWordDistace.java
    |   |           `-- exceptions
    |   |               `-- InvalidSnippetException.java
    |   `-- resources
    `-- test
        `-- java
            `-- org
                `-- TBandar
                    |-- DecoratedSnippetTest.java
                    |-- Decorator
                    |   |-- HiLightWithHTMLTest.java
                    |   |-- HiLightWithMarkUpTest.java
                    |   `-- HiLightWithQuotesTest.java
                    `-- Finder
                        `-- SnippetWordDistanceTest.java

15 directories, 16 files


build
--
mvn clean package.


test
--
mvn test


external dependencies
--
jar dependencies are specificed in pom.xml, which includes JUnit, Hamcrest, Logback and Google Guava.