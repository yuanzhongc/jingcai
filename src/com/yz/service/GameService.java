package com.yz.service;

import java.util.List;

import com.yz.bean.Game;

public interface GameService {

	public Game create(Game game);

	public Game findByCode(String code);

	public Game update(Game game);

	public List<Game> findByAll();

}
