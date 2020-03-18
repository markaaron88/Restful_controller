package cst438hw2.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438hw2.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	@SuppressWarnings("null")
	public CityInfo getCityInfo(String cityName) {
		
		  
		
		List<City> getCity = cityRepository.findByName(cityName);
		
		if(!getCity.isEmpty()) {
			City city = getCity.get(0);
			Country country = countryRepository.findByCode(city.getCountryCode());
			TempAndTime weather = weatherService.getTempAndTime(city.getName());
			Double temp = 32 + (weather.temp - 273.15) * (9.0/5.0);
			LocalDateTime date = LocalDateTime.ofEpochSecond((weather.time + weather.timezone), 0, ZoneOffset.UTC);
			CityInfo iCity = new CityInfo(city, city.getCountryCode(),weather.temp, date.toString());
		return iCity;
		}
		
		return null;
	
	}

}
