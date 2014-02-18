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
package com.gilpereda.streethistory.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author "Javier Gil Pereda"
 *
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {
	
	// Properties definition
	private String tag;
	// Many to many relationships
	private Set<Photo> photos;
	private Set<Scene> scenes;

	// Many to many relationships with Photo
	/**
	 * @return the photos
	 */
	@ManyToMany
	@JoinTable(name = "many_photo_has_many_tag",
	joinColumns = @JoinColumn(name = "id_tag"),
	inverseJoinColumns = @JoinColumn(name = "id_photo"))
	public Set<Photo> getPhotos() {
		return photos;
	}

	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	
	// Many to many relationship with Scene

	/**
	 * @return the scenes
	 */
	@ManyToMany
	@JoinTable(name = "many_scene_has_many_tag",
	joinColumns = @JoinColumn(name = "tag_tag"),
	inverseJoinColumns = @JoinColumn(name = "id_scene"))
	public Set<Scene> getScenes() {
		return scenes;
	}

	/**
	 * @param scenes the scenes to set
	 */
	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

	// Getters and Setters
	/**
	 * @return the tag
	 */
	@Id
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
}
