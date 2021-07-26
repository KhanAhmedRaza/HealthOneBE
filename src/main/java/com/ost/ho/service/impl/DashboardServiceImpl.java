package com.ost.ho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import com.ost.ho.daos.DashboardDao;
import com.ost.ho.daos.DrugSearchDAO;
import com.ost.ho.model.DashboardResult;
import com.ost.ho.model.DashboardFilter;
import com.ost.ho.model.DashboardImages;
import com.ost.ho.service.DashboardService;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardDao dashboardDao;
	@Autowired
	DrugSearchDAO drugSearchDAO;
	public static final String demoCharts = "http://ec2-18-220-88-231.us-east-2.compute.amazonaws.com:3001/getDemographicCharts";
	public static final String timeSeries = "http://ec2-18-220-88-231.us-east-2.compute.amazonaws.com:5001/getTimeSeriesForecast";
	public static final String timeSeriesFilter =  "http://ec2-3-143-16-148.us-east-2.compute.amazonaws.com:5444/timeseries";
	//public static final String timeSeriesFilter ="http://35.185.31.39:5444/timeseries";

	// public static final String NEWDTREEURL=
	// "https://08a16f63-ae44-4f18-9e35-477faa971c93.mock.pstmn.io/getDemographic";
	// public static final String timeSeries=
	// "http://localhost:8082/OSTF/timeseries";
	@Override
	public DashboardResult getInitialResult() {
		// drugSearch.setMax_depth("3");
		DashboardResult result = new DashboardResult();
		result.setAgeGrps(dashboardDao.getAgeGroups());
		result.setCountries(dashboardDao.getCountries());
		result.setDrugs(drugSearchDAO.getaLLDrug());
		result.setManufatureres(dashboardDao.getManufacturers());
		result.setIndicators(dashboardDao.getIndications());
		result.setOutcomes(dashboardDao.getOutcomes());

		HttpEntity<DashboardImages> imageResult = null;
		DashboardImages images = new DashboardImages();
		List<String> imagesList = new ArrayList<>();

		
		  try { 
			  imageResult = connectToDemographicRestService(imageResult);
		  result.setImageResult(imageResult.getBody());
		  imagesList.addAll(imageResult.getBody().getImages());
		  images.setImages(imagesList); } catch (Exception ex) {
		  System.out.println("--" + ex); }
		  
		  try { 
			  imageResult = connectToTimeSeriesRestService(imageResult);
		  result.setImageResult(imageResult.getBody());
		  images.setImg(imageResult.getBody().getImg());
		  imagesList.add(imageResult.getBody().getImg()); images.setImages(imagesList);
		  } catch (Exception ex) { System.out.println("--" + ex); }
		 
		result.setImageResult(images);
		result.setFilteredData(false) ;
		/*
		 * //List<String> imagesList = new ArrayList<>();
		 * imagesList.add(images.getImg()); imagesList.addAll(images.getImages());
		 * images.setImgList(images);
		 */
		return result;
	}

	private HttpEntity<DashboardImages> connectToDemographicRestService(HttpEntity<DashboardImages> imgResult) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(demoCharts);
		HttpEntity<DashboardImages> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		// HttpEntity<DashboardImages> response =
		// restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
		// entity, DashboardImages.class);
		imgResult = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity,
				DashboardImages.class);
		return imgResult;
	}

	private HttpEntity<DashboardImages> connectToTimeSeriesRestService(HttpEntity<DashboardImages> imgResult) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(timeSeries);
		HttpEntity<DashboardImages> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		// HttpEntity<DashboardImages> response =
		// restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
		// entity, DashboardImages.class);
		imgResult = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity,
				DashboardImages.class);
		return imgResult;
	}

	@Override
	public DashboardResult getFilteredResult(DashboardFilter dashboardFilter) {
		DashboardResult dashboardResult = new DashboardResult();
		  dashboardResult.setAgeGrps(dashboardDao.getAgeGroups());
		  dashboardResult.setCountries(dashboardDao.getCountries());
		  dashboardResult.setDrugs(drugSearchDAO.getaLLDrug());
		  dashboardResult.setManufatureres(dashboardDao.getManufacturers());
		  dashboardResult.setIndicators(dashboardDao.getIndications());
		  dashboardResult.setOutcomes(dashboardDao.getOutcomes());
		 
		HttpEntity<DashboardImages> imageResult = null;
		DashboardImages images = new DashboardImages();
		List<String> imagesList = new ArrayList<>();
		try {
			imageResult = connectToTimeSeriesFilterService(imageResult,dashboardFilter);
			//result.setImageResult(imageResult.getBody());
			images.setImg(imageResult.getBody().getImg());
			images.setLink(imageResult.getBody().getLink());
			//imagesList.add(imageResult.getBody().getImg());
			imagesList.add(imageResult.getBody().getLink());
			images.setImages(imagesList);
		} catch (Exception ex) {
			System.out.println("--" + ex);
		}
		dashboardResult.setFilteredData(true) ;
		dashboardResult.setImageResult(images);
		return dashboardResult;
	}
	
	private HttpEntity<DashboardImages> connectToTimeSeriesFilterService(HttpEntity<DashboardImages> imgResult, DashboardFilter dashboardFilter) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(timeSeriesFilter);
		/*
		 * dashboardFilter JSONObject object = new JSONObject();
		 
		object.append("gender", "F").append("ageRange", "10-11").append("drug", "LIPITOR");**/
		HttpEntity<DashboardFilter> entity = new HttpEntity<DashboardFilter>(dashboardFilter,headers);
		
		RestTemplate restTemplate = new RestTemplate();
		// HttpEntity<DashboardImages> response =
		// restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
		// entity, DashboardImages.class);
		imgResult = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity,
				DashboardImages.class);
		return imgResult;
	}
}
