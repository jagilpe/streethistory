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

import java.util.List;

import org.postgis.PGgeometry;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilpereda.streethistory.domain.Photo;
import com.gilpereda.streethistory.domain.Tag;
import com.gilpereda.streethistory.service.PhotoService;

/**
 * @author "Javier Gil Pereda"
 *
 */
@Service("photoService")
@Repository
@Transactional
public class PhotoServiceImpl implements PhotoService {

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Photo> findAll() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#findById(long)
	 */
	@Override
	public Photo findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#save(com.gilpereda.streethistory.domain.Photo)
	 */
	@Override
	public Photo save(Photo photo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#delete(com.gilpereda.streethistory.domain.Photo)
	 */
	@Override
	public void delete(Photo photo) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#findByLocation(org.postgis.PGgeometry)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Photo> findByLocation(PGgeometry geom) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gilpereda.streethistory.service.PhotoService#findByTags(java.util.List)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Photo> findByTags(List<Tag> tags) {
		// TODO Auto-generated method stub
		return null;
	}

}
