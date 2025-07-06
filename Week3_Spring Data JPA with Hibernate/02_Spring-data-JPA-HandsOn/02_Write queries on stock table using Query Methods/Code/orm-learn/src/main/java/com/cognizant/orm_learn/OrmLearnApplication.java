package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

	@Autowired
	private StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date start = sdf.parse("2019-09-01");
		Date end = sdf.parse("2019-09-30");
		List<Stock> fbStocks = stockRepository.findByCodeAndDateBetween("FB", start, end);
		System.out.println("\nFacebook Stock - Sep 2019");
		fbStocks.forEach(System.out::println);

		List<Stock> googStocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
		System.out.println("\nGoogle Stock - Price > 1250");
		googStocks.forEach(System.out::println);

		List<Stock> topVolumes = stockRepository.findTop3ByOrderByVolumeDesc();
		System.out.println("\nTop 3 Volume Dates");
		topVolumes.forEach(System.out::println);

		List<Stock> lowestNetflix = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
		System.out.println("\nNetflix Lowest 3 Close Prices");
		lowestNetflix.forEach(System.out::println);
	}
}
