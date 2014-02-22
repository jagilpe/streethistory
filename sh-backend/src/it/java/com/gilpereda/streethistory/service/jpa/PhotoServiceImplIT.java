/*
 * Copyright (C) 2014 Javier Gil Pereda 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.gilpereda.streethistory.service.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.gilpereda.streethistory.config.StreetHistory;
import com.gilpereda.streethistory.domain.Photo;
import com.gilpereda.streethistory.geometry.GeometryHelper;
import com.gilpereda.streethistory.service.PhotoService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author "Javier Gil Pereda"
 *
 */
public class PhotoServiceImplIT extends AbstractServiceImplIT {
	
	@Autowired
	private PhotoService photoService;
	
	@Test
	public void testFindAll() {
		List<Photo> photos = photoService.findAll();
		
		assertNotNull(photos);
		assertEquals(3, photos.size());
		
	}
	
	@Test
	public void testFindById() {
		long id = 1;
		String title = "Photo 1";
		
		Photo photo = photoService.findById(id);
		
		assertNotNull(photo);
		assertEquals(title, photo.getTitle());	
	}
	
	@Test
	@Rollback(true)
	public void testSaveDelete() {
		// Check if we can save a new Photo
		Photo photo = new Photo();
		photo.setTitle("New Photo");
		photo.setExtract("New Photo's extract");
		photo.setDescription("New Photo's description");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		photo.setDate(formatter.parseDateTime("2010-10-21"));
		photo.setLocation(GeometryHelper.getGeometryFactory().createPoint(new Coordinate(52.2, 13.3)));
		photo.setUrl(StreetHistory.BASE_IMAGE_URL + "img_new.jpg");
		
		photo = photoService.save(photo);
		long photoID = photo.getId();
		
		// Check that it's saved in DB
		photo = photoService.findById(photoID);
		assertNotNull(photo);
		
		// Edit and save it
		photo.setTitle("Another photo");
		photoService.save(photo);
		photo = photoService.findById(photoID);
		assertNotNull(photo);
		assertEquals("Another photo", photo.getTitle());
		
		// Delete it
		photoService.delete(photo);
		photo = photoService.findById(photoID);
		assertNull(photo);
	}
	
	@Test
	public void testFindByLocation() {
		// Load the testing data
		Properties props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream("test-data.properties"));
		} catch (IOException ex) {
			// If we cannot load the properties file the test should fail
			fail("Could not load test-data.properties file");
		}
		// We create the polygons used in the search
		// A polygon that contains all the 3 photos in DB
		Polygon areaAll = GeometryHelper.getRectangle(
				Double.parseDouble(props.getProperty("rectangleAll.lat1")), 
				Double.parseDouble(props.getProperty("rectangleAll.long1")),
				Double.parseDouble(props.getProperty("rectangleAll.lat2")), 
				Double.parseDouble(props.getProperty("rectangleAll.long2")));
		// A polygon that contains 2 of the 3 photo
		Polygon areaTwo = GeometryHelper.getRectangle(
				Double.parseDouble(props.getProperty("rectangleTwo.lat1")), 
				Double.parseDouble(props.getProperty("rectangleTwo.long1")),
				Double.parseDouble(props.getProperty("rectangleTwo.lat2")), 
				Double.parseDouble(props.getProperty("rectangleTwo.long2")));
		// A polygon that contains no photo
		Polygon areaNone = GeometryHelper.getRectangle(
				Double.parseDouble(props.getProperty("rectangleNone.lat1")), 
				Double.parseDouble(props.getProperty("rectangleNone.long1")),
				Double.parseDouble(props.getProperty("rectangleNone.lat2")), 
				Double.parseDouble(props.getProperty("rectangleNone.long2")));
		
		// We check if we find all the photos within the first area
		List<Photo> photos = photoService.findByLocation(areaAll);
		assertNotNull(photos);
		assertEquals(3, photos.size());
		
		// We check if we find two photos within the second area
		photos = photoService.findByLocation(areaTwo);
		assertNotNull(photos);
		assertEquals(2, photos.size());
		
		// We check if we find no photo within the second area
		photos = photoService.findByLocation(areaNone);
		assertNotNull(photos);
		assertEquals(0, photos.size());
	}
	
}
