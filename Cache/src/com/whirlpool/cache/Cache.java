package com.whirlpool.cache;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import com.ibm.broker.plugin.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
public class Cache {
	private static final String CacheInit = "cacheInitialized";
	
	public static void initializeMaps(String loadType) throws Exception {
		try {
	
			// ***********************************************************
			// *** Load the extract and the SUNRISE file into Cache ***
			// ************************************************************
			if (loadType == "EXTRACT") {

				loadCODEFile();
	
				MbGlobalMap DataModelMap = MbGlobalMap.getGlobalMap("DataModelMap");
				if (DataModelMap.containsKey(CacheInit)) {
					DataModelMap.update(CacheInit, 'Y');
				} else {
					DataModelMap.put(CacheInit, 'Y');
				}
 
			}  
		} catch (Exception e) {
			System.err.println("Error in initializeMap method!!");
			e.printStackTrace();}
			// return "NOTFOUND";
			}
	private static void loadCODEFile() throws Exception {
		MbGlobalMap DataModelMap = MbGlobalMap.getGlobalMap("DataModelMap");
		try {
			BufferedReader in = new BufferedReader(new FileReader(
 //				"C:\\Program Files\\ibm\\MQSI\\DATAMAPFILE.txt"));
  				 "/tmp/mqsi/DATAMAPFILE.txt"));
			String str;
			// start reading the file to EOF
			try {
				// AS long as we have a record execute this code
				while ((str = in.readLine()) != null) {
					if (str.length() > 0) {
						String CKey = str.substring(0, str.indexOf(',' ));
						DataModelMap.put(CKey, str.substring(str.indexOf(',')+1, str.length()));
					}
				}
				in.close();
			} catch (IOException e) {
				System.out.println("Issue Reading Error Code File!!");
				e.printStackTrace();
			}
			// Catch the file open errors
		} catch (FileNotFoundException e) {
			System.out.println("Cant Access file!!");
			e.printStackTrace();
		}
	}
	
	public static String getKey(String DMKey) {
		try {
			MbGlobalMap map = MbGlobalMap.getGlobalMap("DataModelMap");
			/*if (!map.containsKey(CacheInit)) {
				initializeMaps("EXTRACT");
			} */
			if (map.containsKey(DMKey)) {
				return (String) map.get(DMKey);
				
			} else {
				return "NOTFOUNDD";
			}
		} catch (Exception e) {
			System.err.println("Error in getKey method!!");
			e.printStackTrace();
			return "NOTFOUNDD";
		}
	}
	public static String getSAID(String SAIDKey) {
		try {
			MbGlobalMap Map = MbGlobalMap.getGlobalMap("SAIDMap");
			if (Map.containsKey(SAIDKey)) {
				return (String) Map.get(SAIDKey);
			} else {
				return "NOTFOUNDS";
			}
		} catch (Exception e) {
			System.err.println("Error in getSAIDKey method!!");
			e.printStackTrace();
			return "NOTFOUNDS";
		}
	}
	
	public static String getSAIDM2M(String SAIDKey) {
		try {
			MbGlobalMap Map = MbGlobalMap.getGlobalMap("M2MSAIDMap");
			if (Map.containsKey(SAIDKey)) {
				return (String) Map.get(SAIDKey);
			} else {
				return "NOTFOUNDS";
			}
		} catch (Exception e) {
			System.err.println("Error in getSAIDKey method!!");
			e.printStackTrace();
			return "NOTFOUNDS";
		}
	}
	
	public static String getDeviceM2M(String DeviceKey) {
		try {
			MbGlobalMap Map = MbGlobalMap.getGlobalMap("M2MDeviceMap");
			if (Map.containsKey(DeviceKey)) {
				return (String) Map.get(DeviceKey);
			} else {
				return "NOTFOUNDS";
			}
		} catch (Exception e) {
			System.err.println("Error in getDeviceKey method!!");
			e.printStackTrace();
			return "NOTFOUNDS";
		}
	}
	
	public static String getM2MDMKey(String M2MDMKey) {
		try {
			MbGlobalMap map = MbGlobalMap.getGlobalMap("M2MDataModelMap");
			/*if (!map.containsKey(CacheInit)) {
				initializeMaps("EXTRACT");
			} */
			if (map.containsKey(M2MDMKey)) {
				return (String) map.get(M2MDMKey);
				
			} else {
				return "NOTFOUNDD";
			}
		} catch (Exception e) {
			System.err.println("Error in getKeyatr name method!!");
			e.printStackTrace();
			return "NOTFOUNDD";
		}
	}
	
	public static String getM2MDMKeyNameAlias(String M2MDMKey) {
		try {
			MbGlobalMap map = MbGlobalMap.getGlobalMap("M2MDataModelMapNameAlias");
			/*if (!map.containsKey(CacheInit)) {
				initializeMaps("EXTRACT");
			} */
			if (map.containsKey(M2MDMKey)) {
				return (String) map.get(M2MDMKey);
				
			} else {
				return "NOTFOUNDD";
			}
		} catch (Exception e) {
			System.err.println("Error in getKeyNamealias method!!");
			e.printStackTrace();
			return "NOTFOUNDD";
		}
	}
}
