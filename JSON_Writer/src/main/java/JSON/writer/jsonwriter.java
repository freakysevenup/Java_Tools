package JSON.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is used to write information supplied by an excel file to a json file.
 * it was customized for the use of the BDD Framework
 * @author Jesse Derochie
 *
 */
public class jsonwriter 
{
	JSONObject rootObj;
	JSONArray list;
	private String fileName = "";
	
	private static final String localPath = "C:\\Users\\jderochie\\workspace\\JSONWriter\\Sessions\\Local\\";
	private static final String remotePath = "C:\\Users\\jderochie\\workspace\\JSONWriter\\Sessions\\Remote\\";
	private static String path = "";
	
	HashMap<String, JSONObject> objMap = new HashMap<String, JSONObject>();
	
	/**
	 * Constructor
	 * @param filename
	 */
	public jsonwriter(String name)
	{
		newJSON(name);
		rootObj = new JSONObject();
	}
	
	/**
	 * Sets the filepath for writing the JSON files
	 * @param remote true = remote, false = local
	 */
	public static void setFilePath(boolean remote)
	{
		if(remote == true)		
		{
			path = remotePath;
		}
		else if(remote == false)
		{
			path = localPath;
		}
	}
	
	/**
	 * create a new JSON file
	 * @param fileName
	 */
	private void newJSON(String name)
	{
		this.fileName = name;
	}
	
	/**
	 * Get the root JSON object for this JSON file
	 * @return
	 */
	public JSONObject getRoot()
	{
		return rootObj;
	}
	
	/**
	 * save this JSON file
	 */
	public void finalize(String json)
	{
		try {

			FileWriter file = new FileWriter(path + fileName + ".JSON");
			file.write(json);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create a variable in the given JSON object
	 * @param obj the object to create the variable in
	 * @param name the name of the variable
	 * @param value the value of the variable
	 */
	@SuppressWarnings("unchecked")
	public void createVariable(JSONObject obj, String name, Object value)
	{
		obj.put(name, value);
	}
	
	/**
	 * Get the JSON object from the root by name
	 * @param name
	 * @return
	 */
	public JSONObject getObject(String name)
	{
		return objMap.get(name);
	}
	
	/**
	 * Create a new JSONObject and store it in the objMap
	 * @param newObj the new JSONObject
	 * @param name the key to store the new JSONObject with
	 */
	public void createNewObject(JSONObject newObj, String name)
	{
		objMap.put(name, newObj);
	}
	
	/**
	 * create an array inside the given JSON object
	 * @param obj the object to create the array in
	 * @param name the name of the array 
	 * @param values the values in the array
	 */
	@SuppressWarnings("unchecked")
	public void createArray(JSONObject obj, String name, String[] values)
	{
		list = new JSONArray();
		for(int i = 0; i < values.length; i++)
		{
			list.add(values[i]);
		}
		obj.put(name, list);
	}
}
