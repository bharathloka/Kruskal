import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Kruskals 
{
	// Making a Edge class which has two vertices as strings and distance between them as integer ///
	public static class Edge implements Comparable<Edge>
	{
		int dist ;
		String vertex1 , vertex2 ;
		
		Edge(int d , String s1 , String s2)
		{
			this.dist = d ;
			this.vertex1 = s1 ;
			this.vertex2 = s2 ;
		}
		
		public int compareTo(Edge e)//overriding comareTo method to compare edges
		{
			if (this.dist < e.dist)
				return -1 ;
			else if (this.dist > e.dist)
				return 1 ;
			
			else 
				return 0 ;
		}
	}


	public void kruskal (String path)
	{
		ArrayList<Edge> EdgesList = new ArrayList<>();
		ArrayList<String> VerticesList = new ArrayList<>();
    	
    	BufferedReader fileReader = null;
        int TotalDistance = 0;
         
        try
        {
            String line = "";
            
            fileReader = new BufferedReader(new FileReader(path)); // Put the CSV File Name here which is to be executed //
             
           
            while ((line = fileReader.readLine()) != null)
            {
                
                String[] entries = line.split(",");
                VerticesList.add(entries[0]);
                	EdgesList.add( new Edge(Integer.parseInt(entries[2]),entries[0],entries[1] ));
                	for (int i =3 ; i<entries.length ; i++)
                	{
                		EdgesList.add(new Edge(Integer.parseInt(entries[i+1]),entries[ 0],entries[ i]));
                		i++ ;
                	}              
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        

        //  Kruskals Algorithm 
		int edgesAccepted = 0 ;
		DisjSets ds = new DisjSets(VerticesList.size());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (Edge e : EdgesList)
		{
			pq.add(e);							// Adding  edge to priority queue 
		}
		
		while (edgesAccepted < VerticesList.size()-1 )
		{
			Edge	e = pq.poll();				//polling function as a substitute to deleteMin()
			 
			 
			if (ds.find(VerticesList.indexOf(new String(e.vertex1))) != ds.find(VerticesList.indexOf(new String(e.vertex2))))
			{
				edgesAccepted ++ ;
				ds.union(ds.find(VerticesList.indexOf(new String(e.vertex1))),ds.find(VerticesList.indexOf(new String(e.vertex2))));
				TotalDistance = TotalDistance + e.dist ;
				System.out.println(e.vertex1+" , "+e.vertex2+", distance= "+e.dist);
			}
		
		}
		System.out.println("Total Distance ="+TotalDistance);
		

	}
    public static void main( String [ ] args )
    {
    	    	Kruskals kr = new Kruskals();
    	    	kr.kruskal(args[0]);   	

    }

}