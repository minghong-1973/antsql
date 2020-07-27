package ant.sql.astnode;
import ant.sql.data.Record;

public class SystemTable {
    private static Record table;
    public static Object get(String field)
    {
       return 	table.get(field);
    }
    public static void setTable(Record record)
    {
    	table=record;
    }

}
