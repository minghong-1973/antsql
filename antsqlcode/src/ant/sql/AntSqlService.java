package ant.sql;

import ant.sql.data.RDD;

public class AntSqlService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public String antSqlService(String[] rows,String where,String group,int limit)
    {
    	  RDD result=new RDD(rows);
    	  return result.filters(where).groupBy(group).limit(limit).toString();
    }
}
