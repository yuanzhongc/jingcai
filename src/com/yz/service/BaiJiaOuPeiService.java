package com.yz.service;

import java.util.List;

import com.yz.bean.BaiJiaOuPei;
import com.yz.bean.Game;

public interface BaiJiaOuPeiService {

	public BaiJiaOuPei create(BaiJiaOuPei baiJiaOuPei);

	public BaiJiaOuPei findByCode(String code);

	public BaiJiaOuPei update(BaiJiaOuPei baiJiaOuPei);

	public List<BaiJiaOuPei> findByAll();

	public BaiJiaOuPei findByGameAndId(Game game, Integer id);

	public void delete(BaiJiaOuPei baij);

	public List<BaiJiaOuPei> findByGame(Game game);

}
