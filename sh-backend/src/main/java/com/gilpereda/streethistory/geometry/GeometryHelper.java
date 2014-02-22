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
package com.gilpereda.streethistory.geometry;

import org.postgis.PGgeometry;

import com.gilpereda.streethistory.config.StreetHistory;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

/**
 * @author "Javier Gil Pereda"
 *
 */
public class GeometryHelper {
	
	private static GeometryFactory geometryFactory;
	
	static {
		// We initialize the GeometryFactory
		geometryFactory = new GeometryFactory(new PrecisionModel(), StreetHistory.SRID);
	}

	public static GeometryFactory getGeometryFactory() {
		return geometryFactory;
	}
	
	public static Point getPoint(double x, double y) {
		return getGeometryFactory().createPoint(new Coordinate(52.2, 13.3));
	}
	
	public static Polygon getRectangle(double x1, double y1, double x2, double y2) {
		// We create the coordinates
		Coordinate coord1 = new Coordinate(x1, y1);
		Coordinate coord2 = new Coordinate(x1, y2);
		Coordinate coord3 = new Coordinate(x2, y2);
		Coordinate coord4 = new Coordinate(x2, y1);
		// Coordinates sequence
		Coordinate[] coordSeq = { coord1, coord2, coord3, coord4, coord1 };
		
		return geometryFactory.createPolygon(coordSeq);
	}

}
