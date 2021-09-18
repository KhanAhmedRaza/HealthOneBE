package com.ost.ho.daos.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ost.ho.daos.DashboardDao;
import com.ost.ho.model.Country;
import com.ost.ho.model.Indicator;
import com.ost.ho.model.Manufacturer;
import com.ost.ho.model.Outcomes;
import com.ost.ho.pojo.AgeGroup;
import com.ost.ho.util.QueryBean;

@Repository("dashboardDao")
public class DashBoardDaoImpl implements DashboardDao {
	@Autowired
	JdbcTemplate hoJdbcTemplate;

	@Autowired 
	QueryBean queryBean;
	 

	@Override
	public List<AgeGroup> getAgeGroups() {
		
		String ageGrpSelect = queryBean.getQuery("dashboardDao.getAllAgeGrp"); 
		List<AgeGroup> ageGroups = hoJdbcTemplate.query(ageGrpSelect, (rs, rownum) -> {
			AgeGroup ageGrp = new AgeGroup();
			ageGrp.setAgeGrp(rs.getString("age_grp"));
			ageGrp.setAgeGrpDesc(rs.getString("Age_grp_desc"));
			return ageGrp;
			
		});
		return ageGroups;
	}


	@Override
	public List<Country> getCountries() {
		String sql_countries = queryBean.getQuery("dashboardDao.getAllCountires"); 
		List<Country> countries = hoJdbcTemplate.query(sql_countries, (rs, rownum) -> {
			Country country = new Country();
			country.setCountryCode(rs.getString("country"));
			country.setCountryName(rs.getString("country_name"));
			return country;
			
		});
		return countries;
	}


	@Override
	public List<Manufacturer> getManufacturers() {
		String sql_Manu = queryBean.getQuery("dashboardDao.getManufacturer"); 
		List<Manufacturer> manufacturers = hoJdbcTemplate.query(sql_Manu, (rs, rownum) -> {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManuName(rs.getString("mfr_name"));
			return manufacturer;
			
		});
		return manufacturers;
	}


	@Override
	public List<Indicator> getIndications() {
		String sql_Indi = queryBean.getQuery("dashboardDao.getIndicators"); 
		List<Indicator> indicators = hoJdbcTemplate.query(sql_Indi, (rs, rownum) -> {
			Indicator indicator = new Indicator();
			indicator.setIndicationPt(rs.getString("indi_pt"));
			return indicator;
			
		});
		return indicators;
	}


	@Override
	public List<Outcomes> getOutcomes() {
		String sql_outcomes = queryBean.getQuery("dashboardDao.getOutcomes"); 
		List<Outcomes> outcomes = hoJdbcTemplate.query(sql_outcomes, (rs, rownum) -> {
			Outcomes outcome = new Outcomes();
			outcome.setOutcomeCode(rs.getNString("out_cod"));
			outcome.setOutcomeDesc(rs.getString("out_cod_desc"));
			return outcome;
			
		});
		return outcomes;
	}

}
