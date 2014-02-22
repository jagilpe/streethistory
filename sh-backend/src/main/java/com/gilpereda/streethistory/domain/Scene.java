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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

/**
 * @author "Javier Gil Pereda"
 *
 */
@Entity
@Table(name = "scene")
public class Scene implements Serializable {
	
	// Properties definition
	private long id;
	private String title;
	private String description;
	private Point location;
	
	// Many to many relationships
	private Set<Photo> photos;
	private Set<Tag> tag;
	
	/**
	 * @return the photos
	 */
	@ManyToMany
	@JoinTable(name = "many_scene_has_many_photo",
	joinColumns = @JoinColumn(name = "id_scene"),
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
	
	/**
	 * @return the tag
	 */
	@ManyToMany
	@JoinTable(name = "many_scene_has_many_tag",
	joinColumns = @JoinColumn(name = "id_scene"),
	inverseJoinColumns = @JoinColumn(name = "tag_tag"))
	public Set<Tag> getTag() {
		return tag;
	}
	
	/**
	 * @param tag the tag to set
	 */
	public void setTag(Set<Tag> tag) {
		this.tag = tag;
	}
	
	// Getters and Setters
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the title
	 */
	@NotNull
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the description
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the location
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "location")
	public Point getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}	
}
