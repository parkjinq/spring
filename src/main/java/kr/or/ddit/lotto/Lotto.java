package kr.or.ddit.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Lotto {
	private int maxNum, ballMax;
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getBallNum() {
		return ballMax;
	}
	public void setBallNum(int ballMax) {
		this.ballMax = ballMax;
	}
	public int[] excute() {
		List<Integer> num = new ArrayList<Integer>();	//숫자범위
		int[] result = new int[ballMax];				//오름차순정렬된 추점결과
		for(int i = 0; i < maxNum; i++) {				//최대숫자까지 숫자 배열 생성
			num.add(i, i+1);
		}
		Collections.shuffle(num);						//리스트 데이터 랜덤섞기
		for(int i = 0; i < ballMax; i++) {				//랜덤추첨숫자 6개 담기
			result[i] = num.get(i);
		}
		Arrays.sort(result);							//결과 오름차순 정렬
		return result;
	}
}






/*
public List<Integer> excute() {
	Set<Integer> set = new HashSet<Integer>();
	while(set.size() < ballMax) {
		set.add((int)(Math.random()*maxNum + 1));
	}
	List<Integer> list = new ArrayList<Integer>(set);
	Collections.sort(list);
	return list;
}
*/
/*
public int[] excute() {
	List<Integer> num = new ArrayList<Integer>();	//숫자범위
	int[] result = new int[ballMax];				//오름차순정렬된 추점결과
	for(int i = 1; i <= maxNum; i++) {				//최대숫자까지 숫자 배열 생성
		num.add(i-1, i);
	}
	Collections.shuffle(num);						//리스트 데이터 랜덤섞기
	for(int i = 0; i < ballMax; i++) {				//랜덤추첨숫자 6개 담기
		result[i] = num.get(i);
	}
	Arrays.sort(result);							//결과 오름차순 정렬
	return result;
}
*/
/*
public int[] excute() {
	int[] num = new int[maxNum];				//숫자범위
	Set<Integer> set = new HashSet<Integer>();	//중복없는 추천결과
	int[] result = new int[ballMax];			//오름차순정렬된 추점결과
	for(int i = 1; i <= maxNum; i++) {			//최대숫자까지 숫자 배열 생성
		num[i-1] = i;
	}
	while(ballMax > set.size()) {				//볼의 개수만큼 중복되지 않는 숫자 추첨
		set.add(num[(int)(Math.random()*maxNum)]);
	}
	int index = 0; 								//추첨결과 결과에 담기
	for(Iterator<Integer> it = set.iterator(); it.hasNext();) {
		result[index++] = it.next();
	}
	Arrays.sort(result);						//결과 오름차순 정렬
	return result;
}
*/