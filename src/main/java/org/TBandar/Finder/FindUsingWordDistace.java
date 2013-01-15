package org.TBandar.Finder;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FindUsingWordDistace extends SnippetFinder {

    private final static Logger Log = LoggerFactory.getLogger(FindUsingWordDistace.class);

    public FindUsingWordDistace(String document) {
        super(document);
    }

    @Override
    public String find(String query, int length) {

        //document is smaller than required default length.
        if (document.length() <= length) return document;

        query = query.toLowerCase();
        List<String> tokens = Lists.newArrayList(Splitter.on(" ").split(query));

        int qlength = tokens.size();
        int dlength = document.length();

        int[][] snippets = new int[qlength][dlength];
        int[][] occurances = new int[qlength][dlength];
        for (int i = 0; i < qlength; i++) {
            Arrays.fill(snippets[i], -1);
            Arrays.fill(occurances[i], -1);
        }

        int index = 0, qNumber = -1, qIndex = -1;
        for (String token : tokens) {
            qIndex = 0;
            qNumber += 1;
            while ((index = document.indexOf(token, index + 1)) > -1 && index < document.length()) {
                if (index > -1) occurances[qNumber][qIndex++] = index;
            }

            System.out.println("tokens : " + Arrays.toString(occurances[qNumber]));
        }

        snippets = calcSnippetRanges(occurances, snippets, qlength);
        int[] longestSnippet = calcRelevantSnippet(snippets, qlength);
        System.out.print(Arrays.toString(longestSnippet));
        return document.substring(longestSnippet[1], longestSnippet[0]) + document.substring(longestSnippet[0]).split(" ")[0];
    }

    protected int[] calcRelevantSnippet(int[][] snippets, int qlength) {
        int snippetIndex = 0, length = 0, result = 0;
        int[] index = new int[qlength];
        while (snippets[0][snippetIndex] != -1) {
            for (int i = 0; i < qlength; i++) {
                index[i] = snippets[i][snippetIndex];
            }
            Arrays.sort(index);
            if (index[qlength - 1] - index[0] > length) {
                result = snippetIndex;
                length = index[qlength - 1] - index[0];
            }
            snippetIndex++;
        }

        for (int i = 0; i < qlength; i++) {
            index[i] = snippets[i][result];
        }
        Arrays.sort(index);
        System.out.println("longest-snippet : " + Arrays.toString(index));
        return new int[]{ index[qlength - 1], index[0] };
    }

    protected int[][] calcSnippetRanges(int[][] occurances, int[][] snippets, int qlength) {
        int snippetCount = 0, tokenSmallestIndex = 0, tokenSmallestValue = Integer.MAX_VALUE;
        int[] tokenIndexes = new int[qlength];

        do {
            for (int i = 0; i < qlength; i++) {
                snippets[i][snippetCount] = occurances[i][tokenIndexes[i]];
                if (occurances[i][tokenIndexes[i]] <= tokenSmallestValue && occurances[i][tokenIndexes[i]+1] != -1) {
                    tokenSmallestIndex = i;
                    tokenSmallestValue = occurances[i][tokenIndexes[i]];
                }
                System.out.println(Arrays.toString(snippets[i]));
            }
            snippetCount += 1;
            tokenIndexes[tokenSmallestIndex]++;
            System.out.println("tokenSmallestValue:" + tokenSmallestValue + ", tokenSmallestIndex:" + tokenSmallestIndex + ", tokenIndexes:" + Arrays.toString(tokenIndexes));
            tokenSmallestValue = Integer.MAX_VALUE;
        } while (isScoreAvailable(occurances, tokenIndexes));
        return snippets;
    }

    protected boolean isScoreAvailable(int[][] occurances, int[] indexes) {
        boolean available = false;

        for (int i = 0; i < indexes.length; i++) {
            available |= occurances[i][indexes[i]+1] > -1;
        }
        System.out.println(available + ", " + Arrays.toString(indexes));
        return available;
    }
}
