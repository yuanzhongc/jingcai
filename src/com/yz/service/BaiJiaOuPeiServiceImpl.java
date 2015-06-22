package com.yz.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.bean.BaiJiaOuPei;
import com.yz.bean.Game;
import com.yz.dao.BaiJiaOuPeiDao;

@Service
public class BaiJiaOuPeiServiceImpl implements BaiJiaOuPeiService {
	@Autowired
	public BaiJiaOuPeiDao baiJiaOuPeiDao;

	@Override
	public BaiJiaOuPei create(BaiJiaOuPei baiJiaOuPei) {
		if (baiJiaOuPei != null) {
			baiJiaOuPei = baiJiaOuPeiDao.insert(baiJiaOuPei);
		}
		return null;
	}

	@Override
	public BaiJiaOuPei findByCode(String code) {
		BaiJiaOuPei baiJiaOuPei = null;
		if (StringUtils.isNotEmpty(code)) {
			baiJiaOuPei = baiJiaOuPeiDao.findByCode(code);
		}
		return baiJiaOuPei;
	}

	@Override
	public BaiJiaOuPei update(BaiJiaOuPei baiJiaOuPei) {
		if (baiJiaOuPei != null) {
			baiJiaOuPei = baiJiaOuPeiDao.update(baiJiaOuPei);
		}
		return null;
	}

	@Override
	public List<BaiJiaOuPei> findByAll() {
		return baiJiaOuPeiDao.QueryByAll();
	}

	@Override
	public BaiJiaOuPei findByGameAndId(Game game, Integer id) {
		if(game != null && id > 0){
			return baiJiaOuPeiDao.QueryByGameAndId(game,id);
		}
		return null;
	}

	@Override
	public void delete(BaiJiaOuPei baiJiaOuPei) {
		if(baiJiaOuPei != null){
			baiJiaOuPeiDao.delete(baiJiaOuPei);
		}
		
	}

	@Override
	public List<BaiJiaOuPei> findByGame(Game game) {
		if(game != null){
			return baiJiaOuPeiDao.findByGame(game);
		}
		return null;
	}

}
