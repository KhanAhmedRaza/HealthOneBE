package com.ost.ho.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ost.ho.model.Country;
import com.ost.ho.model.Indicator;
import com.ost.ho.model.Manufacturer;
import com.ost.ho.model.Outcomes;
import com.ost.ho.pojo.AgeGroup;

@Repository
public interface DashboardDao {
	public List<AgeGroup> getAgeGroups();
	public List<Country> getCountries();
	public List<Manufacturer> getManufacturers();
	public List<Indicator> getIndications();
	public List<Outcomes> getOutcomes();
	
}
