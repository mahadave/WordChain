import java.util.ArrayList;


public class WordNode
{
	Shabd word;
	ArrayList <Shabd> adjacent;

	public WordNode()
	{
		word=new Shabd();
		adjacent = new ArrayList<Shabd>();
	}
	
	public void addAdjNode(Shabd n)
	{
		adjacent.add(n);
	}
	
	public ArrayList<Shabd> getAdjNodeList()
	{
		return adjacent;
	}
	
	
}
