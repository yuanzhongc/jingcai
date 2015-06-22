package com.yz.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.bean.BaiJiaOuPei;
import com.yz.bean.Game;
import com.yz.service.BaiJiaOuPeiService;
import com.yz.service.GameService;
import com.yz.util.HtmlSource;
import com.yz.util.HtmlSourceOfGzip;
import com.yz.util.ImportExcel;
import com.yz.util.JsonResult;
import com.yz.util.YzDateUtil;

@Controller
public class GameControll {
	@Autowired
	public GameService gameService;
	@Autowired
	public BaiJiaOuPeiService baiJiaOuPeiService;

	public static final String WUBAI_WAN_FOOTBALL = "http://trade.500.com/jczq/";

	public JsonResult capture() {
		JsonResult json = new JsonResult(true);
		List<Map<String, String>> games = HtmlSource
				.getWebElement(WUBAI_WAN_FOOTBALL);
		for (Map<String, String> map : games) {
			String code = map.get("code");
			if (StringUtils.isEmpty(code)) {
				continue;
			}
			Game game = gameService.findByCode(code);
			if (game == null) {
				game = new Game();
			}
			game.setCode(map.get("code"));
			game.setName(map.get("name"));
			game.setGameDate(YzDateUtil.dateToString(map.get("gameDate"),
					"yyyy-MM-dd"));
			game.setBeginTime(YzDateUtil.dateToString(map.get("beginTime")));
			game.setEndTime(YzDateUtil.dateToString(map.get("endtime")));
			game.setHomeTeam(map.get("homeName"));
			game.setGuestTeam(map.get("guestName"));
			game.setWin1_odds(Float.valueOf(map.get("win1")));
			game.setWin2_odds(Float.valueOf(map.get("win2")));
			game.setPING1_odds(Float.valueOf(map.get("ping1")));
			game.setPING2_odds(Float.valueOf(map.get("ping2")));
			game.setLOSE1_odds(Float.valueOf(map.get("lose1")));
			game.setLOSE2_odds(Float.valueOf(map.get("lose2")));
			game.setOuzhi_url(map.get("ouzhi"));
			game.setYazhi_url(map.get("yazhi"));
			if (game.getId() == null) {
				gameService.create(game);
				json.msg("创建成功！");
			}else{
				gameService.update(game);
				json.msg("更新成功！");
			}
			createBaijia(game);
		}
		return json;
	}

	/**
	 * 
	 * @Description: 更新主数据
	 * @param @return
	 * @return JsonResult
	 * @throws
	 * @author yuanzhong
	 * @date 2015年5月4日 下午4:44:28
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonResult update() {
		return capture();
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public JsonResult list() {
		JsonResult json = new JsonResult(true);
		List<Map<String, Object>> list = null;
		List<Game> games = gameService.findByAll();
		if (games != null && games.size() > 0) {
			list = new ArrayList<Map<String, Object>>();
			for (Game game : games) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", game.getId());
				map.put("code", game.getCode());
				map.put("name", game.getName());
				map.put("date", game.getGameDate());
				map.put("endTime",YzDateUtil.dateToString(game.getEndTime(),null));
				map.put("homeTeam", game.getHomeTeam());
				map.put("guestTeam", game.getGuestTeam());
				map.put("win1", new DecimalFormat("#.00").format(game.getWin1_odds()));
				map.put("win2", new DecimalFormat("#.00").format(game.getWin2_odds()));
				map.put("ping1", new DecimalFormat("#.00").format(game.getPING1_odds()));
				map.put("ping2", new DecimalFormat("#.00").format(game.getPING2_odds()));
				map.put("lose1", new DecimalFormat("#.00").format(game.getLOSE1_odds()));
				map.put("lose2", new DecimalFormat("#.00").format(game.getLOSE2_odds()));
				map.put("ouzhi", game.getOuzhi_url());
				map.put("yazhi", game.getYazhi_url());
				list.add(map);
			}
		}
		return json.put("games", list);
	}

	/**
	 * 
	 * @Description: 解析并保存百家欧赔数据
	 * @param @param game   
	 * @return void  
	 * @throws
	 * @author yuanzhong
	 * @date 2015年5月4日 下午10:11:16
	 */
	public void createBaijia(Game game){
		String file = "C:\\Users\\zhong\\Downloads\\FK乌法VS莫尔多维亚(俄超)欧洲数据.xls";
		if(game != null){
			//List<Map<String, String>> list = HtmlSourceOfGzip.getWebElement(game.getOuzhi_url());
			List<Map<String, String>> list = ImportExcel.getAllByExcel(file);
			if(list == null || list.size() == 0){
				return;
			}
			//先清空历史数据
			List<BaiJiaOuPei> baijias = baiJiaOuPeiService.findByGame(game);
			if(baijias != null && baijias .size() > 0){
				for (BaiJiaOuPei baij : baijias) {
					baiJiaOuPeiService.delete(baij);
				}
			}
			
			for (Map<String, String> map : list) {
				//String cid = map.get("cid").replaceAll("[^-+.\\d]", "");
				//if(StringUtils.isEmpty(cid)){
				//	continue;
				//}
				//Integer id = Integer.parseInt(cid);
				Integer id = 0;
				BaiJiaOuPei baiJia = baiJiaOuPeiService.findByGameAndId(game,id);
				if(baiJia == null){
					baiJia = new BaiJiaOuPei();
				}
				baiJia.setCom_id(id); //公司id
				baiJia.setGame(game);
				baiJia.setName(map.get("name"));
				//即时欧赔--------------
				baiJia.setJsop_win1(map.get("jsop_win1"));
				baiJia.setJsop_win2(map.get("jsop_win2"));
				baiJia.setJsop_ping1(map.get("jsop_ping1"));
				baiJia.setJsop_ping2(map.get("jsop_ping2"));
				baiJia.setJsop_LOSE1(map.get("jsop_lose1"));
				baiJia.setJsop_LOSE2(map.get("jsop_lose2"));
				//即时概率--------------
				baiJia.setJsgl_win1(map.get("jsgl_win1"));
				baiJia.setJsgl_win2(map.get("jsgl_win2"));
				baiJia.setJsgl_ping1(map.get("jsgl_ping1"));
				baiJia.setJsgl_ping2(map.get("jsgl_ping2"));
				baiJia.setJsgl_LOSE1(map.get("jsgl_lose1"));
				baiJia.setJsgl_LOSE2(map.get("jsgl_lose2"));
				//即时返还率--------------
				baiJia.setJsgl_rehome1(map.get("jsgl_rehome1"));
				baiJia.setJsgl_rehome2(map.get("jsgl_rehome2"));
				//即时凯利--------------
				baiJia.setJskl_win1(map.get("jskl_win1"));
				baiJia.setJskl_win2(map.get("jskl_win2"));
				baiJia.setJsgl_ping1(map.get("jskl_ping1"));
				baiJia.setJskl_ping2(map.get("jskl_ping2"));
				baiJia.setJskl_LOSE1(map.get("jskl_lose1"));
				baiJia.setJsgl_LOSE2(map.get("jskl_lose2"));
				baiJia = baiJiaOuPeiService.create(baiJia);
				
			}
		}
	}
	
	
}
