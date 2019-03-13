import java.util.Comparator;

public class State {

	// fields for a state object
		private String name;
		private String capitol;
		private int population;
		private String state_flower;
		
		
		public State() {
			
		}
		
		public State(String name, String cap, int pop, String flower) {
			this.name = name;
			this.capitol = cap;
			this.population = pop;
			this.state_flower = flower;
		}
		
		
		// setter methods to assign new values to a State
		public void setName(String newName) {
			this.name = newName;
		}
		
		public void setCapitol(String newCap) {
			this.capitol = newCap;
		}
		
		public void setPopulation(int newPop) {
			this.population = newPop;
		}
		
		public void setFlower(String newFlower) {
			this.state_flower = newFlower;
		}
		
		
		// getter methods to receive values of a State
		public String getName() {
			return name;
		}
		
		public String getCap() {
			return capitol;
		}
		
		public int getPop() {
			return population;
		}
		
		public String getFlower() {
			return state_flower;
		}
		
		/* Comparator for sorting list by Name Z-A*/ 
		public static Comparator<State> StateNameSortZA = new Comparator<State>() {
			public int compare(State s1, State s2) {
				String stateName1 = s1.getName().toUpperCase();
				String stateName2 = s2.getName().toUpperCase();
				
				return stateName2.compareTo(stateName1);
			}
		};
		
		/* Comparator for sorting list by Name A-Z */ 
		public static Comparator<State> StateNameSortAZ = new Comparator<State>() {
			public int compare(State s1, State s2) {
				String stateName1 = s1.getName().toUpperCase();
				String stateName2 = s2.getName().toUpperCase();
				
				return stateName1.compareTo(stateName2);
			}
		};
		
		/* Comparator for sorting list by Name Z-A*/ 
		public static Comparator<State> StatePopSortHL = new Comparator<State>() {
			public int compare(State s1, State s2) {
				int statePop1 = s1.getPop();
				int statePop2 = s2.getPop();
				
				return statePop2 - statePop1;
			}
		};

		/* Comparator for sorting list by Name Z-A*/ 
		public static Comparator<State> StatePopSortLH = new Comparator<State>() {
			public int compare(State s1, State s2) {
				int statePop1 = s1.getPop();
				int statePop2 = s2.getPop();
				
				return statePop1 - statePop2;
			}
		};
}
