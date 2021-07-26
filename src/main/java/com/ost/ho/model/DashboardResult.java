package com.ost.ho.model;

import java.util.List;

import com.ost.ho.pojo.AgeGroup;

public class DashboardResult {
	
	private List<AgeGroup> ageGrps;
	private DashboardImages imageResult;
	private List<Country> countries;
	private List<Drug> drugs;
	private List<Manufacturer> manufatureres;
	private List<Indicator> indicators;
	private List<Outcomes> outcomes;
	
	private boolean filteredData;
	
	
	
	public List<AgeGroup> getAgeGrps() {
		return ageGrps;
	}
	public void setAgeGrps(List<AgeGroup> ageGrps) {
		this.ageGrps = ageGrps;
	}
	public DashboardImages getImageResult() {
		return imageResult;
	}
	public void setImageResult(DashboardImages imageResult) {
		this.imageResult = imageResult;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	public List<Drug> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}
	public List<Manufacturer> getManufatureres() {
		return manufatureres;
	}
	public void setManufatureres(List<Manufacturer> manufatureres) {
		this.manufatureres = manufatureres;
	}
	public List<Indicator> getIndicators() {
		return indicators;
	}
	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
	public List<Outcomes> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcomes> outcomes) {
		this.outcomes = outcomes;
	}
	public boolean isFilteredData() {
		return filteredData;
	}
	public void setFilteredData(boolean filteredData) {
		this.filteredData = filteredData;
	}
	

}
