package com.yourpackagename.yourwebproject.webapp.utils;

import com.yourpackagename.framework.data.JpaEntity;
import com.yourpackagename.framework.web.datatable.DataTablesResultSet;
import com.yourpackagename.framework.web.datatable.PagingCriteria;
import com.yourpackagename.framework.web.datatable.ResultSet;
import com.yourpackagename.framework.web.datatable.WebResultSet;

public class ControllerUtils {

	public static <T extends JpaEntity<?>> WebResultSet<T> getWebResultSet(PagingCriteria pc, ResultSet<T> rs)
	{
	  return new DataTablesResultSet<T>(pc, rs);
	}

}
