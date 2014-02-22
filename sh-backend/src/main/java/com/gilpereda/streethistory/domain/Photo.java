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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.vividsolutions.jts.geom.Point;

/**
 * @author "Javier Gil Pereda"
 *
 */
@Entity
@Table(name = "photo")
@NamedQueries({
	@NamedQuery(name="Photo.findByLocation",
			query="select p from Photo p where within(p.location, :area)=true")
})
public class Photo implements Serializable {
	
	// Properties definition
	private long id;
	private String title;
	private String extract;
	private String description;
	private DateTime date;
	private String url;
	private Point location;
	// Many to many relationship
	private Set<Tag> tags;
	private Set<Scene> scenes;
	
	// Many to many relationship with tag
	/**
	 * @return the tags
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "many_photo_has_many_tag",
			joinColumns = @JoinColumn(name = "id_photo"),
			inverseJoinColumns = @JoinColumn(name = "tag_tag"))
	public Set<Tag> getTags() {
		return tags;
	}
	
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * @return the scenes
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "many_scene_has_many_photo",
	joinColumns = @JoinColumn(name = "id_photo"),
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
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_id_seq")
	@SequenceGenerator(name = "photo_id_seq", sequenceName = "photo_id_seq")
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
	 * @return the extract
	 */
	@Column(name = "extract")
	public String getExtract() {
		return extract;
	}
	/**
	 * @param extract the extract to set
	 */
	public void setExtract(String extract) {
		this.extract = extract;
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
	 * @return the date
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "date")
	public DateTime getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}
	/**
	 * @return the url
	 */
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the location
	 */
	@JsonIgnore
	@Type(type = "org.hibernate.spatial.GeometryType")
	@Column(name = "location", columnDefinition = "org.postgis.PGgeometry")
	public Point getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public String toString() {
		return "id: " + getId() +
				"\ntitle: " + getTitle() +
				"\nextract: " + getExtract() +
				"\ndescription: " + getDescription() + 
				"\ndate: " + getDate() +
				"\nurl: " + getUrl() +
				"\nlocation: " + getLocation();
	}
}
