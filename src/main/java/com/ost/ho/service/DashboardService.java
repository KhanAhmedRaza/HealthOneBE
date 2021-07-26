package com.ost.ho.service;


import org.springframework.stereotype.Service;

import com.ost.ho.model.DashboardFilter;
import com.ost.ho.model.DashboardResult;


@Service
public interface DashboardService {
	public DashboardResult getInitialResult();
	public DashboardResult getFilteredResult(DashboardFilter dashboardResult);
}
 