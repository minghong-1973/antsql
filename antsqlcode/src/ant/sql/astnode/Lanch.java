package ant.sql.astnode;
import java.io.StringReader;

public class Lanch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String where="(id=='hjj' or jk>=123) and kl!=345"; 
        StringReader reader=new StringReader(where);
        AntWhereParser parse=new AntWhereParser(reader);
        try {
			parse.ExpressionWhere();
			SimpleNode node=(SimpleNode)(parse.rootNode());
			node.dump("test---------------");
        
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
	
	}

}
