package com.xpanxion.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.xpanxion.exceptions.InvalidPropertyException;

public class Configuration {

	private static Configuration config;

	private Properties prop;
	// private HashMap<String, String> urlMap;
	private Properties systemProps;
//	private Properties defaultProps;

	// TODO: Write a private default constructor

	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	private Properties getProp() {
		if (prop == null) {
			prop = readPropFile(Constants.CONFIG_FILE);
		}
		return prop;
	}

	private Properties readPropFile(String fileName) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			// System.out.println(new File("bob.txt").getAbsolutePath());
			input = new FileInputStream(new File(Res.getResource(fileName).toURI()));
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				input.close();
			} catch (Throwable t) {
				t.printStackTrace(System.out);
			}
		}
		return prop;
	}

	private String get(String propName) {
		// check is system is having property
		if (systemProps == null) {
			systemProps = System.getProperties();
		}
		if (systemProps.get(propName) != null) {
			return systemProps.get(propName).toString();
		}
		String value = getProp().getProperty(propName);
		if (value != null) {
			return value;
		}
		throw new InvalidPropertyException("Invalid property name: " + propName);
		
	}

	

	private final boolean remote = Boolean.parseBoolean(get("selenium.remote"));
	private final String browsers = get("selenium.browsers");
	// private final String mobiles = getProp().getProperty("selenium.mobiles");

	// private final String host = getProp().getProperty("aut.server");
	// private final boolean useSsl =
	// Boolean.valueOf(getProp().getProperty("aut.useSSL"));

	// private final String mobileDevice = "Galaxy S4";
	// private final int mobileWidth = 200;
	// private final int mobileHeight = 600;

	// private final String chromeWebdriver =
	// getProp().getProperty("webdriver.chrome.driver");
	// private final String ieWebdriver =
	// getProp().getProperty("webdriver.ie.driver");

	// private final String confluenceUser =
	// getProp().getProperty("confluence.user");
	// private final String confluencePassword =
	// getProp().getProperty("confluence.password");

	// private final String executionEnvironment =
	// getProp().getProperty("execution.environment");
	// private final String newLine = System.lineSeparator();

	private final int elementTimeoutMillis = Integer.parseInt(get("selenium.elementtimeout"));
	// private final int pageTimeoutMillis =
	// Integer.parseInt(getProp().getProperty("selenium.pagetimeout"));
	// private final boolean setBrowsermobProxy = true;

	// public HashMap<String, String> getUrlMap() {
	// return urlMap;
	// }
	//
	public boolean isRemote() {
		return remote;
	}

	// public String getSeleniumGridUrl() {
	// return seleniumGridUrl;
	// }
	//
	// public boolean isDemo() {
	// return demo;
	// }
	//
	// public String getHost() {
	// return host;
	// }
	//
	// public boolean isUseSsl() {
	// return useSsl;
	// }

	// public String getMobileDevice() {
	// return mobileDevice;
	// }
	//
	// public int getMobileWidth() {
	// return mobileWidth;
	// }
	//
	// public int getMobileHeight() {
	// return mobileHeight;
	// }

	// public String getChromeWebdriver() {
	// return chromeWebdriver;
	// }
	//
	// public String getIeWebdriver() {
	// return ieWebdriver;
	// }

	// public String getConfluenceUser() {
	// return confluenceUser;
	// }
	//
	// public String getConfluencePassword() {
	// return confluencePassword;
	// }
	//
	// public String getExecutionEnvironment() {
	// return executionEnvironment;
	// }
	//
	// public String getNewLine() {
	// return newLine;
	// }
	//
	public int getElementTimeoutMillis() {
		return elementTimeoutMillis;
	}

	//
	// public int getPageTimeoutMillis() {
	// return pageTimeoutMillis;
	// }
	//
	// public boolean isSetBrowsermobProxy() {
	// return setBrowsermobProxy;
	// }
	//
	public String getBrowsers() {
		return browsers;
	}

	//
	// public String getMobiles() {
	// return mobiles;
	// }
	public String getDevice() {
		return get(PropKeys.DEVICE_NAME);
	}

	public static interface PropKeys {
		String DEVICE_NAME = "deviceName";
		String HUB_URL = "selenium.hub_url";
		String DEBUG_MODE = "project.debug_mode";
		String AUT_SERVER_URL = "aut.server";
		String AUT_USE_SSL = "aut.useSSL";
		String ELEMENT_TIMEOUT = "selenium.element_timeout";
		String PAGE_TIMEOUT = "selenium.page_timeout";
		String BROWSERS = "selenium.browsers";
		String MOBILES = "selenium.mobiles";

	}

	public String getHubUrl() {

		return get(PropKeys.HUB_URL);
	}

	public String getBrowserName() {
		//TODO: remove this methods
		return "";
	}

}
