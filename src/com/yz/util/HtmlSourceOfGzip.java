package com.yz.util;

import java.io.BufferedInputStream;
import java.io.IOException;
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

public class HtmlSourceOfGzip {

	/**
	 * 
	 * 通过url返回一个GZIP网页源代码<BR>
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
	public static String getHtmlSoursceByUrlOfGzip(String url) {
		String encoding = null;
		StringBuffer buffer = new StringBuffer();
		URLConnection uc = null;
		GZIPInputStream gzipIn = null;
		try {
			/* 导import java.net.URL; */
			URL urlObj = new URL(url); // 建立网络连接
			uc = urlObj.openConnection();// 打开网络连接

			byte[] buf = new byte[1024];
			// 获取内面字符编码
			String charset = uc.getContentType();
			System.out.println(charset);
			if (charset.indexOf("charset=") > -1) {
				encoding = charset.split("charset=")[1];
			}
			// 如果没有指定，则默认采用gb2312
			if (StringUtils.isEmpty(encoding)) {
				encoding = "gb2312";
			}
			System.out.println("encoding:" + encoding);
			gzipIn = new GZIPInputStream(new BufferedInputStream(
					urlObj.openStream()));
			int num;
			while ((num = gzipIn.read(buf, 0, buf.length)) != -1) {
				buffer.append(new String(buf, encoding) + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (gzipIn != null)
					gzipIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return buffer.toString(); // 强转为string
	}

	/**
	 * 
	 * @Description: 解析数据
	 * @param @param url
	 * @param @return
	 * @return List<Map<String,String>>
	 * @throws
	 * @author yuanzhong
	 * @date 2015年5月4日 下午10:15:54
	 */
	public static List<Map<String, String>> getWebElement(String url) {
		List<Map<String, String>> list = null;
		String html = null;
		if (StringUtils.isNotEmpty(url)) {
			html = getHtmlSoursceByUrlOfGzip(url);
		}

		// 解析网页源代码
		Document document = Jsoup.parse(html);
		if (document == null) {
			return list;
		}
		list = new ArrayList<Map<String, String>>();
		/* 导 import org.jsoup.nodes.Element; */
		// 获取网页指定元素的外层div元素
		Element elm = document.getElementById("table_cont"); // 赛事列表
		if (elm == null) {
			return list;
		}

		Elements trs = elm.getElementsByTag("tr");
		for (Element tr : trs) {
			String id = tr.id();
			Map<String, String> map = new HashMap<String, String>();
			if (id != null) {
				map.put("cid", id); // Cid
				Elements tds = tr.getElementsByTag("td");
				for (Element td : tds) {
					// 赔率公司名称
					if (td.hasClass("tb_plgs")) {
						// System.out.println(td.attr("title"));
						map.put("name", td.attr("title"));
					}
					Elements ctds = td.getElementsByClass("pl_table_data");
					int length = ctds.size();
					int z = 0;
					for (Element ctd : ctds) {
						Elements dtds = ctd.getElementsByTag("td");
						int len = dtds.size();
						if (z == 0 && z <= length) {
							for (int i = 0; i < len; i++) {
								String value = dtds.eq(i).text();
								if (StringUtils.isEmpty(value)) {
									continue;
								}
								System.out.println(id+"====="+i + "========" + value);
								switch (i) {
								case 0:
									map.put("jsop_win1", value);
									break;
								case 1:
									map.put("jsop_ping1", value);
									break;
								case 2:
									map.put("jsop_lose1", value);
									break;
								case 3:
									map.put("jsop_win2", value);
									break;
								case 4:
									map.put("jsop_ping2", value);
									break;
								case 5:
									map.put("jsop_lose2", value);
									break;
								}
							}

						} else if (z == 1 && z <= length) {
							for (int i = 0; i < len; i++) {
								String value = dtds.eq(i).text();
								if (StringUtils.isEmpty(value)) {
									continue;
								}
								System.out.println(i + "========" + value);
								switch (i) {
								case 0:
									map.put("jsgl_win1", value);
									break;
								case 1:
									map.put("jsgl_ping1", value);
									break;
								case 2:
									map.put("jsgl_lose1", value);
									break;
								case 3:
									map.put("jsgl_win2", value);
									break;
								case 4:
									map.put("jsgl_ping2", value);
									break;
								case 5:
									map.put("jsgl_lose2", value);
									break;
								case 6:
									map.put("jsgl_rehome1", value);
									break;
								case 7:
									map.put("jsgl_rehome2", value);
									break;
								}
							}
						} else if (z == 2 && z <= length) {

							for (int i = 0; i < len; i++) {
								String value = dtds.eq(i).text();
								if (StringUtils.isEmpty(value)) {
									continue;
								}
								System.out.println(i + "========" + value);
								switch (i) {
								case 0:
									map.put("jskl_win1", value);
									break;
								case 1:
									map.put("jskl_ping1", value);
									break;
								case 2:
									map.put("jskl_lose1", value);
									break;
								case 3:
									map.put("jskl_win2", value);
									break;
								case 4:
									map.put("jskl_ping2", value);
									break;
								case 5:
									map.put("jskl_lose2", value);
									break;
								}
							}
						}
					}
				}
			}
			list.add(map);
		}
		return list;
	}

	public static void main(String[] args) {
		String url1 = "http://odds.500.com/fenxi/ouzhi-441558.shtml";
		String url = "http://trade.500.com/jczq/";
		// List<HashMap<String, Object>> list = getPicElement(url, "utf-8");
		getWebElement(url1);
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

}
