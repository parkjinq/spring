package kr.or.ddit.ioc;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import kr.or.ddit.lotto.Lotto;

public class LottoTest {

	@Test
	public void lottoTest() {
		/***Given***/
		Lotto lotto = new Lotto();

		/***When***/
		lotto.setMaxNum(46);			//숫자범위
		lotto.setBallNum(6);			//추첨숫자개수
		/***Then***/
		int[] result = lotto.excute();	//추첨결과 리스트
		System.out.println(Arrays.toString(result));
		assertEquals(lotto.getBallNum(), result.length);
	}

}
