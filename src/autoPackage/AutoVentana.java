package autoPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JList;

@SuppressWarnings("serial")
public class AutoVentana extends JFrame {
	
	NuevoAuto nuevo;
	@SuppressWarnings("rawtypes")
	JList listaAutos;
	@SuppressWarnings("rawtypes")
	DefaultListModel modeloListaAutos;
	@SuppressWarnings("rawtypes")
	JList listaResumen;
	@SuppressWarnings("rawtypes")
	DefaultListModel modeloListaResumen;
	
	ArrayList<Auto> autoList = new ArrayList<Auto>();
	HashMap<AutoKey, Resumen> resumen = new HashMap<AutoKey, Resumen>();

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoVentana frame = new AutoVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AutoVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 227, 239);
		contentPane.add(scrollPane);
		
		listaAutos = new JList();
        modeloListaAutos = new DefaultListModel();
        listaAutos.setModel(modeloListaAutos);
		scrollPane.setViewportView(listaAutos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(247, 11, 177, 159);
		contentPane.add(scrollPane_1);
		
		listaResumen = new JList();
		modeloListaResumen = new DefaultListModel();
        listaResumen.setModel(modeloListaResumen);
		scrollPane_1.setViewportView(listaResumen);
		
		nuevo = new NuevoAuto(this);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo.setVisible(true);
			}
		});
		btnAgregar.setBounds(293, 201, 89, 23);
		contentPane.add(btnAgregar);
	}
	
	@SuppressWarnings("unchecked")
	public void getAuto() {
		AutoKey key;
		
		String marca = String.valueOf(this.nuevo.getMarcaBox().getSelectedItem());
		String modelo = this.nuevo.getModeloField().getText();
		int precio = Integer.parseInt(this.nuevo.getPrecioField().getText());
		int año = Integer.parseInt(String.valueOf(this.nuevo.getAñoBox().getSelectedItem()));
		int mes = Integer.parseInt(String.valueOf(this.nuevo.getMesBox().getSelectedItem()));
		
		this.autoList.add(new Auto(marca, modelo, precio, año, mes));
		Collections.sort(this.autoList);
		
		key = new AutoKey(marca, modelo, año);
		if (!this.resumen.containsKey(key)) {
			this.resumen.put(key, new Resumen(0,Integer.MAX_VALUE,0));
		}
		
		this.modeloListaAutos.addElement(marca + " " + modelo + " " + año + " " + mes + " " + precio);
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerResumen() {
		int max = 0;
		int min = 0;
		int sum = 0;
		double promedio = 0;
		int cant = 0;
		AutoKey autoActual = new AutoKey();
		
		boolean first = true;
		
		this.modeloListaResumen.clear();
		
		for (Auto auto : this.autoList) {
			if (!auto.getKey().equals(autoActual)) {
				if (!first) {
					promedio = sum / cant;
					this.resumen.put(autoActual, new Resumen(max, min, promedio));
					this.modeloListaResumen.addElement(autoActual.getMarca() + " " + autoActual.getModelo() + " " + autoActual.getAño());
					this.modeloListaResumen.addElement(min + " " + promedio + " " + max);
					sum = 0;
					cant = 0;
				} else {
					first = false;
				}
				autoActual = auto.getKey();
			}
			
			min = this.resumen.get(autoActual).getMin();
			max = this.resumen.get(autoActual).getMax();
			sum += auto.getPrecio();
			cant++;
			
			if (auto.getPrecio() < min) {
				min = auto.getPrecio();
			}
			
			if (auto.getPrecio() > max) {
				max = auto.getPrecio();
			}
		}
		
		promedio = sum / cant;
		this.resumen.put(autoActual, new Resumen(max, min, promedio));
		this.modeloListaResumen.addElement(autoActual.getMarca() + " " + autoActual.getModelo() + " " + autoActual.getAño());
		this.modeloListaResumen.addElement(min + " " + promedio + " " + max);
	}
}
