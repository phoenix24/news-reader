package org.TBandar.Finder;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class SnippetWordDistace extends SnippetFinder {

    private final static Logger Log = LoggerFactory.getLogger(SnippetWordDistace.class);

    public SnippetWordDistace(String document) {
        super(document);
    }

    @Override
    public String find(String query, int snippetLength) {
        query = query.toLowerCase();
        List<String> tokens = Lists.newArrayList(Splitter.on(" ").split(query));

        int tokensCount = tokens.size();
        int dlength = document.length();

        int[][] snippets = new int[tokensCount][dlength];
        int[][] occurrences = new int[tokensCount][dlength];
        for (int i = 0; i < tokensCount; i++) {
            Arrays.fill(snippets[i], -1);
            Arrays.fill(occurrences[i], -1);
        }

        int index = 0, qNumber = -1, qIndex = -1;
        for (String token : tokens) {
            qIndex = 0;
            qNumber += 1;
            while ((index = document.indexOf(token, index + 1)) > -1 && index < document.length()) {
                if (index > -1) occurrences[qNumber][qIndex++] = index;
            }
            Log.debug("tokens {}", Arrays.toString(occurrences[qNumber]));
        }

        //find all the snippets.
        snippets = calcSnippetRanges(occurrences, snippets, tokensCount);

        //find the most relevant snippet, in this case longest.
        int[] longestSnippet = calcRelevantSnippet(snippets, tokensCount, snippetLength);
        String snippet = oDocument.substring(longestSnippet[1], longestSnippet[0])
                        + oDocument.substring(longestSnippet[0]).split(" ")[0];

        Log.debug("snippet-longest:{}, snippet-text:{}", Arrays.toString(longestSnippet), snippet);
        return snippet;
    }

    /**
     * Returns the most relevant snippet, of the given length.
     * We identify the smallest snippet of length greater that the required snippetLength,
     * and then trim out the noise words to fit the length criterion.
     *
     * @param snippets      snippets that have been identified.
     * @param tokensCount   number of query tokens
     * @param snippetLength maximum required length of the snippet.
     * @return
     */
    protected int[] calcRelevantSnippet(int[][] snippets, int tokensCount, int snippetLength) {
        int[] indices = new int[tokensCount];
        int snippetIndex = 0, result = 0, pIndex = 0, nIndex = 0,
                pDiff = Integer.MAX_VALUE, nDiff = Integer.MIN_VALUE;

        while (snippets[0][snippetIndex] != -1) {
            for (int i = 0; i < tokensCount; i++) {
                indices[i] = snippets[i][snippetIndex];
            }
            Arrays.sort(indices);
            int curSnippetLength = indices[tokensCount - 1] - indices[0];
            int diffLength = curSnippetLength - snippetLength;

            if (diffLength >= 0 && diffLength < pDiff) {
                pDiff = diffLength;
                pIndex = snippetIndex;
            } else if (diffLength < 0 && diffLength > nDiff) {
                nDiff = diffLength;
                nIndex = snippetIndex;
            }
            snippetIndex++;
        }

        result = nDiff <= 10 ? nIndex : pIndex;
        for (int i = 0; i < tokensCount; i++) {
            indices[i] = snippets[i][result];
        }
        Log.debug("snippet-relevant indices: {}", indices);
        return trimSnippet(indices, snippetLength);
    }

    protected int[] trimSnippet(int[] indices, int snippetLength) {
        Arrays.sort(indices);
        int start = indices[0], stop = indices[indices.length - 1];
        int diff = (snippetLength - (stop - start)) / 2;

        Log.debug("remaining-length : {}, indices:{}", diff, Arrays.toString(indices));
        while (diff != 0 && (stop - start < document.length())) {

            //move the start index.
            int u1diff = start - diff < 0 ? start : diff;
            start -= u1diff;

            //move the stop index.
            int u2diff = stop + diff > document.length() ? document.length() - stop : diff;
            stop += u2diff;

            //evaluate how much more to expand.
            diff = (2 * diff - u1diff - u2diff) / 2;
        }
        return new int[]{stop, start};
    }

    /**
     * Find all the snippets, in the said document.
     * We basically return the index positions for all the possible snippets possible.
     * The basic advantage of doing this is that all the snippets identified thus far, can be used various
     * algorithms to evaluate relevant snippets using natural language or other techniques.
     *
     * Note: all of these snippets can also be treated as snippet windows., I realized term much later.
     *
     * ex. for these tokens - mobile, phone, cheap; their indexes in the document may be as follow -
     * mobile : 105,  239
     * phone : 27, 127, 217
     * cheap : 34, 131
     *
     * Thus, our first snippet is with following indices -
     * 105,
     * 27
     * 34
     * |---- this is the first snippet, or as I call it snippet-index 0.
     *
     *
     * To be able to calculate the next snippet, I simply move the smallest index to its next valid occurance., thus
     * resulting in -
     *
     * 105, 105,
     * 27,  127,
     * 34,  34,
     * |    |-----this is the second snippet, or as I call it snippet-index 1.
     * |----------this is the first snippet, or as I call it snippet-index 0.
     *
     * And finally, we get all the snippets which have all the tokens present -
     * 105, 105, 105, 239, 239,  <-- mobile  (occurrences of the token).
     * 27 , 127, 127, 127, 217,  <-- phone
     * 34 , 34 , 131, 131, 131,  <-- cheap
     * |    |     |    |    |-------- fifth snippet, snippet-index 4.
     * |    |     |    |------------- fourth snippet, snippet-index 3.
     * |    |     |------------------ third  snippet, snippet-index 2.
     * |    |------------------------ second snippet, snippet-index 1.
     * |-----------------------------  first snippet, snippet-index 0.
     *
     * @param occurances
     * @param snippets
     * @param qlength
     * @return
     */
    protected int[][] calcSnippetRanges(int[][] occurances, int[][] snippets, int qlength) {
        int snippetCount = 0, tokenSmallestIndex = 0, tokenSmallestValue = Integer.MAX_VALUE;
        int[] tokenIndices = new int[qlength];

        do {
            for (int i = 0; i < qlength; i++) {
                snippets[i][snippetCount] = occurances[i][tokenIndices[i]];
                if (occurances[i][tokenIndices[i]] <= tokenSmallestValue && occurances[i][tokenIndices[i] + 1] != -1) {
                    tokenSmallestIndex = i;
                    tokenSmallestValue = occurances[i][tokenIndices[i]];
                }
                Log.debug("snippet {}", Arrays.toString(snippets[i]));
            }
            snippetCount += 1;
            tokenIndices[tokenSmallestIndex]++;
            tokenSmallestValue = Integer.MAX_VALUE;
        } while (isSnippetAvailable(occurances, tokenIndices));
        return snippets;
    }

    /**
     * Returns true if any more snippets are possible., else false.
     * Basically, returns true if any of the tokens has a next valid index present.,
     * such that it can be used to create a new snippet.
     *
     * @param occurrences
     * @param indexes
     * @return
     */
    protected boolean isSnippetAvailable(int[][] occurrences, int[] indexes) {
        boolean available = false;
        for (int i = 0; i < indexes.length; i++) {
            available |= occurrences[i][indexes[i] + 1] > -1;
        }
        return available;
    }
}
