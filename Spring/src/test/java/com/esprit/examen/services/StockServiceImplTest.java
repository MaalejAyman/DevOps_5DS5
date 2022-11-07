package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.StockRequestModel;
import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockServiceImpl stockService;

	private Stock s1;
	private Stock s2;
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		this.s1 = new Stock();
		this.s1.setIdStock(0L);
		this.s1.setQte(100);
		this.s1.setLibelleStock("Stock 1");
		this.s2 = new Stock();
		this.s2.setIdStock(1L);
		this.s2.setQte(200);
		this.s2.setLibelleStock("Stock 2");
		this.modelMapper = new ModelMapper();
	}

	@Test
	public void testAddStock() {
		init();
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		StockRequestModel srm=modelMapper.map(s1, StockRequestModel.class);
		Stock snew=stockService.addStock(srm);
		assertNotNull(snew);
		assertThat(snew.getQte()).isEqualTo(100);
	}
	@Test
	public void getStocks() {
		init();
		List<Stock> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		when(stockRepository.findAll()).thenReturn(list);
		List<Stock> Stocks = stockService.retrieveAllStocks();
		assertEquals(2, Stocks.size());
		assertNotNull(Stocks);
	}
	
	@Test
	public void getStockById() {
		init();
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		StockRequestModel srm=modelMapper.map(s1, StockRequestModel.class);
		Stock snew=stockService.addStock(srm);
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		Stock existingStock = stockService.retrieveStock(snew.getIdStock());
		assertNotNull(existingStock);
		assertThat(existingStock.getIdStock()).isNotNull();
	}
	
	@Test
	public void updateStock() {
		init();
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		s1.setLibelleStock("Salut");
		StockRequestModel srm=modelMapper.map(s1, StockRequestModel.class);
		Stock exisitingStock = stockService.updateStock(srm);
		
		assertNotNull(exisitingStock);
		assertEquals("Salut", exisitingStock.getLibelleStock());
	}
	
	@Test
	public void deleteStock() {
		init();
		Long StockId = 1L;
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		doNothing().when(stockRepository).deleteById(anyLong());
		stockService.deleteStock(StockId);
		verify(stockRepository, times(1)).deleteById(anyLong());
	}
}
