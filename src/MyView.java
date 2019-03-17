import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class MyView extends JFrame {
	
	/* states that are visible to the user */
	public ArrayList<State> stateList = new ArrayList<>();
	
	/* checklist that allows users to select a state*/
	ArrayList<JRadioButton> stateCheckList = new ArrayList<>();
	
	/* List used to filter results when searching*/
	ArrayList<State> filterList = new ArrayList<State>();
	
	/* master list of all created states*/
	ArrayList<State> masterList = new ArrayList<State>();
	
	boolean initialRun = false;
	
	/* flag to keep radio button list from being replicated*/ 
	private boolean addedToList = false;
	
	/* string used to filter through search box*/
	public String s = "";
	
	
	/* index of a selected State in stateList */
	int selectedIndex = 0;
	
	/* Selected State from stateList*/
	State selected;
	
	/* group of radio buttons used to select a state from the list */
	ButtonGroup group;
	
	/* grid used to map out user interface*/
	GridBagConstraints g;
	
	
	/* builds the frame for the user interface */
	public void buildFrame() {
		
		setTitle("My States");
		setSize(700, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		System.out.println("starting a new buildFrame method");
		// field1.requestFocusInWindow();
		states();
	}
	
	/* builds view for adding a state to the list */
	public void addScreen() {
		
		g = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		getContentPane().removeAll();
		getContentPane().repaint();
		
		JLabel name = new JLabel("State Name: ");
		JLabel capitol = new JLabel("State Capitol: ");
		JLabel pop = new JLabel("Population Size: ");
		JLabel flower = new JLabel("State Flower: ");
		
		JTextField stateName = new JTextField(30);
		JTextField capName = new JTextField(30);
		JTextField population = new JTextField(30);
		JTextField flowerName = new JTextField(30);
		
		JButton add = new JButton("Add State");
		JButton cancel = new JButton("Cancel");
		
		g.gridx = 0;
		g.gridy = 0;
		panel.add(name, g);
		g.gridy = 2;
		panel.add(capitol, g);
		g.gridy = 4;
		panel.add(pop, g);
		g.gridy = 6;
		panel.add(flower, g);
		
		g.gridx = 1;
		g.gridy = 0;
		panel.add(stateName, g);
		g.gridy = 2;
		panel.add(capName, g);
		g.gridy = 4;
		panel.add(population, g);
		g.gridy = 6;
		panel.add(flowerName, g);
		
		g.gridy = 8;
		panel.add(add, g);
		g.gridy = 10;
		panel.add(cancel, g);
		
		add(panel);
		revalidate();
		
		/* add action to 'cancel' button */ 
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				states();
			}
		});
		
		/* add action to 'add' button*/
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stateName.getText().equals("") || capName.getText().equals("") || population.getText().equals("") || flowerName.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Please fill all fields");
					addScreen();
				}
				else {
					try {
						State s = new State(stateName.getText(), capName.getText(), Integer.parseInt(population.getText()), flowerName.getText());
						stateList.add(s);
						masterList.add(s);
						states();
					}
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please enter a valid number for population");
					}
				}
			}
		});
	}
	
	/* create view for editing a state */
	public void editScreen() {
		
g = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		getContentPane().removeAll();
		getContentPane().repaint();
		
		JLabel name = new JLabel("State Name: ");
		JLabel capitol = new JLabel("State Capitol: ");
		JLabel pop = new JLabel("Population Size: ");
		JLabel flower = new JLabel("State Flower: ");
		
		JTextField stateName = new JTextField(selected.getName(), 30);
		JTextField capName = new JTextField(selected.getCap(), 30);
		JTextField population = new JTextField(Integer.toString(selected.getPop()), 30);
		JTextField flowerName = new JTextField(selected.getFlower(), 30);
		
		JButton save = new JButton("Save Changes");
		JButton cancel = new JButton("Cancel");
		
		g.gridx = 0;
		g.gridy = 0;
		panel.add(name, g);
		g.gridy = 2;
		panel.add(capitol, g);
		g.gridy = 4;
		panel.add(pop, g);
		g.gridy = 6;
		panel.add(flower, g);
		
		g.gridx = 1;
		g.gridy = 0;
		panel.add(stateName, g);
		g.gridy = 2;
		panel.add(capName, g);
		g.gridy = 4;
		panel.add(population, g);
		g.gridy = 6;
		panel.add(flowerName, g);
		
		g.gridy = 8;
		panel.add(save, g);
		g.gridy = 10;
		panel.add(cancel, g);
		
		add(panel);
		revalidate();
		
		/* add action for 'cancel' button */
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				states();
			}
		});
		
		/* add action to 'save' button */ 
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stateName.getText().equals("") || capName.getText().equals("") || population.getText().equals("") || flowerName.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Please fill all fields");
					editScreen();
				}
				else {
					try {
						selected.setName(stateName.getText());
						selected.setCapitol(capName.getText());
						selected.setPopulation(Integer.parseInt(population.getText()));
						selected.setFlower(flowerName.getText());
						
						states();
					}
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please enter a valid number for population");
					}
				}
			}
		});
	}
	
	
	/* creates the interface for viewing a list of states */
	public void states() {
		
		g = new GridBagConstraints();
		g.insets = new Insets(5,30,5,30);
		g.anchor = GridBagConstraints.WEST;
		
		stateCheckList.clear();
		getContentPane().removeAll();
		getContentPane().repaint();
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel bottomCenterPanel = new JPanel(new GridBagLayout());
		
		panel.add(rightPanel, BorderLayout.EAST);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(bottomCenterPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(centerPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JButton add = new JButton("Add State");
		JButton remove = new JButton("Remove State");
		JButton edit = new JButton("Edit State");
		JButton search = new JButton("Search");

		String[] filters = {"Name A-Z", "Name Z-A", "Population L-H", "Population H-L"};
		JComboBox filter = new JComboBox(filters);
		filter.setSelectedIndex(selectedIndex);
		
		JTextField field1 = new JTextField(s, 20);
		
		JRadioButton r1;
		
		JLabel state = new JLabel("State: ");
		JLabel capitol = new JLabel("Capitol: ");
		JLabel population = new JLabel("Population: ");
		JLabel flower = new JLabel("State Flower: ");
		
		JLabel capName;
		JLabel popName;
		JLabel flowerName;
		
		topPanel.add(filter, g);
		g.gridx = 2;
		topPanel.add(field1, g);
		g.gridx = 6;
		topPanel.add(search, g);
		g.gridx = 0;
		
		
		centerPanel.add(state, g);
		g.gridx = 2;
		centerPanel.add(capitol, g);
		g.gridx = 4;
		centerPanel.add(population, g);
		g.gridx = 6;
		centerPanel.add(flower, g);
		g.gridx = 0;
		
		/* check if button group list has already been created */
		if (addedToList == false) {
			group = new ButtonGroup();
			
			/* creates list of radio buttons used to select a state */ 
			for(int i = 0; i < stateList.size(); i++) {
				r1 = new JRadioButton(""+stateList.get(i).getName());
				r1.setName(stateList.get(i).getName());
				stateCheckList.add(r1);
				group.add(r1);
				
			}
		}
		
		/* populates panel with state information from stateList */
		for(int i = 0; i < stateCheckList.size(); i++) {
			g.gridy = i+1;

			centerPanel.add(stateCheckList.get(i), g);
			capName = new JLabel("" + stateList.get(i).getCap());
			popName = new JLabel("" + stateList.get(i).getPop());
			flowerName = new JLabel("" + stateList.get(i).getFlower());
			g.gridx = 2;
			centerPanel.add(capName, g);
			g.gridx = 4;
			centerPanel.add(popName, g);
			g.gridx = 6;
			centerPanel.add(flowerName, g);
			g.gridx = 0;
			g.gridy = 0;
		}
		
		
		bottomCenterPanel.add(add, g);
		g.gridx = 2;
		bottomCenterPanel.add(remove, g);
		g.gridx = 4;
		bottomCenterPanel.add(edit, g);
		g.gridx = 6;
		
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);
		field1.requestFocusInWindow();
		
		/* check if first time running to create master list of states */
		if(initialRun == false) {
			masterList.addAll(stateList);
			initialRun = true;
		}
		
		
		revalidate();
		
		/* add action to the 'filter' combo box */
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIndex = filter.getSelectedIndex();
				if(selectedIndex == 1) {
					Collections.sort(stateList, State.StateNameSortZA);
				}
				
				if(selectedIndex == 0) {
					Collections.sort(stateList, State.StateNameSortAZ);
				}
				
				if(selectedIndex == 2) {
					Collections.sort(stateList, State.StatePopSortLH);
				}
				
				if(selectedIndex == 3) {
					Collections.sort(stateList, State.StatePopSortHL);
				}

				states();
			}
		});
		
		/* add action to the 'add' button */ 
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScreen();
			}
		});
		
		/* add action to the 'edit' button */
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!group.isSelected(null)){
					for (int i = 0; i < stateCheckList.size(); i++) {
						if(stateCheckList.get(i).isSelected()) {
							selected = stateList.get(i);
						}
					}
					editScreen();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please Select a State to Edit");
					states();
				}
			}
		});
		
		/* add action to the 'remove' button */
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < stateCheckList.size(); i++) {
					if(stateCheckList.get(i).isSelected()) {
						selected = stateList.get(i);
						stateList.remove(i);
						masterList.remove(i);
						JOptionPane.showMessageDialog(null, selected.getName() + " has been removed");
					}
				}
				states();
			}
		});
		
		/* adds actions for test field filtering of the state list */
		field1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();

			}
			
			public void update() {

				s = field1.getText();
				for(int i = 0; i < masterList.size(); i++) {
					if(masterList.get(i).getName().toLowerCase().contains(s)) {
						filterList.add(masterList.get(i));
					}
				}
				stateList.clear();
				stateList.addAll(filterList);
				filterList.clear();
				states();
			}
		});
	}


}
