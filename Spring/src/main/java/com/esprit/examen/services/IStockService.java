package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.StockRequestModel;

public interface IStockService {

	List<Stock> retrieveAllStocks();

	Stock addStock(StockRequestModel s);

	void deleteStock(Long id);

	Stock updateStock(StockRequestModel u);

	Stock retrieveStock(Long id);

	String retrieveStatusStock();
}
