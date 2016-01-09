package pl.edu.agh.farfromthesun.algorithm;

import java.time.LocalDate;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class ParametersDatepicker extends JDatePickerImpl implements
		ParametersInput {

	private static final long serialVersionUID = -8354086480368212522L;

	public ParametersDatepicker(JDatePanelImpl dateInstantPanel) {
		super(dateInstantPanel);
	}

	@Override
	public Object getInputValue() {
		LocalDate date;
		DateModel<?> model = this.getModel();
		
		if(model.getValue() != null){
			date = LocalDate.of(model.getYear(), model.getMonth(), model.getDay());
		} else {
			date = LocalDate.now();
		}
		
		return date;
	}

}
