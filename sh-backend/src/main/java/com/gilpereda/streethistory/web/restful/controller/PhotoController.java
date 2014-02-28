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
package com.gilpereda.streethistory.web.restful.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gilpereda.streethistory.domain.Photo;
import com.gilpereda.streethistory.domain.Photos;
import com.gilpereda.streethistory.service.PhotoService;

/**
 * @author "Javier Gil Pereda"
 *
 */
@Controller
@RequestMapping(value="/photo")
public class PhotoController {
	
	final Logger logger = LoggerFactory.getLogger(PhotoController.class);
	
	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public List<Photo> list() {
		return photoService.findAll();
	}
}
