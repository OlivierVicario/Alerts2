package com.safetynet.alerts.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Root;

/**
 * @author olvic
 *
 * create, read, update and delete firestations
 */
@Service
public class FirestationService {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FirestationService.class);
	
	
	public List<Firestation> listFirestations = new ArrayList<Firestation>();
	
	
	/**
	 * @return List<Firestation>
	 */
	public List<Firestation> findAllFirestations() {
		return listFirestations;
	}


	/**
	 * @param address
	 * @param station
	 * @return
	 */
	public Firestation createfirestation(String address, String station) {
		try {
			LOGGER.info("begin createFirestation");
			Firestation firestation = new Firestation();
			firestation.setAddress(address);
			firestation.setStation(station);
			listFirestations.add(firestation);
			return firestation;
		 } catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end createFirestation");
		}
		return null;
	}
	
	

	/**
	 * @param address
	 * @return Firestation
	 */
	public Firestation deleteFirestationByAddress(String address) {
		try {
			LOGGER.info("end deleteFirestation");
			for (Firestation firestation : listFirestations) {
				if (firestation.address.equals(address)) {
					listFirestations.remove(firestation);
					return firestation;
				}
			}
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteFirestation");
		}
		return null;
	}
	

	/**
	 * @param station
	 * @return
	 */
	public Firestation deleteFirestationByStation(String station) {
		try {
			LOGGER.info("begin deleteFirestation");
			for (Firestation firestation : listFirestations) {
				if (firestation.station.equals(station)) {
					listFirestations.remove(firestation);
					return firestation;
				}
			}
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end deleteFirestation");
		}
		return null;
	}
	
	

	/**
	 * @param address
	 * @param station
	 * @return
	 */
	public Firestation updateFirestation(String address, String station)
	{
		try {
			LOGGER.info("begin updateFirestation");
			for (Firestation firestation : listFirestations) {
				if (firestation.address.equals(address)) {
					firestation.setStation(station);
					return firestation;
				}
			}
		} catch (Exception e) {
			LOGGER.error( e.getMessage());
		}finally {
			LOGGER.info("end updateFirestation");
		}
		return null;
		
	}
	
	/**
	 * @return List<Firestation>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostConstruct
	public List<Firestation> loadFirestations() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Root root = mapper.readValue(new File("data.json"), Root.class);
		listFirestations  = root.getFirestations();
		return listFirestations;
	}
}
