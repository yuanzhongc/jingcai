package com.yz.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * ClassName: BaiJiaOuPei 
 * @Description: 百家欧赔数据采集
 * @author yuanzhong
 * @date 2015年5月4日  下午9:39:49
 */
@Entity
@Table(name="BAIJIAOUPEIS")
public class BaiJiaOuPei implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_")
	private Integer id;
	//赛事ID
	@ManyToOne
	@JoinColumn(name="GAME_ID_")
	private Game game;
	//明细ID
	@Column(name="COM_ID_")
	private Integer com_id;
	//赔率公司名
	@Column(name="NAME_")
	private String name;
	
	//即时欧赔
	@Column(name="JSOP_WIN1_")
	private String jsop_win1;
	@Column(name="JSOP_WIN2_")
	private String jsop_win2;
	@Column(name="JSOP_PING1_")
	private String jsop_ping1;
	@Column(name="JSOP_PING2_")
	private String jsop_ping2;	
	@Column(name="JSOP_LOSE1_")
	private String jsop_LOSE1;
	@Column(name="JSOP_LOSE2_")
	private String jsop_LOSE2;
	
	//即时概率
	@Column(name="JSGL_WIN1_")
	private String jsgl_win1;
	@Column(name="JSGL_WIN2_")
	private String jsgl_win2;
	@Column(name="JSGL_PING1_")
	private String jsgl_ping1;
	@Column(name="JSGL_PING2_")
	private String jsgl_ping2;	
	@Column(name="JSGL_LOSE1_")
	private String jsgl_LOSE1;
	@Column(name="JSGL_LOSE2_")
	private String jsgl_LOSE2;
	//即时概率——返还率
	@Column(name="JSGL_rehome1_")
	private String jsgl_rehome1;
	@Column(name="JSGL_rehome2_")
	private String jsgl_rehome2;
	
	//即时凯利
	@Column(name="JSKL_WIN1_")
	private String jskl_win1;
	@Column(name="JSKL_WIN2_")
	private String jskl_win2;
	@Column(name="JSKL_PING1_")
	private String jskl_ping1;
	@Column(name="JSKL_PING2_")
	private String jskl_ping2;	
	@Column(name="JSKL_LOSE1_")
	private String jskl_LOSE1;
	@Column(name="JSKL_LOSE2_")
	private String jskl_LOSE2;
	
	@Column(name="CREATETIME_")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Integer getCom_id() {
		return com_id;
	}

	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJsop_win1() {
		return jsop_win1;
	}

	public void setJsop_win1(String jsop_win1) {
		this.jsop_win1 = jsop_win1;
	}

	public String getJsop_win2() {
		return jsop_win2;
	}

	public void setJsop_win2(String jsop_win2) {
		this.jsop_win2 = jsop_win2;
	}

	public String getJsop_ping1() {
		return jsop_ping1;
	}

	public void setJsop_ping1(String jsop_ping1) {
		this.jsop_ping1 = jsop_ping1;
	}

	public String getJsop_ping2() {
		return jsop_ping2;
	}

	public void setJsop_ping2(String jsop_ping2) {
		this.jsop_ping2 = jsop_ping2;
	}

	public String getJsop_LOSE1() {
		return jsop_LOSE1;
	}

	public void setJsop_LOSE1(String jsop_LOSE1) {
		this.jsop_LOSE1 = jsop_LOSE1;
	}

	public String getJsop_LOSE2() {
		return jsop_LOSE2;
	}

	public void setJsop_LOSE2(String jsop_LOSE2) {
		this.jsop_LOSE2 = jsop_LOSE2;
	}

	public String getJsgl_win1() {
		return jsgl_win1;
	}

	public void setJsgl_win1(String jsgl_win1) {
		this.jsgl_win1 = jsgl_win1;
	}

	public String getJsgl_win2() {
		return jsgl_win2;
	}

	public void setJsgl_win2(String jsgl_win2) {
		this.jsgl_win2 = jsgl_win2;
	}

	public String getJsgl_ping1() {
		return jsgl_ping1;
	}

	public void setJsgl_ping1(String jsgl_ping1) {
		this.jsgl_ping1 = jsgl_ping1;
	}

	public String getJsgl_ping2() {
		return jsgl_ping2;
	}

	public void setJsgl_ping2(String jsgl_ping2) {
		this.jsgl_ping2 = jsgl_ping2;
	}

	public String getJsgl_LOSE1() {
		return jsgl_LOSE1;
	}

	public void setJsgl_LOSE1(String jsgl_LOSE1) {
		this.jsgl_LOSE1 = jsgl_LOSE1;
	}

	public String getJsgl_LOSE2() {
		return jsgl_LOSE2;
	}

	public void setJsgl_LOSE2(String jsgl_LOSE2) {
		this.jsgl_LOSE2 = jsgl_LOSE2;
	}

	public String getJsgl_rehome1() {
		return jsgl_rehome1;
	}

	public void setJsgl_rehome1(String jsgl_rehome1) {
		this.jsgl_rehome1 = jsgl_rehome1;
	}

	public String getJsgl_rehome2() {
		return jsgl_rehome2;
	}

	public void setJsgl_rehome2(String jsgl_rehome2) {
		this.jsgl_rehome2 = jsgl_rehome2;
	}

	public String getJskl_win1() {
		return jskl_win1;
	}

	public void setJskl_win1(String jskl_win1) {
		this.jskl_win1 = jskl_win1;
	}

	public String getJskl_win2() {
		return jskl_win2;
	}

	public void setJskl_win2(String jskl_win2) {
		this.jskl_win2 = jskl_win2;
	}

	public String getJskl_ping1() {
		return jskl_ping1;
	}

	public void setJskl_ping1(String jskl_ping1) {
		this.jskl_ping1 = jskl_ping1;
	}

	public String getJskl_ping2() {
		return jskl_ping2;
	}

	public void setJskl_ping2(String jskl_ping2) {
		this.jskl_ping2 = jskl_ping2;
	}

	public String getJskl_LOSE1() {
		return jskl_LOSE1;
	}

	public void setJskl_LOSE1(String jskl_LOSE1) {
		this.jskl_LOSE1 = jskl_LOSE1;
	}

	public String getJskl_LOSE2() {
		return jskl_LOSE2;
	}

	public void setJskl_LOSE2(String jskl_LOSE2) {
		this.jskl_LOSE2 = jskl_LOSE2;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
