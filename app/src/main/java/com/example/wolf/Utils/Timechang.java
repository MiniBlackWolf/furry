package com.example.wolf.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by CWbl 2018-07-09
 */
public class Timechang
{
	private volatile static Timechang instance;

	public static Timechang getInstance()
	{
		if (instance == null) {
			synchronized (Timechang.class) {
				if (instance == null) {
					instance = new Timechang();
				}
			}
		}
		return instance;
	}

	public  String dateToStamp(String s) throws ParseException
	{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	public  String stampToDate(String s)
	{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
}
