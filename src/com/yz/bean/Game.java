package com.yz.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * ClassName: Game
 * 
 * @Description: 赛事场次信息
 * @author yuanzhong
 * @date 2015年5月4日
 */
@Entity
@Table(name = "GAMES")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_")
	private Integer id;
	/* 数据来源网站 */
	@Column(name = "WEB_")
	private String web;

	@Column(name = "CODE_")
	private String code;

	@Column(name = "NAME_")
	private String name;
	/* 场次 */
	@Column(name = "ROUND")
	private String round;
	/* 比赛日期 */
	@Column(name = "GAMEDATE_")
	private Date gameDate;
	/* 开始时间 */
	@Column(name = "BEGINTIME_")
	private Date beginTime;
	/* 截止时间 */
	@Column(name = "ENDTIME_")
	private Date endTime;
	/* 主队 */
	@Column(name = "HOMETEAM_")
	private String homeTeam;
	/* 客队 */
	@Column(name = "GUESTTEAM_")
	private String guestTeam;
	/* 胜赔率1 */
	@Column(name = "WIN1_ODDS")
	private Float win1_odds;
	/* 胜赔率2 */
	@Column(name = "WIN2_ODDS")
	private Float win2_odds;
	/* 平赔率1 */
	@Column(name = "PING1_ODDS")
	private Float PING1_odds;
	/* 平赔率2 */
	@Column(name = "PING2_ODDS")
	private Float PING2_odds;
	/* 负赔率1 */
	@Column(name = "LOSE1_ODDS")
	private Float LOSE1_odds;
	/* 负赔率2 */
	@Column(name = "LOSE2_ODDS")
	private Float LOSE2_odds;
	/* 亚资 */
	@Column(name = "YAZHI_URL_")
	private String yazhi_url;
	/* 欧资 */
	@Column(name = "OUZHIz_URL_")
	private String ouzhi_url;

	@Column(name = "CREATETIME_")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public Float getWin1_odds() {
		return win1_odds;
	}

	public void setWin1_odds(Float win1_odds) {
		this.win1_odds = win1_odds;
	}

	public Float getWin2_odds() {
		return win2_odds;
	}

	public void setWin2_odds(Float win2_odds) {
		this.win2_odds = win2_odds;
	}

	public Float getPING1_odds() {
		return PING1_odds;
	}

	public void setPING1_odds(Float pING1_odds) {
		PING1_odds = pING1_odds;
	}

	public Float getPING2_odds() {
		return PING2_odds;
	}

	public void setPING2_odds(Float pING2_odds) {
		PING2_odds = pING2_odds;
	}

	public Float getLOSE1_odds() {
		return LOSE1_odds;
	}

	public void setLOSE1_odds(Float lOSE1_odds) {
		LOSE1_odds = lOSE1_odds;
	}

	public Float getLOSE2_odds() {
		return LOSE2_odds;
	}

	public void setLOSE2_odds(Float lOSE2_odds) {
		LOSE2_odds = lOSE2_odds;
	}

	public String getYazhi_url() {
		return yazhi_url;
	}

	public void setYazhi_url(String yazhi_url) {
		this.yazhi_url = yazhi_url;
	}

	public String getOuzhi_url() {
		return ouzhi_url;
	}

	public void setOuzhi_url(String ouzhi_url) {
		this.ouzhi_url = ouzhi_url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
