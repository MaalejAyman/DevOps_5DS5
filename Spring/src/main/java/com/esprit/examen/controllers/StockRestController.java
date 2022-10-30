package com.esprit.examen.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.services.IStockService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des stocks")
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockRestController {

	@Autowired
	IStockService stockService;
	
	public class StockRequestModel {

		private Long idStock;
		private String libelleStock;
		private String libelle;
		private Integer qte;
		private Integer qteMin;
		}
	
	@GetMapping("/retrieve-all-stocks")
	@ResponseBody
	public List<Stock> getStocks() {
		return stockService.retrieveAllStocks();
	}

	
	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public Stock retrieveStock(@PathVariable("stock-id") Long stockId) {
		return stockService.retrieveStock(stockId);
	}

	
	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody StockRequestModel StockModel) {
		
		Stock s = new Stock();
		s.setIdStock(StockModel.idStock);
		s.setLibelleStock(StockModel.libelleStock);
		s.setQte(StockModel.qte);
		s.setQteMin(StockModel.qteMin);
		
		return stockService.addStock(s);
	}

	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removeStock(@PathVariable("stock-id") Long stockId) {
		stockService.deleteStock(stockId);
	}

	
	@PutMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);
	}
}