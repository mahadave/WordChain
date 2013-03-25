import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	static final int SAVE=  0;
	static final int LOAD=  1;
	static final int RUN=  2;
	static final String dictionary = "dic.txt";
	static final int runMode =  LOAD;
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		int len;
		ShabdMala sm;
		String source,target;

		if(runMode==LOAD)
		{
			source = sc.next();
			target = sc.next();

			source = source.toLowerCase();
			target = target.toLowerCase();



			len=preChecks(source,target);
			if(len<0)
			{
				exit(source,target);
			}
			if(preChecks2(source,target))
			{

			}
			String fName="dic_"+len+".data";
			FileInputStream f_in = new FileInputStream(fName);

			// Read object using ObjectInputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);

			// Read an object
			sm = new ShabdMala(len, LOAD);
			System.out.println("LOADING..."+fName);
			sm = (ShabdMala) obj_in.readObject();
			System.out.println("LOADING COMPLETE");
			//sm.printGraph();


			sm.find(source,target,len);

		}

		if(runMode==SAVE)
		{
			for(len=1;len<10;len++){
				sm = new ShabdMala(dictionary,len);
				FileOutputStream f_out;
				ObjectOutputStream obj_out;

				try{
					f_out = new FileOutputStream("dic_"+len+".data");
					System.out.println("SAVING..."+"dic_"+len+".data");
					obj_out = new ObjectOutputStream (f_out);
					obj_out.writeObject ( sm );
					System.out.println("SAVED...");
					}catch(Exception e) {e.printStackTrace();}
			}

		}


		//sm.printDictionary(10);
		//sm.printDictionaryByLength(10);



	}
	private static void exit(String source, String target)
	{
		// TODO Auto-generated method stub
		System.out.println("No solution found from ("+source+") to ("+target+")");
		System.exit(0);
	}
	private static int preChecks(String source, String target) {
		// TODO Auto-generated method stub
		int a=source.length();
		int b=target.length();
		if(a==b)
			return a;
		return -1;
	}

	private static boolean preChecks2(String source, String target) {
		// TODO Auto-generated method stub

		if(source.equalsIgnoreCase(target))
			return true;
		return false;
	}
}
