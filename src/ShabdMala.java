
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ShabdMala implements java.io.Serializable {

    ArrayList<Shabd> lengthMap[]; // words grouped according to lengths
    HashMap<Shabd, Integer> wordList; // full word list, ungrouped
    BKGraph lengthBasedGraph[]; // words grouped according to lengths and sorted
    static final int L = 0;
    ;
	static final int U = 10;
    ;
	static final boolean show = false;
    static final long serialVersionUID = -7588980448693010399L;

    public ShabdMala(String dictionaryPath) {
        wordList = new HashMap<Shabd, Integer>();
        lengthMap = new ArrayList[15]; // by length , array of arraylists
        lengthBasedGraph = new BKGraph[15];
        for (int i = L; i < U; i++) {
            lengthMap[i] = new ArrayList<Shabd>();
        }
        setup(dictionaryPath);


    }

    public ShabdMala(int len, int load) {
        wordList = new HashMap<Shabd, Integer>();
        lengthMap = new ArrayList[15]; // by length , array of arraylists
        lengthBasedGraph = new BKGraph[15];
        for (int i = L; i < U; i++) {
            lengthMap[i] = new ArrayList<Shabd>();
        }

    }

    public ShabdMala(String dictionaryPath, int len) // len = length of query string
    {
        wordList = new HashMap<Shabd, Integer>();
        lengthMap = new ArrayList[15]; // by length , array of arraylists
        lengthBasedGraph = new BKGraph[15];
        for (int i = L; i < U; i++) {
            lengthMap[i] = new ArrayList<Shabd>();
        }
        setup(dictionaryPath, len);
    }

    private void setup(String dictionaryPath) {
        readDictionary(dictionaryPath);
        constructGraphByLength();
    }

    private void setup(String dictionaryPath, int len) {
        readDictionary(dictionaryPath);
        constructGraphForLength(len);
    }

    private void constructGraphForLength(int len) {
        int l = L + 1;
        int u = U;

        int i = len;
        if (lengthMap[i].size() < 1) {
            return;
        }

        if (show) {
            System.out.println("... constructing graph for " + i + "-letter words" + "...");
        }
        lengthBasedGraph[i] = constructGraph(lengthMap[i]);

        if (show) {
            System.out.println("... Printing TREE<START>...");
        }

        lengthBasedGraph[i].printTree();

        if (show) {
            System.out.println("... Printing TREE<END>...");
        }


    }

    private void constructGraphByLength() // don't run --- will exceed memory limits
    {
        int l = L + 1;
        int u = U;

        for (int i = l; i < u; i++) {

            if (lengthMap[i].size() < 1) {
                continue;
            }

            if (show) {
                System.out.println("... constructing graph for " + i + "-letter words" + "...");
            }
            lengthBasedGraph[i] = constructGraph(lengthMap[i]);

        }
    }

    public void printGraph(int len) {
        if (show) {
            System.out.println("... Printing TREE<START>...");
        }
        lengthBasedGraph[len].printTree();
        if (show) {
            System.out.println("... Printing TREE<END>...");
        }

    }

    public void printGraph() {
        int l = L + 1;
        int u = U;
        for (int i = l; i < u; i++) {
            if (lengthBasedGraph[i] == null) {
                continue;
            }

            if (show) {
                System.out.println("... Printing TREE<START>...");
            }
            lengthBasedGraph[i].printTree();
            if (show) {
                System.out.println("... Printing TREE<END>...");
            }
        }
    }

    private BKGraph constructGraph(ArrayList<Shabd> sm) // based on 1 char change
    {

        BKGraph bkt = new BKGraph(sm);


        return bkt;
    }

    public void readDictionary(String dictionaryPath) {
        if (show) {
            System.out.println("... loading dictionary, please wait...");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(dictionaryPath)));
            String line = br.readLine();
            int num = 0;
            while (line != null) {
                num++;
                Shabd word = new Shabd(line, num);
                wordList.put(word, num);
                lengthMap[word.getLength()].add(word);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (show) {
            System.out.println("... read complete [Dictionary loaded]...");
        }
    }

    public void printDictionary(int skip) // skip = num words to skip while displaying
    {
        if (show) {
            System.out.println("... <start> [printing skip-th record]...");
        }
        for (int i = 0; i < wordList.size(); i += 1 + skip) {
            System.out.println(wordList.get(i));
        }
        if (show) {
            System.out.println("... <end>...");
        }
    }

    public void printDictionaryByLength(int skip) {
        if (show) {
            System.out.println("... <start> ...");
        }
        for (int i = L; i < U; i++) {
            if (show) {
                System.out.println("... <Length = " + i + "> ...");
            }
            for (int j = 0; j < lengthMap[i].size(); j += 1 + skip) {
                System.out.println(lengthMap[i].get(j).getWord());
            }
        }
        if (show) {
            System.out.println("... <end>...");
        }
    }

    public void find(String source, String target, int len) {

        BKGraph lgb = lengthBasedGraph[len];

        BKNode start = lgb.findNode(source);
        BKNode end = lgb.findNode(target);


        LinkedList<BKNode> route = getDirections(lgb, start, end);
//		for(int i=0;i<route.size();i++)
//		{
//			System.out.println(route.get(i).getShabd().getWord()+" --- ");
//		}
        //BFS();
    }

    public LinkedList getDirections(BKGraph lgb, BKNode start, BKNode finish) {
        HashMap<String, Boolean> vis = new HashMap<String, Boolean>();
        HashMap<BKNode, Integer> dist = new HashMap<BKNode, Integer>();
        HashMap<BKNode, BKNode> prev = new HashMap<BKNode, BKNode>();
        HashMap<String, String> prevWord = new HashMap<String, String>();
        LinkedList directions = new LinkedList();
        Queue<BKNode> q = new LinkedList();
        BKNode current = start;
        boolean fail = false;

        q.add(current);
        vis.put(current.getShabd().getWord(), true);
        dist.put(current, 0); // starting


        BKNode lastNode = null;
        while (!q.isEmpty()) {
            current = q.remove();
            int d = dist.get(current);

            current = lgb.findNode(current.getShabd().getWord());
            //System.out.println(q.toString());
            if (show) {
                System.out.println(d + "[cur]" + current.getShabd().getWord());
            }
            if (current.getShabd().getWord().equals(finish.getShabd().getWord())) {
                break;
            } else {
                for (BKNode node : current.getAdjBKNListAsNodes()) {
                    if (!vis.containsKey(node.getShabd().getWord())) {
                        q.add(node);

                        vis.put(node.getShabd().getWord(), true); // mark
                        prev.put(node, current); // previous
                        prevWord.put(node.getShabd().getWord(), current.getShabd().getWord());
                        //System.out.println("enQQQQQQQ---"+node.getShabd().getWord()+" -- PREVVVVV --- "+prev.get(node).getShabd().getWord());
                        if (show) {
                            System.out.print("[p-c].." + node.getShabd().getWord() + " TO " + current.getShabd().getWord());
                        }
                        dist.put(node, d + 1);
                    }
                }

                if (show) {
                    System.out.println();
                }
            }


        }

        if (!current.getShabd().getWord().equals(finish.getShabd().getWord())) {
            System.out.println("can't reach destination");
            fail = true;
        }


        if (show) {
            System.out.print("[HERE]..");
        }

//                String s = finish.getShabd().getWord();
//                System.out.print("[showing].."+s);
//                while(!s.equals(start.getShabd().getWord())){
//	    	//node = lgb.findNode(node.getShabd().getWord());
//	        directions.add(s);
//                s = prevWord.get(s);
//                }

        if (show) {
            System.out.println("\n[PATH]\n");
        }
        String qr = finish.getShabd().getWord();
        String value = "";
        System.out.println(qr);
        while (value != null) {
            String key = qr;

            if (!prevWord.containsKey(key)) {
                break;
            }

            value = prevWord.get(key).toString();


            //System.out.println("QR = "+qr);
            if (key.equals(qr)) {
                System.out.println(value);
                qr = value;
            }

        }


//	    System.out.println(d);
        //directions.reverse();
        return directions;
    }
}
