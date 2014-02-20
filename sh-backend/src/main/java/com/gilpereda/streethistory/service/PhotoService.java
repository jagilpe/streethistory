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
package com.gilpereda.streethistory.service;

import java.util.List;

import org.postgis.PGgeometry;

import com.gilpereda.streethistory.domain.Photo;
import com.gilpereda.streethistory.domain.Tag;

/**
 * @author "Javier Gil Pereda"
 * 
 */
public interface PhotoService {

	public List<Photo> findAll();

	public Photo findById(long id);

	public Photo save(Photo photo);

	public void delete(Photo photo);

	/**
	 * Finds all the photos which are within the geom object
	 * 
	 * @param geom
	 * @return
	 */
	public List<Photo> findByLocation(PGgeometry geom);
	
	/**
	 * Finds all the photos which are tagged with at least one of the Tags in the List
	 * 
	 * @param tags
	 * @return
	 */
	public List<Photo> findByTags(List<Tag> tags);
	
}
