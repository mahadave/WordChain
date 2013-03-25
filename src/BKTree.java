import java.util.ArrayList;


public class BKTree
{

	int items;
	int height;
	ArrayList<Shabd> wl;
	ArrayList<BKNode> tree;
	static final boolean show=true;

	public BKTree()
	{
		tree = new ArrayList<BKNode>() ;
	}

	public BKTree(ArrayList<Shabd> wordList)
	{
		tree = new ArrayList<BKNode>() ;
		wl = wordList;
		makeTree();
	}

	private void makeTree()
	{
		int i;
		String cur = null;
		BKNode curNode = null;
		Shabd insWord=null;
		//for(i=0;i<wl.size();i++); // O(n^2)
		i=0;
		{

			insWord = wl.get(i);
			if(tree.size()==0) // uninit
			{
				curNode =new BKNode(insWord); 
				tree.add(curNode);

				if (show) System.out.println("added : "+tree.get(tree.size()-1).getShabd().getWord());
			}

			for(int k=0;k<wl.size();k++) // lev dist from all other words in this group
			{
				if(k==i) continue; // skip self --
				cur = wl.get(k).getWord();
				int edge= getLev(insWord.getWord(), cur);
				curNode.addAdjBKN(new AdjacentBK(new BKNode(insWord), edge));
			}
		}

	}

	public void printTree()
	{
		int i; BKNode cur=null;
		
		for(i=0;i<tree.size();i++) // number of nodes
		{
			cur = tree.get(i);
			System.out.println("Tree-Print for "+cur.getShabd().getWord());

		}
	}

	public int getLev(String w1, String w2) // calc Levenshtein distance
	{
		int dist=0;
		for(int i=0;i<w1.length();i++)
		{
			if(w1.charAt(i)!=w2.charAt(i))
				dist++;
		}
		
		return dist;
	}

}
