package com.valtech.team18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.valtech.team18.entity.SupplierDetails;
import com.valtech.team18.entity.TruckDetails;
import com.valtech.team18.repo.SupplierDetailsRepo;
import com.valtech.team18.repo.TruckDetailsRepo;
import com.valtech.team18.service.AdminLoginService;
import com.valtech.team18.service.AdminService;
import com.valtech.team18.service.NewOrderService;
import com.valtech.team18.service.SupplierLoginService;
import com.valtech.team18.service.SupplierService;
import com.valtech.team18.service.TruckDetailsService;
import com.valtech.team18.service.TruckLoginService;


@SpringBootTest
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc
public class DartExpressLogisticsLoginAndRegisterTests {
	
	@Autowired
	  private MockMvc mvc;
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private NewOrderService newOrderService;
	
	@Autowired
	private SupplierLoginService supplierLoginService;
	
	@Autowired
	private SupplierDetailsRepo supplierDetailsRepo;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private TruckLoginService truckLoginService;
	
	@Autowired
	private TruckDetailsRepo truckDetailsRepo;
	
	@Autowired
	private TruckDetailsService truckDetailsService;
	
	 @Test
     public void testAdminLoginValidation() throws Exception{
	       assertEquals(true, adminLoginService.loginvalidation("admin@gmail.com","admin"));
	       assertEquals(false, adminLoginService.loginvalidation("admin","user"));
     }
	 
//	 @Test
//	 public void AdminOrderDetails() throws Exception{
//			assertEquals(adminService.getAllOrderD().size(), adminService.getAllOrderD().size());
//			assertEquals(adminService.getAllSuppplierD().size(), adminService.getAllSuppplierD().size());
//			assertEquals(adminService.getAllTruckD().size(), adminService.getAllTruckD().size());
//	  }
	 
	 @Test
     public void testSupplierLoginValidation() throws Exception{
	       assertEquals(true, supplierLoginService.loginvalidation("santhoshkumara1204@gmail.com","$Santhu12"));
	       assertEquals(false, supplierLoginService.loginvalidation("supplier","user"));
     }
	 @Test
     public void testDriverLoginValidation() throws Exception{
	       assertEquals(true, truckLoginService.loginvalidation("santhoshkumara1204@gmail.com","$Santhu12"));
	       assertEquals(false, truckLoginService.loginvalidation("truck","user"));
     }
	 @Test
	 public void testSupplierRegistration() throws Exception{
		 List<SupplierDetails> sd=supplierDetailsRepo.findAll();
		 int a=sd.size();
		 supplierService.register("username", "email", "password", "fromAddress", "6361748785", "25358698967");
		 sd=supplierDetailsRepo.findAll();
		 int b=sd.size();
		 SupplierDetails sd2=supplierDetailsRepo.findByEmail("email");
		 assertEquals(a+1, b);
		 supplierDetailsRepo.deleteById(sd2.getSuppId());
		 assertEquals(b-1, a);
		 
	 }
	 
	 @Test
     public void testDriverRegisteration() throws Exception{
		 List<TruckDetails> td = truckDetailsRepo.findAll();
		 int x =td.size();
//		 System.out.println("x= "+x);
		 truckDetailsService.register("Test", "password", "anujsm112345@gmail.com", "7896998962");
		  td = truckDetailsRepo.findAll();
		 int y = td.size();
//		 System.out.println("y= "+y);
		 TruckDetails td2 = truckDetailsRepo.findByEmail("anujsm112345@gmail.com");
	       assertEquals(x+1, y);
	     truckDetailsRepo.deleteById(td2.getTruckId());
	     assertEquals(y-1, x);
     }
	
	 
	 
//	 @Test
//	 public void saveNewOrderDetails(){
//		 LocalDateTime time=LocalDateTime.parse("2022-12-01T18:41:29.065");
//		 OrderDetails ord=new OrderDetails("santhosh",time, "davanagere", 9876543210L,"nonveg",8, 78);
//		 assertEquals(, newOrderServiceImpl.saveNew(ord));
//	  }
	
	
//	 @Test
//     public void testAdminLoginPage() throws Exception{
//         mvc.perform(get("/admin/adminLogin")).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
//	 
//	 
//	 
//	 @Test
//     public void testSupplierLoginPage() throws Exception{
//         mvc.perform(get("/supplier/supplierLogin")).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
//	 
//	 @Test
//     public void testSupplierRegisterPage() throws Exception{
//         mvc.perform(get("/supplier/supplierRegister")).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
//	 
//	 @Test
//     public void testDriverLoginPage() throws Exception{
//         mvc.perform(get("/truckDriver/driverLogin")).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
//	
//	 @Test
//     public void testDriverRegisterPage() throws Exception{
//         mvc.perform(get("/truckDriver/driverRegister")).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
//	 
//	
//	
//	 @Test
//     public void testSupplierTruckDetailsPage() throws Exception{
//		 String id="1";
//         mvc.perform(get("/supplier/truckDetails/{id}",id)).andDo(print()).andExpect(content().string("")).andExpect(status().isOk());
//     }
	
	
	 
}
