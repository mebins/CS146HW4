import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryRepresentation {

	public static void main(String[] args)
	{
		Map<Integer, List<Integer>> graph = new TreeMap<>();
		Collection<Integer> row_1 = new ArrayList<>();
		Collection<Integer> row_2 = new ArrayList<>();
		Collection<Integer> row_3 = new ArrayList<>();
		Collection<Integer> row_4 = new ArrayList<>();
		row_1.add(2);
		row_1.add(3);
		row_2.add(1);
		row_3.add(2);
		row_3.add(4);
		row_4.add(1);
		row_4.add(3);
		graph.put(1,new ArrayList<Integer>(row_1));
		graph.put(2,new ArrayList<Integer>(row_2));
		graph.put(3,new ArrayList<Integer>(row_3));
		graph.put(4,new ArrayList<Integer>(row_4));
		
		System.out.println(graph.get(1));
	}
}
