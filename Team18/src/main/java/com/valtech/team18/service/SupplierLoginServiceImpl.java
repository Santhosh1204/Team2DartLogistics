package com.valtech.team18.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.valtech.team18.entity.PendingSupplier;
import com.valtech.team18.entity.SupplierDetails;
import com.valtech.team18.repo.SupplierDetailsRepo;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class SupplierLoginServiceImpl implements SupplierLoginService {
	
	@Autowired
	private SupplierDetailsRepo supplierDetailsRepo;
	
	@Autowired
	private MailMessage mailMessage;
	
	public static String getRandomNumberString() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
	}
	

	//Login Validation by checking given username and password in the database
	@Override
	public boolean loginvalidation(String email,String password) throws NullPointerException{
		
		try{
		SupplierDetails sup=supplierDetailsRepo.findByEmailAndApprovedTrue(email);
		
		if((email.equals(sup.getEmail())) && (password.equals(sup.getSuppPassword()))){
			System.out.println("test5");
			return true;
		}
		
		return false;
		}
		
		catch(NullPointerException n){
			return false;
		}
		
		
	}

	@Override
	public int getIdFromEmail(String email){
		return supplierDetailsRepo.findByEmail(email).getSuppId();
	}

	@Override
	public boolean checkmail(String email) {
		SupplierDetails sd=supplierDetailsRepo.findByEmail(email);
		if(sd.getEmail()!=null){
			String pass=getRandomNumberString();
			sd.setOtp(pass);
			supplierDetailsRepo.save(sd);
			mailMessage.sendOTP(sd.getEmail(),pass,"Supplier");
			return true;
			
		}
		return false;
	}


	@Override
	public boolean checkOTP(int id,String otp) {
		SupplierDetails sd=supplierDetailsRepo.findBySuppId(id);
		if(otp.equals(sd.getOtp()))
			return true;
		
		return false;
	}


	@Override
	public void changePassword(int id, String password) {
		SupplierDetails sd=supplierDetailsRepo.findBySuppId(id);
		sd.setSuppPassword(password);
		sd.setOtp(null);
		supplierDetailsRepo.save(sd);
		mailMessage.successfulPasswordChange(sd.getEmail(),"Supplier");
		
		
	}
	
	

//	// Save new supplier details
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public SupplierDetails saveNew(PendingSupplier ps){
//		String username=ps.getUsername();
//		String password=ps.getPassword();
//		String fromAdd=ps.getFromAddress();
//		String suppConDe=ps.getSuppContactDetails();
//		SupplierDetails sl=new SupplierDetails(username, password,fromAdd,suppConDe);
//		 return supplierDetailsRepo.save(sl);
//	}
	
}
