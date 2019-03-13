import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		
		MyView view = new MyView();
		
		BufferedReader in = new BufferedReader(new FileReader("stateInfo.txt"));
		String str;
		int i = 0;
		while((str = in.readLine()) != null) {
			String[] info = str.split(";");
			State s = new State(info[1], info[0], Integer.parseInt(info[2]), info[3]);
			view.stateList.add(s);
			i++;
		}
		
		// System.out.println(view.stateList.get(0).getName());
		
		// Collections.sort(view.stateList, State.StateNameSortZA);
		
		// System.out.println(view.stateList.get(0).getName());
		
		view.buildFrame();
	}

}
