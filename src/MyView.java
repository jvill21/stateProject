import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MyView extends JFrame {
	
	/* states that are visible to the user */
	public ArrayList<State> stateList = new ArrayList<>();
	
	/* checklist that allows users to select a state*/
	ArrayList<JRadioButton> stateCheckList = new ArrayList<>();
	
	private boolean addedToList = false;
	
	private String selectedState;
	private String selectedCapitol;
	private int selectedPopulation;
	private String selectedFlower;
	
	int selectedIndex = 0;
	
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
		states();
		// addScreen();
		// login();
	}
	
	
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
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				states();
			}
		});
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State s = new State(stateName.getText(), capName.getText(), Integer.parseInt(population.getText()), flowerName.getText());
				stateList.add(s);
				states();
			}
		});
	}
	
	
	public void editScreen() {
		
g = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		getContentPane().removeAll();
		getContentPane().repaint();
		
		JLabel name = new JLabel("State Name: ");
		JLabel capitol = new JLabel("State Capitol: ");
		JLabel pop = new JLabel("Population Size: ");
		JLabel flower = new JLabel("State Flower: ");
		
		JTextField stateName = new JTextField(selectedState, 30);
		JTextField capName = new JTextField(selectedCapitol, 30);
		JTextField population = new JTextField(Integer.toString(selectedPopulation), 30);
		JTextField flowerName = new JTextField(selectedFlower, 30);
		
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
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				states();
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				State s = new State(stateName.getText(), capName.getText(), Integer.parseInt(population.getText()), flowerName.getText());
				stateList.add(s);
				states();
			}
		});
	}
	
	/* creates the interface for viewing a list of states */
	public void states() {
		
		// getContentPane().removeAll();
		// getContentPane().repaint();
		
		g = new GridBagConstraints();
		g.insets = new Insets(5,30,5,30);
		g.anchor = GridBagConstraints.WEST;
		
		stateCheckList.clear();
		getContentPane().removeAll();
		getContentPane().repaint();
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel bottomCenterPanel = new JPanel(new GridBagLayout());
		
		// panel.setPreferredSize(new Dimension(700, 400 ));
		
		panel.add(rightPanel, BorderLayout.EAST);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(bottomCenterPanel, BorderLayout.CENTER);
		
		
		
		
		// add(panel);
		
		JScrollPane scrollPane = new JScrollPane(centerPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// JScrollPane scrollPane = new JScrollPane(panel1);

		JButton add = new JButton("Add State");
		JButton remove = new JButton("Remove State");
		JButton edit = new JButton("Edit State");
		JButton search = new JButton("Search");

		String[] filters = {"Name A-Z", "Name Z-A", "Population L-H", "Population H-L"};
		JComboBox filter = new JComboBox(filters);
		filter.setSelectedIndex(selectedIndex);
		
		JTextField field1 = new JTextField(20);
		
		JCheckBox c1;
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
		
		
		if (addedToList == false) {
			ButtonGroup group = new ButtonGroup();
			
			
			for(int i = 0; i < stateList.size(); i++) {
				r1 = new JRadioButton(""+stateList.get(i).getName());
				r1.setName(stateList.get(i).getName());
				stateCheckList.add(r1);
				group.add(r1);
			}
			// addedToList = true;
		}
		
		for(int i = 0; i < stateCheckList.size(); i++) {
			g.gridy = i+1;
			// stateCheckList.get(i).setName(stateList.get(i).getName());
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
		// add(panel2, BorderLayout.CENTER);
		//add(bottomCenterPanel);
		// pack();
		revalidate();
		
		
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedIndex = filter.getSelectedIndex();
				if(selectedIndex == 1) {
					Collections.sort(stateList, State.StateNameSortZA);
					System.out.println("Z-A");
					// filter.setSelectedIndex(selectedIndex);
				}
				
				if(selectedIndex == 0) {
					Collections.sort(stateList, State.StateNameSortAZ);
					System.out.println("A-Z");
				}
				
				if(selectedIndex == 2) {
					Collections.sort(stateList, State.StatePopSortLH);
					System.out.println("L-H");
				}
				
				if(selectedIndex == 3) {
					Collections.sort(stateList, State.StatePopSortHL);
					System.out.println("H-L");
				}

				// stateCheckList.clear();
				states();
			}
		});
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScreen();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < stateCheckList.size(); i++) {
					if(stateCheckList.get(i).isSelected()) {
						selectedState = stateList.get(i).getName();
						selectedCapitol = stateList.get(i).getCap();
						selectedPopulation = stateList.get(i).getPop();
						selectedFlower = stateList.get(i).getFlower();
					}
				}
				
				editScreen();
			}
		});
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < stateCheckList.size(); i++) {
					if(stateCheckList.get(i).isSelected()) {
						stateList.remove(i);
					}
				}
				states();
			}
		});
	}


}
