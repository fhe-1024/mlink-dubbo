package com.mlink.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtil {

	private static PropertiesConfiguration CONFIG;

	static {
		try {
			CONFIG = new PropertiesConfiguration("mlink.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static final String MLINK_PIC = CONFIG.getString("mlink.pic.path");

}
