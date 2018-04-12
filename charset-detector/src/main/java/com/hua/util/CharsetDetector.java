/**
 * CharsetDetector.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

/**
 * CharsetDetector
 * 描述: 
 * @author  qye.zheng
 */
public final class CharsetDetector
{

	private boolean found = false;
	
	private String encoding = null;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public CharsetDetector()
	{
	}
	
	/**
	 * 
	 * @description 
	 * @param file
	 * @param detector
	 * @return
	 * @author qianye.zheng
	 */
	public String guessFileEncoding(final File file, final nsDetector detector)
	{
	
		detector.Init(new nsICharsetDetectionObserver()
		{
			
			/**
			 * 
			 * @description 
			 * @param charset
			 * @author qianye.zheng
			 */
			@Override
			public void Notify(final String charset)
			{
				System.out.println("notify -- charset = " + charset);
				encoding = charset;
				found = true;
			}
		});
		try
		{
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int length = -1;
			boolean done = false;
			boolean isAscii = false;
			while (-1 != (length = bufferedInputStream.read(buf, 0, buf.length)))
			{
				isAscii = detector.isAscii(buf, length);
				if (isAscii)
				{ // ASCII 编码
				    encoding = "ASCII";
		            found = true;
					break;
				}
				done = detector.DoIt(buf, length, false);
				if (done)
				{
					break;
				}
			}
			bufferedInputStream.close();
			// 数据结束
			detector.DataEnd();
			if (!found)
			{ // 没有找到
				// 获取可能的字符集
				final String[] prob = detector.getProbableCharsets();
			      //这里将可能的字符集组合起来返回
	            for (int i = 0; i < prob.length; i++) {
	                if (i == 0) {
	                    encoding = prob[i];
	                } else {
	                    encoding += "," + prob[i];
	                }
	            }
	            if (prob.length > 0)
	            {
	            	return encoding;
	            } else
	            {
	            	return null;
	            }
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return encoding;
	}

}
