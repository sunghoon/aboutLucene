package com.tistory.devyongsik.analyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author need4spd, need4spd@cplanet.co.kr, 2011. 7. 8.
 *
 */
public class DevysStopFilterTest {

	private static Set<String> tokens = new HashSet<String>();
	//�ҿ��� the�� .
	StringReader reader = new StringReader("the �����ϰ� �� �̰��� �� �ؾ��մϴ�. �����Դϴ�.");

	@BeforeClass
	public static void setUp() {
		tokens.add("�����ϰ�");
		tokens.add("�̰���");
		tokens.add("�ؾ��մϴ�");
		tokens.add("�����Դϴ�");
		tokens.add("��");
		tokens.add("��");
	}
	
	
	@Test
	public void stopFilter() throws IOException {
		TokenStream stream = new DevysStopFilter(true, new DevysTokenizer(reader));
		CharTermAttribute charTermAttr = stream.getAttribute(CharTermAttribute.class);
		PositionIncrementAttribute positionAttr = stream.getAttribute(PositionIncrementAttribute.class);
		
		while(stream.incrementToken()) {
			System.out.println("charTermAttr : " + charTermAttr.toString());
			System.out.println("positionAttr : " + positionAttr.getPositionIncrement());

			Assert.assertTrue(tokens.contains(charTermAttr.toString()));
		}
	}
}
