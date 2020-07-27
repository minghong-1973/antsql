package ant.sql.data;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ant.sql.astnode.AntWhereParser;
import ant.sql.astnode.ParseException;
import ant.sql.astnode.SimpleNode;
import ant.sql.astnode.SystemTable;

public class RDD {
	

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public List<Record> getDatas() {
		return datas;
	}

	public void setDatas(List<Record> datas) {
		this.datas = datas;
	}

	public String[] getGroups() {
		return groups;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}

	private List<Record> datas = new ArrayList<Record>();
	private String[] groups=null;
	private String sqlWhere;
	private Record header;
	public RDD(String[] rows) {
		String[] headers = rows[0].split(",");
		header = new Record();
		for (int i = 0; i < headers.length; i++) {
			header.set(headers[i], "header");
		}
		for (int i = 1; i < rows.length; i++) {
			String[] fields = rows[i].split(",");
			Record record = new Record();
			for (int j = 0; j < headers.length; j++) {
				
				record.set(headers[j], fields[j].trim());
				
			}
			datas.add(record);
		}

	}

	public RDD(List<Record> rows) {
		datas = rows;
	}

	public RDD filters(String where) {
		if (where == null || where != null && where.equals("")) {
			return this;
		}
		List<Record> newDatas = new ArrayList<Record>();
		
		for (Record item : datas) {
			try {
				StringReader reader = new StringReader(where);
				AntWhereParser parse = new AntWhereParser(reader);
				parse.ExpressionWhere();
				SimpleNode node = (SimpleNode) (parse.rootNode());
				node.dump("test---------------");
				SystemTable.setTable(item);
				node.interpret();
				if (!((Boolean) node.stack[node.top]).booleanValue()) {
					--node.top;
					continue;
				} else {
					--node.top;
					newDatas.add(item);

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		RDD resultRDD = new RDD(newDatas);
		resultRDD.setSqlWhere(where);
	    return resultRDD;
	}

	private List<Record> initGroupTreeBy(List<Record> datas, String group) {
		List<Record> newDatas = new ArrayList<Record>();
		HashMap<String, List<Record>> dict = new HashMap<String, List<Record>>();
		for (Record item : datas) {
			String groupKey = (String) item.get(group);
			List<Record> resultDic = dict.get(groupKey);
			if (resultDic == null) {
				resultDic = new ArrayList<Record>();
				item.del(group);
				resultDic.add(item);
				dict.put(groupKey, resultDic);

			} else {
				item.del(group);
				resultDic.add(item);

			}

		}
		for (String groupKey : dict.keySet()) {
			Record item = new Record();
			item.set(group, groupKey);
			item.set("subtable", dict.get(groupKey));
			item.set("level",new Integer(1));
			newDatas.add(item);
		}
		return newDatas;
	}

	private void groupTree(Record root,int level,String group)
	{
		int j=((Integer)root.get("level")).intValue();
		if(j==level)
		{
			List<Record> newDatas = new ArrayList<Record>();
			List<Record> tempResult=(List<Record>)root.get("subtable");
			HashMap<String, List<Record>> dict = new HashMap<String, List<Record>>();
			for (Record item : tempResult) {
				String groupKey = (String) item.get(group);
				List<Record> resultDic = dict.get(groupKey);
				if (resultDic == null) {
					resultDic = new ArrayList<Record>();
					item.del(group);
					resultDic.add(item);
					dict.put(groupKey, resultDic);

				} else {
					item.del(group);
					resultDic.add(item);

				}

			}
			for (String groupKey : dict.keySet()) {
				Record item = new Record();
				item.set(group, groupKey);
				item.set("subtable", dict.get(groupKey));
				item.set("level",new Integer(level+1));
				newDatas.add(item);
			}
			root.set("subtable", newDatas);
		
		
		}	
		else
		{
			List<Record> tempResult=(List<Record>)root.get("subtable");
			for(Record temp : tempResult)
			{
				groupTree(temp,level,group);
			}	
		}
	
	}
	
	public RDD groupBy(String group) {
		if (group == null || group != null && group.equals("")) {
			return this;
		}
		groups = group.split(",");
		int i = 0;
		List<Record> result = initGroupTreeBy(datas, groups[i]);
		i++;
		while (i < groups.length) {
			for (Record item : result) {
				groupTree(item,i,groups[i]);	
				
			}
		   i++;
		}
		RDD resultRDD = new RDD(result);
		resultRDD.setGroups(groups);
		return resultRDD;
	}

	public RDD limit(int count) {
		if(count==-1)
		{
			return this;
		}
		
		List<Record> newDatas = new ArrayList<Record>();
		for(int i=0;i<count&&i<datas.size();i++)
		{
			newDatas.add(datas.get(i));	
		}
		RDD resultRDD = new RDD(newDatas);
		resultRDD.setGroups(groups);
	    return resultRDD;
	}

	private String treeToStr(Record item)
	{
		
		String content=item.toString();
		List<Record> tempResult=(List<Record>)item.get("subtable");
		if(tempResult!=null)
		{   content=content+"[";
			for(Record subtable:tempResult)
        	{
        		content=content+treeToStr(subtable)+"||";
        	}
			content=content.substring(0,content.length()-2);
			content=content+"]";
		}
		return content;
		
	}
	public String toString() {
		 String content="";
        if(groups!=null)
        {
        	   
            	for(Record item:datas)
            	{
            		content=content+treeToStr(item)+"\r\n";
            	}
        }
        else
        {  
        	for(Record item:datas)
        	{
        		content=content+item.toString()+"\r\n";
 
        	}
        }
	   return content;
	}
}
