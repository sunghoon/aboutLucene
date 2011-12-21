package com.tistory.devyongsik.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.ReusableAnalyzerBase;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

/**
 * @author need4spd, need4spd@cplanet.co.kr, 2011. 12. 21.
 *
 */
public class RemoteTestAnalyzer extends ReusableAnalyzerBase {
	
	@Override
	protected TokenStreamComponents createComponents(final String fieldName,
			Reader reader) {
		
		String result = "¹«±ÃÈ­ ²É";
		reader = new StringReader(result);
		
		final Tokenizer source = new StandardTokenizer(Version.LUCENE_33, reader);
		
		Set<String> stopSet = new TreeSet<String>();
		
		return new TokenStreamComponents(source, new StopFilter(Version.LUCENE_33, source, stopSet));
	}
}
