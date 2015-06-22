package com.yz.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yz.bean.Game;

@Repository
@Transactional
public class GameDao extends BaseDao<Game, Integer> {

	public Game insert(Game game) {
		if (game != null) {
			game.setCreateTime(new Date());
			getSession().save(game);
			return game;
		}
		return null;
	}

	public Game findByCode(String code) {
		if (StringUtils.isNotEmpty(code)) {
			String hql = "SELECT g From Game g where code = ?";
			Query query = getSession().createQuery(hql).setParameter(0, code);
			return (Game) query.uniqueResult();
		}
		return null;
	}

	public Game update(Game game) {
		if (game != null) {
			getSession().update(game);
			return game;
		}
		return null;
	}

	public List<Game> QueryByAll() {
		String hql = "SELECT g From Game g where 1 = 1";
		Query query = getSession().createQuery(hql);
		return (List<Game>) query.list();
	}

}
