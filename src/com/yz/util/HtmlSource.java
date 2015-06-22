package com.yz.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlSource {

	private static String encoding;

	/**
	 * 
	 * 通过url返回网页源代码<BR>
	 * 方法名：getHtmlSoursceByUrl<BR>
	 * 创建人：laotou <BR>
	 * 时间：2014年10月31日-下午9:00:59 <BR>
	 * 
	 * @param url
	 * @param encoding
	 * @return String<BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	public static String getHtmlSoursceByUrl(String url) {
		StringBuffer buffer = new StringBuffer();
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			/* 导import java.net.URL; */
			URL urlObj = new URL(url); // 建立网络连接
			uc = urlObj.openConnection();// 打开网络连接

			String charset = uc.getContentType();
			if (charset.indexOf("charset=") > -1) {
				encoding = charset.split("charset=")[1];
				System.out.println("charset:" + charset);
			} else {
				encoding = "GBK";
			}
			// 获取网页是否被压缩
			String gzip = uc.getContentEncoding();
			System.out.println("gzip:" + gzip);

			/* import java.io.InputStreamReader */
			// 建立文件的写入流
			isr = new InputStreamReader(uc.getInputStream(), encoding);
			// 建立写入文件缓冲流
			reader = new BufferedReader(isr);

			// 按每一行的形式进行循环读取
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return buffer.toString(); // 强转为string
	}

	/**
	 * 
	 * 解析网页源代码，并采集指定元素内容<BR>
	 * 方法名：getWebElement<BR>
	 * 创建人：laotou <BR>
	 * 时间：2014年10月31日-下午9:01:37 <BR>
	 * 
	 * @param url
	 * @param encoding
	 * @return List<HashMap<String,Object>><BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */
	@SuppressWarnings("unused")
	public static List<Map<String, String>> getWebElement(String url) {
		List<Map<String, String>> list = null;

		// 获取网页源代码
		String htmlSource = getHtmlSoursceByUrl(url);

		/* 导入import org.jsoup.Jsoup; */
		// 解析网页源代码
		Document document = Jsoup.parse(htmlSource);

		/* 导 import org.jsoup.nodes.Element; */
		// 获取网页指定元素的外层div元素
		Element element = document.getElementById("bet_content"); // 赛事列表
		if (element == null) {
			return list;
		}
		// 获取list外层DIV元素
		/* 导import org.jsoup.select.Elements; */
		Elements elemens = document.getElementsByTag("tr");
		list = new ArrayList<Map<String, String>>();
		// 创建集合来接收数据
		HashMap<String, String> map = null;
		if (elemens.size() == 0) {
			return list;
		}
		for (Element el : elemens) {
			// pdate 比赛日期,作为数据抓取依据
			String pdate = el.getElementsByTag("tr").attr("pdate");
			if (StringUtils.isEmpty(pdate)) {
				continue;
			}

			map = new HashMap<String, String>();
			map.put("gameDate", pdate);
			// zid 编号
			String zid = el.getElementsByTag("tr").attr("zid");
			map.put("code", zid);
			// fid 未知
			String fid = el.getElementsByTag("tr").attr("fid");
			// System.out.println("fid:"+fid);
			// mid 未知
			String mid = el.getElementsByTag("tr").attr("mid");
			// System.out.println("mid:"+mid);
			// pname 未知
			String pname = el.getElementsByTag("tr").attr("pname");
			// System.out.println("pname:"+pname);
			// 截止时间
			String pendtime = el.getElementsByTag("tr").attr("pendtime");
			map.put("endtime", pendtime);

			String game_num = el.getElementsByClass("game_num").val();
			if (!game_num.equals("")) {
				// map.put("game_num", game_num);
			}

			Elements tds = el.getElementsByTag("td");
			for (Element td : tds) {
				// 赛事名称
				Elements elNames = td.getElementsByClass("league");
				for (Element elName : elNames) {
					String name = elName.getElementsByTag("a").text();
					map.put("name", name);
				}

				// 主队名称
				if (td.hasClass("left_team")) {
					String leftName = td.getElementsByTag("a").text();
					map.put("homeName", leftName);
					// System.out.println(td.getElementsByTag("a").text()+"=============="+league);
				}

				// 客队名称
				if (td.hasClass("right_team")) {
					String rightName = td.getElementsByTag("a").text();
					map.put("guestName", rightName);
					// System.out.println(td.getElementsByTag("a").text()+"=============="+league);
				}

				// 陪率值
				Elements odds = td.getElementsByClass("odds_item");
				for (int i = 0; i < odds.size(); i++) {
					String value = odds.eq(i).text();
					switch (i) {
					case 0:
						map.put("win1", value);
						break;
					case 1:
						map.put("ping1", value);
					case 2:
						map.put("lose1", value);
						break;
					case 3:
						map.put("win2", value);
					case 4:
						map.put("ping2", value);
						break;
					case 5:
						map.put("lose2", value);
					}
				}

				// 欧/亚陪网址
				Elements as = td.getElementsByTag("a");
				for (Element a : as) {
					if (a.text().trim().equals("欧")) {
						map.put("ouzhi", a.attr("href"));
						// getHtml2(a.attr("href"));
					} else if (a.text().trim().equals("亚")) {
						map.put("yazhi", a.attr("href"));
					}

				}
				// 欧陪值
				if (td.hasClass("oupei")) {
					Elements oups = td.getElementsByTag("span");
					for (Element op : oups) {
						System.out.println(td);
						if (!op.text().trim().equals("")) {
							System.out.println("op:" + op.text());
						}
					}
				}
			}
			list.add(map);
			// System.out.println("=================================================================");

		}
		return list;
	}

	public static void getHtml2(String url2) {
		String html = getHtmlSoursceByUrl(url2);

		// 解析网页源代码
		Document document = null;
		try {
			document = Jsoup.parse(new String(html.getBytes("ISO-8859-1"),
					"GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* 导 import org.jsoup.nodes.Element; */
		// 获取网页指定元素的外层div元素

		Element elm = document.getElementById("table_cont"); // 赛事列表
		if (elm == null) {
			return;
		}
		Elements els = elm.getElementsByTag("tr");

		for (Element el : els) {
			
			System.out.println(el);

		}

	}

	public static void main(String[] args) {
		String url = "http://trade.500.com/jczq/";
		// List<HashMap<String, Object>> list = getPicElement(url, "utf-8");
		List<Map<String, String>> list = getWebElement(url);
		System.out.println(list);
		// if(list != null && list.size() >0){
		// for(HashMap<String, Object> map : list){
		// System.out.println(map.get("pdate"));
		// System.out.println(map.get("zid"));
		// System.out.println(map.get("game_num"));
		// System.out.println(map.get("end_time"));
		// System.out.println(map.get("match_time"));
		// System.out.println(map.get("left_team"));
		// // }
		// }

	}

	/**
	 * 
	 * 获取网页图片<BR>
	 * 方法名：getPicElement<BR>
	 * 创建人：laotou <BR>
	 * 时间：2014年11月1日-下午11:25:24 <BR>
	 * 
	 * @param url
	 * @param encoding
	 * @return List<HashMap<String,Object>><BR>
	 * @exception <BR>
	 * @since 1.0.0
	 */

	@SuppressWarnings("unused")
	public static List<HashMap<String, Object>> getPicElement(String url,
			String encoding) {
		List<HashMap<String, Object>> picList = new ArrayList<HashMap<String, Object>>();

		// 获取网页源代码
		String htmlSource = getHtmlSoursceByUrl(url);

		/* 导入import org.jsoup.Jsoup; */
		// 解析网页源代码
		Document document = Jsoup.parse(htmlSource);

		/* 导 import org.jsoup.nodes.Element; */
		Elements elemens1 = document.getElementsByClass("list_pic");

		// 获取list外层DIV元素
		/* 导import org.jsoup.select.Elements; */
		Elements elemens2 = document.getElementsByClass("img");
		System.out.println(elemens2);
		// 创建集合来接收数据
		HashMap<String, Object> map = null;
		for (Element el : elemens2) {
			map = new HashMap<String, Object>();
			// 获取图片地址
			String imgSrc = el.getElementsByTag("img").attr("src");
			String imgWid = el.getElementsByTag("img").attr("width");
			String imgHei = el.getElementsByTag("img").attr("height");
			// 获取链接地址
			String hrefAdd = el.getElementsByTag("a").attr("href");
			// 获取标题
			String title = el.getElementsByTag("img").attr("alt");
			map.put("hrefAdd", hrefAdd);
			map.put("imgSrc", imgSrc);
			map.put("title", title);
			map.put("imgWid", imgWid);
			map.put("imgHei", imgHei);
			picList.add(map);
		}
		return picList;
	}

	/**
	 * 解GZIP
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

}
