package com.valtech.team18.service;

import java.util.List;

import com.valtech.team18.entity.OrderDetails;
import com.valtech.team18.entity.TruckDetails;

public interface TruckDetailsService {

	List<OrderDetails> getAllOrderD();

	List<TruckDetails> getAllTruckD();

	List<OrderDetails> getAllOrdersByDriverId(int id);

	List<TruckDetails> getPendingDriver();

	List<TruckDetails> getApprovedDriver();

	TruckDetails approvingDriver(int id);

	void deleteRejectedDriver(int id);
	

}