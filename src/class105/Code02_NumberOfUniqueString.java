package class105;

import java.util.Arrays;
import java.util.HashSet;

// 独特子串的数量
// 给你一个由数字组成的字符串s
// 返回s中独特子字符串数量
// 其中的每一个数字出现的频率都相同
// 测试链接 : https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency/
public class Code02_NumberOfUniqueString {

	public static int equalDigitFrequency(String str) {
		long base = 499;
		char[] s = str.toCharArray();
		int n = s.length;
		HashSet<Long> set = new HashSet<>();
		// 词频表 0-9 10个数字
		int[] cnt = new int[10];
		for (int i = 0; i < n; i++) {
			// i...i
			// i...i+1
			// i...i+2
			// i...n-1
			Arrays.fill(cnt, 0);
			long hashCode = 0; // i...j的哈希值
			// 假设i...j -> a
			// 那么i...j+1 -> a*base + s[j+1] -> b
			// 那么i...j+2 -> b*base + s[j+2] -> c
			int curVal = 0, maxCnt = 0, maxCntKinds = 0, allKinds = 0;
			for (int j = i; j < n; j++) {
				curVal = s[j] - '0'; // 当前数字
				hashCode = hashCode * base + curVal + 1; // +1是为了让0字符变成1字符，举例：‘0231’
				// 更新词频表
				cnt[curVal]++;
				// cnt[curVal] == 1，说明当前数字是第一次出现，那么独特数字种类+1
				if (cnt[curVal] == 1) {
					allKinds++;
				}
				// cnt[curVal] > maxCnt，说明当前数字出现的次数比之前出现的次数多，那么更新maxCnt和maxCntKinds
				if (cnt[curVal] > maxCnt) {
					maxCnt = cnt[curVal];
					maxCntKinds = 1;
					// cnt[curVal] == maxCnt，说明当前数字出现的次数和之前出现的次数一样多，那么maxCntKinds+1
				} else if (cnt[curVal] == maxCnt) {
					maxCntKinds++;
				}
				// 判断是否为独特子串
				if (maxCntKinds == allKinds) {
					set.add(hashCode);
					/*
					HashSet 是基于哈希表（Hash Table）实现的，它的主要特点是：
					不允许重复元素：当你向 HashSet 中插入一个元素时，如果该元素已经存在，HashSet 不会添加这个重复的元素。换句话说，HashSet 会自动去重。
					无序存储：HashSet 不保证元素的顺序，这意味着存储在 HashSet 中的元素的迭代顺序可能和插入顺序不同。
					 */
				}
			}
		}
		return set.size();
	}
}
