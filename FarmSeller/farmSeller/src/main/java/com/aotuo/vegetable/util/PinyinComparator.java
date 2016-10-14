package com.aotuo.vegetable.util;

import com.aotuo.vegetable.entity.SortMessage;

import java.util.Comparator;

/**
 * 
 * @author shiguoyuan
 * 
 */
public class PinyinComparator implements Comparator<SortMessage> {

	@Override
	public int compare(SortMessage o1, SortMessage o2) {
		if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return 1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return -1;
		} else {
			String str1 = PingYinUtil.getPingYin(o1.getSortLetters());
			String str2 = PingYinUtil.getPingYin(o2.getSortLetters());
			return str1.compareTo(str2);
		}
	}
}
