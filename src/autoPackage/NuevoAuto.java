package autoPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NuevoAuto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField modeloField;
	private JTextField precioField;
	private JComboBox<String> marcaBox;
	private JComboBox<Integer> mesBox;
	private JComboBox<Integer> añoBox;
	
	AutoVentana autoVentana;
	
	public JTextField getModeloField() {
		return modeloField;
	}

	public JTextField getPrecioField() {
		return precioField;
	}

	public JComboBox<String> getMarcaBox() {
		return marcaBox;
	}

	public JComboBox<Integer> getMesBox() {
		return mesBox;
	}

	public JComboBox<Integer> getAñoBox() {
		return añoBox;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoAuto dialog = new NuevoAuto(new AutoVentana());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoAuto(AutoVentana autoVentana) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAo = new JLabel("Año");
		lblAo.setBounds(10, 11, 46, 14);
		contentPanel.add(lblAo);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(245, 11, 46, 14);
		contentPanel.add(lblMes);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 65, 46, 14);
		contentPanel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 113, 46, 14);
		contentPanel.add(lblModelo);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 158, 46, 14);
		contentPanel.add(lblPrecio);
		
		modeloField = new JTextField();
		modeloField.setBounds(125, 110, 86, 20);
		contentPanel.add(modeloField);
		modeloField.setColumns(10);
		
		precioField = new JTextField();
		precioField.setBounds(125, 155, 86, 20);
		contentPanel.add(precioField);
		precioField.setColumns(10);
		
		marcaBox = new JComboBox<String>();
		marcaBox.setBounds(125, 62, 86, 20);
		marcaBox.addItem("Fiat");
		marcaBox.addItem("Ford");
		marcaBox.addItem("Peugeot");
		marcaBox.addItem("Chevrolet");
		marcaBox.addItem("Honda");
		marcaBox.addItem("Volkswagen");
		marcaBox.addItem("Renault");
		contentPanel.add(marcaBox);
		
		añoBox = new JComboBox<Integer>();
		añoBox.setBounds(125, 8, 86, 20);
		for (int i = 2017 ; i >= 1990 ; i--) {
			añoBox.addItem(i);
		}
		contentPanel.add(añoBox);
		
		mesBox = new JComboBox<Integer>();
		mesBox.setBounds(301, 8, 78, 20);
		for (int i = 12 ; i >= 1 ; i--) {
			mesBox.addItem(i);
		}
		
		this.autoVentana = autoVentana;
		
		contentPanel.add(mesBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (camposValidos()) {
							autoVentana.getAuto();
							autoVentana.obtenerResumen();
							//limpiarVentana();
						} else {
							JOptionPane.showMessageDialog(null, "Debe ingresar un valor en todos los campos");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limpiarVentana();
						cerrar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean camposValidos() {
		return !this.modeloField.getText().equals("") && !this.precioField.getText().equals("");
	}
	
	public void limpiarVentana() {
		this.modeloField.setText("");
		this.precioField.setText("");
		this.marcaBox.setSelectedIndex(0);
		this.mesBox.setSelectedIndex(0);
		this.añoBox.setSelectedIndex(0);
	}
	
	public void cerrar() {
		this.dispose();
	}
}
