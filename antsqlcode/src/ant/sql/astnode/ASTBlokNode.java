package ant.sql.astnode;
/* Generated By:JJTree: Do not edit this line. ASTBlokNode.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=BaseNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTBlokNode extends SimpleNode {
  public ASTBlokNode(int id) {
    super(id);
  }

  public ASTBlokNode(AntWhereParser p, int id) {
    super(p, id);
  }
  public void interpret()
  {
	 ((SimpleNode)children[0]).interpret();
      return;
    
  }
}
/* JavaCC - OriginalChecksum=b79bd0c588fb7fd8c42992e72778557c (do not edit this line) */
