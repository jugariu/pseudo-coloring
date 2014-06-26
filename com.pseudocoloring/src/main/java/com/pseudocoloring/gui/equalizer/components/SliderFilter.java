package com.pseudocoloring.gui.equalizer.components;

import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jidesoft.swing.RangeSlider;
import com.pseudocoloring.gui.panel.ImagePanel;
import com.pseudocoloring.processing.equalizer.EqualizeProcess;

public class SliderFilter extends RangeSlider implements ChangeListener {
	private static final long serialVersionUID = 1L;
	private ImagePanel imagePanel;
	private EqualizeProcess process = null;
	private String type = "";

	public SliderFilter(String type, ImagePanel imagePanel, int x, int y) {
		this.imagePanel = imagePanel;
		this.type = type;
		initializeUI(x, y);
	}

	private void initializeUI(int x, int y) {
		this.setOrientation(SwingConstants.VERTICAL);
		this.setMaximum(255);
		this.setMinimum(0);
		this.setHighValue(255);
		this.setLowValue(0);
		this.setBounds(x, y, 40, 200);
		this.setLabelTable(this.createStandardLabels(25));
		this.addChangeListener(this);

		this.setVisible(true);
	}

	public void stateChanged(ChangeEvent e) {
		if(process == null){
			process = new EqualizeProcess(getImagePanel());
		}
		if (type.equals("RED"))
			process.equalizeImageRedValue(this.getLowValue(),
					this.getHighValue());
		if (type.equals("GREEN"))
			process.equalizeImageGreenValue(this.getLowValue(),
					this.getHighValue());
		if (type.equals("BLUE"))
			process.equalizeImageBlueValue(this.getLowValue(),
					this.getHighValue());
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
}
