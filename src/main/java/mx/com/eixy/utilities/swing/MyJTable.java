package mx.com.eixy.utilities.swing;

import java.util.List;
import java.util.stream.Stream;

import javax.swing.JTable;

import mx.com.eixy.swing.table.MyTableModel;
import mx.com.eixy.util.MyList;

public class MyJTable<T> {
	
	private JTable jTable;
	private List<T> list;	
	private MyList<T> myList;
	private MyTableModel<T> myTableModel;

	
	
	public MyJTable (JTable jTable, Class<T> clas) {
		
		this.jTable = jTable;
				
		myList = new MyList<>();		
		myTableModel = new MyTableModel<>(myList, clas);		
		jTable.setModel(myTableModel);
	}
	
	



	public JTable getjTable() {
		return jTable;
	}



	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}



	public List<T> getList() {
		return list;
	}



	public void setList(List<T> list) {
		this.list = list;
	}



	public MyList<T> getMyList() {
		return myList;
	}



	public void setMyList(MyList<T> myList) {
		this.myList = myList;
	}



	public MyTableModel<T> getMyTableModel() {
		return myTableModel;
	}



	public void setMyTableModel(MyTableModel<T> myTableModel) {
		this.myTableModel = myTableModel;
	}





	public void add(T t) {		
		myList.add(t);
	}
	
	public Stream<T> stream() {
		return myList.stream();
	}
	
	

	
	

}
