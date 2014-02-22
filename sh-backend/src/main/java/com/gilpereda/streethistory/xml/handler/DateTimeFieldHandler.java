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
package com.gilpereda.streethistory.xml.handler;

import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author "Javier Gil Pereda"
 *
 */
public class DateTimeFieldHandler extends GeneralizedFieldHandler {
	
	private static String dateFormatPattern;
	
	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}

	/* (non-Javadoc)
	 * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponGet(java.lang.Object)
	 */
	@Override
	public Object convertUponGet(Object arg0) {
		DateTime dateTime = (DateTime) arg0;
		return format(dateTime);
	}

	/* (non-Javadoc)
	 * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponSet(java.lang.Object)
	 */
	@Override
	public Object convertUponSet(Object arg0) {
		String dateTimeString = (String) arg0;
		return parse(dateTimeString);
	}

	/* (non-Javadoc)
	 * @see org.exolab.castor.mapping.GeneralizedFieldHandler#getFieldType()
	 */
	@Override
	public Class<DateTime> getFieldType() {
		return DateTime.class;
	}
	
	protected static String format(final DateTime dateTime) {
		String dateTimeString = "";
		
		if (dateTime != null) {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTimeString = formatter.print(dateTime);
		}
		
		return dateTimeString;
	}
	
	protected static DateTime parse(final String dateTimeString) {
		DateTime dateTime = new DateTime();
		
		if (dateTimeString != null) {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormatPattern);
			dateTime = formatter.parseDateTime(dateTimeString);
		}
		
		return dateTime;
	}

}
