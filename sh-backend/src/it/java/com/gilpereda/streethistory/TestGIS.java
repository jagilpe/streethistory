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
package com.gilpereda.streethistory;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.postgis.PGgeometry;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.gilpereda.streethistory.domain.Photo;
import com.gilpereda.streethistory.service.PhotoService;

/**
 * @author "Javier Gil Pereda"
 *
 */
public class TestGIS {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:it-app-context.xml");
		ctx.refresh();
		
		DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
		Connection conn = dataSource.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select location from photo");
		while (rs.next()) {
			PGgeometry geom = (PGgeometry)rs.getObject(1);
			System.out.println(geom.toString());
		}
		
		PhotoService photoService = ctx.getBean("photoService", PhotoService.class);
		List<Photo> photos = photoService.findAll();
		for (Photo photo: photos) {
			System.out.println(photo);
		}
		
	}

}
