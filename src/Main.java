import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws Exception{
		
		/* create a new view */
		MyView view = new MyView();
		
		/* create new Array List of state objects from state info file */
		BufferedReader in = new BufferedReader(new FileReader("stateInfo.txt"));
		String str;
		int i = 0;
		while((str = in.readLine()) != null) {
			String[] info = str.split(";");
			State s = new State(info[1], info[0], Integer.parseInt(info[2]), info[3]);
			view.stateList.add(s);
			i++;
		}
		
		/* render view*/
		view.buildFrame();
	}

}
