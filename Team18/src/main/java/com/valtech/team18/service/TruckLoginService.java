package com.valtech.team18.service;

//import com.valtech.team18.entity.PendingDriver;
//import com.valtech.team18.entity.TruckDetails;

public interface TruckLoginService {

	boolean loginvalidation(String username, String password) throws NullPointerException;

	int getIdFromEmail(String email);

	boolean checkmail(String email);
	
	
//	TruckDetails saveNew(PendingDriver pd);

}