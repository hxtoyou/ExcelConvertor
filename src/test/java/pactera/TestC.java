package pactera;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * @author Xiao He E-mail:hxtoyou@163.com
 * @version 创建时间：2015年8月17日 下午5:05:08
 * 
 */
public class TestC {
	public static void main(String args[]){
		int _max = 100;
		int[] c1=new int[_max];
		int[] c2 = new int[_max];
		System.out.println("开始："+c1);
		int nNum=15 ;
		int i, j, k;
		for(i=0; i<=nNum; ++i)   // ---- ①
		{
			c1[i] = 1;
			c2[i] = 0;
		}
		for(i=2; i<=nNum; ++i)   // ----- ②
		{
 
			for(j=0; j<=nNum; ++j)   // ----- ③
				for(k=0; k+j<=nNum; k+=i)  // ---- ④
				{
					c2[j+k] += c1[j];
				}
			for(j=0; j<=nNum; ++j)     // ---- ⑤
			{
				c1[j] = c2[j];
				c2[j] = 0;
			}
		}
			System.out.println("结束："+JSON.toJSONString(c1));
			
	}
}
