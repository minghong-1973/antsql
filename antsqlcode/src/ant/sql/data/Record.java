package ant.sql.data;

import java.util.HashMap;

public class Record {
	private HashMap<String, Object> fields=new HashMap<String, Object>();

	public void set(String field, Object value) {
		fields.put(field, value);

	}

	public Object get(String field) {
		return fields.get(field);

	}

	public void del(String field) {
		fields.remove(field);

	}

	public String toString()
    {
    	String content="";
    	for(String key:fields.keySet())
    	{
    	   if(key.equals("level")||key.equals("subtable"))
				{
				    continue;	
				}
    	   content=content+fields.get(key)+",";	
    		
    		
    	}
    	content=content.substring(0,content.length()-1);
    	return content;
    	
    }
}
