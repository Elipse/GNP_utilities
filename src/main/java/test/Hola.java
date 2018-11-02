package test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Hola extends JFrame {

    private JPanel contentPane;
    private Thread t;
    private ExecutorService executorService;
    private MyThread myThread;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Hola frame = new Hola();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public Hola() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);

	JButton btnNewButton = new JButton("Start");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		Hola.this.actionPerformed(arg0);
	    }
	});
	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
	contentPane.add(btnNewButton);

	JButton btnNewButton_1 = new JButton("Restart");
	btnNewButton_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Hola.this.reset();
	    }
	});
	contentPane.add(btnNewButton_1);

	JButton btnStop = new JButton("Stop");
	btnStop.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Hola.this.stop();
	    }
	});

	textField = new JTextField();
	contentPane.add(textField);
	textField.setColumns(10);
	contentPane.add(btnStop);
    }

    protected void stop() {
	myThread.doStop();
    }

    protected void reset() {
	int tmp = Integer.parseInt(textField.getText());
	myThread.setTimer(tmp, 10, Hola.this::actualizaElProgressBar);
    }

    protected void actionPerformed(ActionEvent arg0) {
	myThread = new MyThread(200, 10, Hola.this::actualizaElProgressBar);
	myThread.start();
    }

    private void actualizaElProgressBar(boolean mode) {
	System.out.println("dependiendo del modo actualizo: " + mode + " "
		+ Thread.currentThread());
    }
}
