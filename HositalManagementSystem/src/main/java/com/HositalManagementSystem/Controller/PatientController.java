package com.HositalManagementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HositalManagementSystem.Entity.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import com.HositalManagementSystem.repository.PatientRepository;

@CrossOrigin(origins= "http://localhost:4200")// to communicate with angular
@RestController 
@RequestMapping("/api/v1")
public class PatientController {
	
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient)
	{
		return patientRepository.save(patient);
	}
	
	   //to get data ---------------------------------------------------------------------------------------------
	
		@GetMapping("/patients")
		public List <Patient>getAllPatient()
		{
			return patientRepository.findAll();
		}
		
		
		//get by id ----------------------------------------------------------------------------------------------
		
		
		@GetMapping("patients/{id}")
		public ResponseEntity<Patient>getPatientById(@PathVariable long id) throws AttributeNotFoundException
		{
			Patient patient = patientRepository.findById(id).orElseThrow(()-> new  AttributeNotFoundException("Patient not fount with"));
			
			return ResponseEntity.ok(patient);
		}
		
		
		//Delete API --------------------------------------------------------------------------------------------------------------------------------
		
		
		@DeleteMapping("/patients/{id}")
		public ResponseEntity<Map<String, Boolean>>deletePatient(@PathVariable long id) throws  AttributeNotFoundException
		{
			Patient patient = patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient not found with id :"+id));
			
			 patientRepository.delete(patient);
			Map<String, Boolean> response=new HashMap<String, Boolean>();
			response.put("Delete", Boolean.TRUE);
			
			return ResponseEntity.ok(response);
		}
		
		
		//update API --------------------------------------------------------------------------------------------------------------------------------
		
		
		@PutMapping("/patients/{id}")
		public ResponseEntity<Patient> updatePatientById(@PathVariable long id,@RequestBody Patient patientDetails) throws AttributeNotFoundException
		{
			Patient patient = patientRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Patient not found with id :"+id));
			
			patient.setAge(patientDetails.getAge());
			
			patient.setName(patientDetails.getName());
			
			patient.setBlood(patientDetails.getBlood());
			
			patient.setDose(patientDetails.getDose());
			
			patient.setFees(patientDetails.getFees());
			
			patient.setPrescription(patientDetails.getPrescription());
			
			patient.setUrgency(patientDetails.getUrgency());
			
			
			
			//to save above details -------------------------------------------------------------------------------------------------------------------------
			
			Patient savedPatient = patientRepository.save(patient);
			
			return ResponseEntity.ok(savedPatient);

		}
		
}
