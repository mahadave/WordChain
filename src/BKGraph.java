import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class BKGraph implements java.io.Serializable // graph will be made from many trees
{

	private int items;
	private int height;
	private ArrayList<BKNode> cliqueRoots;
	static final boolean show=true;
	static final long serialVersionUID = -7588980448693010399L;



	public BKGraph()
	{
		cliqueRoots = new ArrayList<BKNode>() ;
	}

	public BKGraph(ArrayList<Shabd> wordList)
	{
		cliqueRoots = new ArrayList<BKNode>() ;
		for(int i=0;i<wordList.size();i++)
		{
			cliqueRoots.add(new BKNode(wordList.get(i))); // init-tree roots
		}

		makeGraph();
	}

	public BKNode findNode(String name)
	{
		BKNode potential=null;
		BKNode yes = null;
		for(int i=0;i<cliqueRoots.size();i++)
		{
			potential=cliqueRoots.get(i);
			if(cliqueRoots.get(i).getShabd().getWord().equals(name))
			{
				yes=potential;
			}
		}
		return yes;
	}

	private void makeGraph()
	{

		String cur = null;
		BKNode curNode = null;
		Shabd insWord=null;


		if(cliqueRoots.size()<=1) return;
		for (int k=0;k<cliqueRoots.size();k++)
		{
			curNode = cliqueRoots.get(k);
			String curWord = cliqueRoots.get(k).getShabd().getWord();

			//System.out.println(" *cur = "+curWord);

			for(int ix=0;ix<cliqueRoots.size();ix++) // lev dist from all other words in this group
			{
				if(k==ix) continue; // skip self --
				insWord = cliqueRoots.get(ix).getShabd();

				String ins = cliqueRoots.get(ix).getShabd().getWord();
				int edge= getLev(curWord, ins);
				if(edge>1)
					continue;
				BKNode toInsert=new BKNode(insWord);
				AdjacentBK adjIns = new AdjacentBK(toInsert, edge);

				curNode.addAdjBKN(adjIns);
			}
		}




			/*
			*/

	}

	public void printTree()
	{
		int i; BKNode cur=null;

		for(i=0;i<cliqueRoots.size();i++) // number of nodes
		{
			cur = cliqueRoots.get(i);
			System.out.println("    >>>>>"+cur.toString());


		}
	}

	public int getLev(String w1, String w2) // calc Levenshtein distance
	{
		int dist=0;
		char [] ar1=w1.toCharArray();
		char [] ar2=w2.toCharArray();
		for(int i=0;i<ar1.length;i++)
		{
			if(ar1[i]!=ar2[i])
				dist++;
		}

		return dist;
	}

}
