package com.yourpackagename.framework.web.datatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum SortDirection 
{
  ASC("asc"), DESC("desc");
	
  private String direction;
  private static final Logger logger = LoggerFactory.getLogger(SortDirection.class);
	
  private SortDirection(String direction)
  {
    this.direction = direction;
  }
	
  public String getDirection()
  {
    return this.direction;
  }
	
  public static SortDirection valueOfCaseInsensitive(String value)
  {
    String valueUpper = value.toUpperCase();
    SortDirection sd = SortDirection.valueOf(valueUpper);
    logger.debug("SortDirection converted "+value+" to "+sd);
    return sd;
  }
}
