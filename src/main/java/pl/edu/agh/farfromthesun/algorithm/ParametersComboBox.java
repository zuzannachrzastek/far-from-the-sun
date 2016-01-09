package pl.edu.agh.farfromthesun.algorithm;

import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import pl.edu.agh.farfromthesun.algorithm.model.Nameable;

public class ParametersComboBox extends JComboBox<String> implements ParametersInput {
	private static final long serialVersionUID = 1L;
	private List<String> names;
	private Nameable[] items;

	public ParametersComboBox(Nameable[] items){
		super();
		this.items = items;
		names = new LinkedList<String>();
		for(Nameable i : items){
			names.add(i.getName());
		}
		this.setModel(new DefaultComboBoxModel<String>(names.toArray(new String[0])));
	}
	
	
	@Override
	public Nameable getInputValue() {
		String name = (String) this.getSelectedItem();
		for(Nameable i : items){
			if(name == i.getName()){
				return i;
			}
		}
		return null;
	}

}
