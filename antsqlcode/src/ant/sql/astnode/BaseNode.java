package ant.sql.astnode;


public class BaseNode {
	  
	  public String name;
	  public static Object[] stack = new Object[1024];
	  public static int top =0;

	  /** @throws UnsupportedOperationException if called */
	  public void interpret()
	  {
	     throw new UnsupportedOperationException(); // It better not come here.
	  }
}
