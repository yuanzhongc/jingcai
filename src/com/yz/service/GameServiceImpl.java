package com.yz.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.bean.Game;
import com.yz.dao.GameDao;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	public GameDao gameDao;

	@Override
	public Game create(Game game) {
		if (game != null) {
			game = gameDao.insert(game);
		}
		return null;
	}

	@Override
	public Game findByCode(String code) {
		Game game = null;
		if (StringUtils.isNotEmpty(code)) {
			game = gameDao.findByCode(code);
		}
		return game;
	}

	@Override
	public Game update(Game game) {
		if (game != null) {
			game = gameDao.update(game);
		}
		return null;
	}

	@Override
	public List<Game> findByAll() {
		return gameDao.QueryByAll();
	}

}
