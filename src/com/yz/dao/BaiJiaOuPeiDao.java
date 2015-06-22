package com.yz.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yz.bean.BaiJiaOuPei;
import com.yz.bean.Game;

@Repository
@Transactional
public class BaiJiaOuPeiDao extends BaseDao<BaiJiaOuPei, Integer> {

	public BaiJiaOuPei insert(BaiJiaOuPei baiJiaOuPei) {
		if (baiJiaOuPei != null) {
			baiJiaOuPei.setCreateTime(new Date());
			getSession().save(baiJiaOuPei);
			return baiJiaOuPei;
		}
		return null;
	}

	public BaiJiaOuPei findByCode(String code) {
		if (StringUtils.isNotEmpty(code)) {
			String hql = "SELECT g From BaiJiaOuPei g where code = ?";
			Query query = getSession().createQuery(hql).setParameter(0, code);
			return (BaiJiaOuPei) query.uniqueResult();
		}
		return null;
	}

	public BaiJiaOuPei update(BaiJiaOuPei baiJiaOuPei) {
		if (baiJiaOuPei != null) {
			getSession().update(baiJiaOuPei);
			return baiJiaOuPei;
		}
		return null;
	}

	public List<BaiJiaOuPei> QueryByAll() {
		String hql = "SELECT g From BaiJiaOuPei g where 1 = 1";
		Query query = getSession().createQuery(hql);
		return (List<BaiJiaOuPei>) query.list();
	}

	public BaiJiaOuPei QueryByGameAndId(Game game, Integer id) {
		if(game != null && id > 0){
			String hql = "SELECT g From BaiJiaOuPei g where game.id = ? and com_id = ?";
			Query query = getSession().createQuery(hql)
					.setInteger(0, game.getId())
					.setInteger(1, id);
			return (BaiJiaOuPei) query.uniqueResult();
		}
		return null;
	}

	public void delete(BaiJiaOuPei baiJiaOuPei) {
		if(baiJiaOuPei != null){
			getSession().delete(baiJiaOuPei);
		}
		
	}

	public List<BaiJiaOuPei> findByGame(Game game) {
		if(game != null){
			String hql = "SELECT g From BaiJiaOuPei g where game.id = ?";
			Query query = getSession().createQuery(hql)
					.setInteger(0, game.getId());
			return (List<BaiJiaOuPei>) query.list();
		}
		return null;
	}

}
