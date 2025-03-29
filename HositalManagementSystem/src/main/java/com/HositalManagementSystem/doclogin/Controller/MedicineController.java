package com.HositalManagementSystem.doclogin.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HositalManagementSystem.doclogin.Entity.Medicine;
import com.HositalManagementSystem.doclogin.repository.MedicineRepository;
import com.HositalManagementSystem.repository.PatientRepository;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class MedicineController {
	
	
	@Autowired
	MedicineRepository medicineRepository;
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@PostMapping("/medicines")
	public Medicine createMedicine(@RequestBody Medicine medicine)
	{
		return medicineRepository.save(medicine);
	}

	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@GetMapping("/medicines")
	public List<Medicine>getAllMedicine()
	{
		return medicineRepository.findAll();
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/medicines/{id}") //get medicine by ID
	public ResponseEntity<Medicine>getMedicineById(@PathVariable long id) throws AttributeNotFoundException
	{
	 Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found With id :"+id));
		
	 return ResponseEntity.ok(medicine); 
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//Update Medicine by id
	
	@PutMapping("/medicines/{id}")
	public ResponseEntity<Medicine>updateMedicine(@PathVariable long id,@RequestBody Medicine medicineDetails) throws AttributeNotFoundException
	{
	 Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found With id :"+id));
	
	 medicine.setDrugName(medicineDetails.getDrugName());
	 medicine.setStock(medicineDetails.getStock());
	 
	 medicineRepository.save(medicine);
	 
	 return ResponseEntity.ok(medicine);
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//Delete Medicine by id
	
	@DeleteMapping("/medicines/{id}")
	public ResponseEntity<Map<String , Boolean>> delete(@PathVariable long id) throws AttributeNotFoundException
	{
		 Medicine medicine = medicineRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine Not Found With id :"+id));

		 medicineRepository.delete(medicine);
		 
		 Map<String, Boolean> response= new HashMap<String, Boolean>();
		 
		 response.put("Delete", Boolean.TRUE);
		 
		 return ResponseEntity.ok(response);
	}
}
