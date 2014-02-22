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
package com.gilpereda.streethistory.config;

import java.io.IOException;
import java.util.Properties;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * @author "Javier Gil Pereda"
 *
 */
public class StreetHistory {
	
	public static final String APP_PROPERTIES = "streetHistory.properties";
	public static final int SRID;
	public static final String BASE_IMAGE_URL;
	private static GeometryFactory geometryFactory;
	
	static {
		Properties props = new Properties();
		// Default values for the parameters
		int tempSrid = 4326;
		String tempBaseImageURL = "http://localhost/images/";
		// We try to load the general configuration parameters
		
		try {
			props.load(ClassLoader.getSystemResourceAsStream(APP_PROPERTIES));
			tempSrid = Integer.parseInt(props.getProperty("app.SRID"));
			tempBaseImageURL = props.getProperty("app.baseImageUrl");
		} catch (IOException ex) {
			/*
			 * We had a problem when loading the file
			 * We print a trace of the Exception and keep the default values
			 */
			ex.printStackTrace();
		}
		// We finally initialize the app's parameters
		SRID = tempSrid;
		BASE_IMAGE_URL = tempBaseImageURL;

	}
}
