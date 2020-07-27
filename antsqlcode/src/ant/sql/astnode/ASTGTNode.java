package ant.sql.astnode;
/* Generated By:JJTree: Do not edit this line. ASTGTNode.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=BaseNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTGTNode extends SimpleNode {
  public ASTGTNode(int id) {
    super(id);
  }

  public ASTGTNode(AntWhereParser p, int id) {
    super(p, id);
  }
  public void interpret()
  {
	 ASTFiledExpression field=((ASTFiledExpression)children[0]);
	 ASTvalueExpression value=((ASTvalueExpression)children[1]);
       
	 Object fieldObjectValue=SystemTable.get((String)field.name); 
	 Object valueObject=value.value;
	 if(valueObject instanceof Integer )
	  {
		  try {
			if(Integer.valueOf((String)fieldObjectValue)>((Integer)valueObject).intValue())
			  {
				  stack[++top]=new Boolean(true);
				  return;  
				  
			  }
			  else
			  {
				  stack[++top]=new Boolean(false);
				  return;
				  
			  }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stack[++top]=new Boolean(false);
			return; 
		}
		
	  
	  
	  }
	 if(valueObject instanceof String )
	 {
		 try {
			String strValue=((String) valueObject).replaceAll("'","");
			 strValue=strValue.replaceAll("'","");
			 if(strValue.compareTo((String)fieldObjectValue)>0)
			 {
				 stack[top]=new Boolean(true);
				 return;
			 }
			 else
			 {
				 stack[top]=new Boolean(false);
					return; 
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stack[top]=new Boolean(false);
		}
		 
		 
	 }
	 
	 
	
  }
}
/* JavaCC - OriginalChecksum=80d54a735438a6a4c76a053fa7d64f04 (do not edit this line) */