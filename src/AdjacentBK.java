
public class AdjacentBK  implements java.io.Serializable
{
	private BKNode bkn;
	private int edge;
	static final long serialVersionUID = -7588980448693010399L;

	public AdjacentBK(BKNode bkn,int edge)
	{
		this.bkn = bkn;
		this.edge=edge;
	}
	public int getEdge() {
		// TODO Auto-generated method stub
		return edge;
	}

	public BKNode getNode() {
		// TODO Auto-generated method stub
		return bkn;
	}

	public String toString()
	{
		return (bkn.getShabd().getWord()+"("+edge+")");
	}

}
