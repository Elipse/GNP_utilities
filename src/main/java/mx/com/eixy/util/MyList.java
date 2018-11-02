package mx.com.eixy.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MyList<E> extends ArrayList<E> {

	
	private static final long serialVersionUID = 1L;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	@Override
	public boolean add(E row) {
		boolean b = super.add(row);
		
		this.pcs.firePropertyChange("row", null, row);
		return b;
	}

	@Override
	public E remove(int index) {
		E row = super.remove(index);
		this.pcs.firePropertyChange("row", row, null);
		return row;
	}

	@Override
	public void clear() {
		super.clear();
		this.pcs.firePropertyChange("row", null, null);
	}
}
