import java.util.ArrayList;


public class BKNode  implements java.io.Serializable
{
	private ArrayList<AdjacentBK> adj;
	private AdjacentBK minAdj;
	private Shabd s;
	static final long serialVersionUID = -7588980448693010399L;

	public BKNode()
	{
		adj = new ArrayList<AdjacentBK>();
		minAdj=null;
		s =null;
	}

	public ArrayList<BKNode> getAdjBKNListAsNodes()
	{
		ArrayList<BKNode> adjNodes=new ArrayList<BKNode> ();
		for(int index=0;index<adj.size();index++)
			adjNodes.add(adj.get(index).getNode());
		return adjNodes;
	}


	public BKNode(Shabd w)
	{
		adj = new ArrayList<AdjacentBK>();
		minAdj=null;
		s=w;
	}

	public void addAdjBKN(AdjacentBK ad)
	{
		if(minAdj==null)
			minAdj = ad;
		else if(minAdj.getEdge()>ad.getEdge())
			minAdj = ad;

		adj.add(ad);
	}

	public ArrayList<AdjacentBK> getAdjBKNList()
	{
		return adj;
	}

	public AdjacentBK getMinAdjEdge()
	{
		return minAdj;
	}


	public int getSignature() {
		// TODO Auto-generated method stub
		return s.getSignature();
	}

	public Shabd getShabd()
	{
		return s;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<adj.size();i++)
			sb.append("-"+adj.get(i).toString());


		StringBuffer str;
		if(adj.size()>0)
			str = new StringBuffer(s.getWord()+"-"+"----adj>"+sb);
		else
			str = new StringBuffer(s.getWord());
		return str.toString();
	}




}
