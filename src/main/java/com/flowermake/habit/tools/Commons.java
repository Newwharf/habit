package com.flowermake.habit.tools;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

/**
 * 常用工具类
 * 
 * @author lihan
 *
 */
public class Commons {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		JSONArray jsarray = new JSONArray();
//		jsarray.add(0);
//		jsarray.add(1);
//		jsarray.add(2);
//		jsarray.add(3);
//		jsarray.add(4);
//		jsarray.add(5);
//		jsarray.add(6);
//		jsarray.add(7);
//		jsarray.add(8);
//		jsarray.add(9);
//		jsarray.add(10);
//		JSONArray tempArray = new Commons().getTargetArrayByIndex(jsarray, 9, 2);
//
//		for (Object i : tempArray) {
//			System.out.println(i.toString());
//		}
	}

	/**
	 * 取出jsonArray在该集合中index位置处的前后各num位，组成一个新数组，如果某一遍的元素长度不足num，则取另一边补齐
	 * 
	 * @param jsonArray
	 *            原数组
	 * @param index
	 *            参考位置
	 * @param num
	 *            两侧各取多位
	 * @return
	 */
	public static JSONArray getTargetArrayByIndex(JSONArray jsonArray, int index, int num) {
		JSONArray targetArray = new JSONArray();
		int targetSize = (2 * num) + 1;
		if (jsonArray.size() > targetSize) {
			int right = 0;
			int left = 0;

			if (index > 2) {
				if (jsonArray.size() - index > num) {
					left = num;
				} else {
					left = targetSize - (jsonArray.size() - index);
				}
			} else {
				left = index;
			}
			right = targetSize - left - 1;
			// System.out.println("参考位：" + index);
			// System.out.println("长度为：" + jsonArray.size());
			// System.out.println("左边取：" + left);
			// System.out.println("右边取：" + right);

			for (int i = 0; i < jsonArray.size(); i++) {
				if (i >= (index - left) && i <= (index + right)) {
					targetArray.add(jsonArray.get(i));
				}
			}
		} else {
			targetArray = jsonArray;
		}
		return targetArray;
	}

	/**
	 * 根据年龄取得出生年份，月份与日期默认1月1号
	 * 
	 * @param age
	 *            年龄
	 * @return 出生年月的date对象
	 * @throws ParseException
	 */
	public static Date getDateByAge(int age) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Integer year = Integer.valueOf(dateFormat.format(new Date())) - age;
		return dateFormat.parse("" + year);
	}

	/**
	 * 根据时间字符串返回date
	 * 
	 * @param dateString
	 *            时间字符串
	 * @param format
	 *            时间字符串格式
	 * @return date
	 * @throws Exception
	 */
	public static Date getDateByString(String dateString, String format) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(dateString);
		return date;
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            要格式化的date
	 * @param format
	 *            格式化字符串
	 * @return 返回格式化的时间字符串
	 * @throws Exception
	 */
	public static String formatDate(Date date, String format) throws Exception {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * ajax返回统一调用方法
	 * 
	 * @param response
	 *            传入的response对象
	 * @param str
	 *            要返回的字符串
	 * @throws Exception
	 */
	public static void ajaxResponse(HttpServletResponse response, String str) throws Exception {
		response.setContentType("application/json");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}

	/**
	 * 判断第一个值与第二值的关系是否成立
	 * 
	 * @param value1
	 *            要比较的第一个值
	 * @param value2
	 *            要比较的第二个值
	 * @param nexus
	 *            比较关系，0：大于等于，1：小于等于
	 * @return 如果关系成立,返回true，否则false
	 */
	public static boolean value1CompareValue2(float value1, float value2, byte nexus) {
		if (nexus == 0) {
			if (value1 >= value2) {
				return true;
			} else {
				return false;
			}
		} else {
			if (value1 <= value2) {
				return true;
			} else {
				return false;
			}
		}
	}

}
