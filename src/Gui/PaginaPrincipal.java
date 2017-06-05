package Gui;

import java.awt.EventQueue;

import Models.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class PaginaPrincipal {

	protected JFrame frame;
	private Coche c;
	private JButton btnEntradaVehiculo;
	private JButton btnPagoSalida;
	private JButton btnCajaDia;
	private Calendar calendarioStart;
	private Calendar calendarioStop;
	private String matriculaAux;
	private int horaLlegadaAux;
	private int minutosLlegadaAux;
	private int horaSalidaAux;
	private int minutosSalidaAux;
	private float precioAux;
	private JTextField textFieldMatricula;
	private JLabel lblMatricula;
	private JButton btnRegistrar;
	private JLabel lblLlegada;
	private JLabel lblSalida;
	private JTextField textFieldLlegada;
	private JTextField textFieldSalida;
	private JButton btnPagarYSalir;
	private JLabel lblTotalAPagar;
	private JTextField textFieldTotal;
	private JTextField textFieldImporte;
	private JLabel lblImporte;
	private JLabel lblCambio;
	private JTextField textFieldCambio;
	private float cajaDia = 0.0F;

	// CONSTANTES

	private final float MINUTO_X = 0.06F;
	private final float MINUTO_Y = 0.01F;
	private final float MINUTO_Z = 0.03F;
	private final float MINUTO_FINAL = 0.15F;
	private JButton btnCerrar;

	/**
	 * Create the application.
	 */
	public PaginaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnEntradaVehiculo = new JButton("Entrada");
		btnEntradaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnPagoSalida.setVisible(false);
				btnCajaDia.setVisible(false);
				lblMatricula.setVisible(true);
				textFieldMatricula.setVisible(true);
				btnRegistrar.setVisible(true);
				calendarioStart = new GregorianCalendar();
				horaLlegadaAux = calendarioStart.get(Calendar.HOUR_OF_DAY);
				minutosLlegadaAux = calendarioStart.get(Calendar.MINUTE);

			}
		});
		btnEntradaVehiculo.setBounds(10, 26, 123, 41);
		frame.getContentPane().add(btnEntradaVehiculo);

		btnPagoSalida = new JButton("Pago/Salida");
		btnPagoSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (Container.getListaCoches().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "No ha entrado ningún vehículo aún.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					btnPagoSalida.setVisible(false);
					calendarioStop = new GregorianCalendar();
					horaSalidaAux = calendarioStop.get(Calendar.HOUR_OF_DAY);
					minutosSalidaAux = calendarioStop.get(Calendar.MINUTE);
					precioMinuto();
					mostrarDatos();

				}
			}
		});
		btnPagoSalida.setBounds(161, 26, 111, 41);
		frame.getContentPane().add(btnPagoSalida);

		btnCajaDia = new JButton("Caja del d\u00EDa");
		btnCajaDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (Container.getListaCoches().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "No ha entrado ningún vehículo aún.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
						btnEntradaVehiculo.setVisible(false);
						btnPagoSalida.setVisible(false);
						calcularCaja();
						String auxCaja = Float.toString(cajaDia);
						textFieldTotal.setText(auxCaja);
						btnCajaDia.setVisible(false);
						btnCerrar.setVisible(true);
				}
			}
		});
		btnCajaDia.setBounds(302, 26, 111, 41);
		frame.getContentPane().add(btnCajaDia);

		textFieldMatricula = new JTextField();
		textFieldMatricula.setBackground(Color.WHITE);
		textFieldMatricula.setVisible(false);
		textFieldMatricula.setBounds(98, 98, 131, 27);
		frame.getContentPane().add(textFieldMatricula);
		textFieldMatricula.setColumns(10);

		lblMatricula = new JLabel("Matr\u00EDcula:");
		lblMatricula.setVisible(false);
		lblMatricula.setBounds(21, 98, 67, 27);
		frame.getContentPane().add(lblMatricula);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				matriculaAux = textFieldMatricula.getText();
				if (matriculaAux == null) {
					JOptionPane.showMessageDialog(frame, "Debe rellenar la matrícula.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (matriculaAux.length() != 7) {
						JOptionPane.showMessageDialog(frame, "Formato de matrícula incorrecto. Son 7 dígitos.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						c = new Coche(matriculaAux, horaLlegadaAux, minutosLlegadaAux, precioAux);
						Container.getListaCoches().add(c);
						lblMatricula.setVisible(false);
						textFieldMatricula.setText("");
						textFieldMatricula.setVisible(false);
						btnRegistrar.setVisible(false);
						if (Container.getListaCoches().contains(c)) {
							JOptionPane.showMessageDialog(frame, "Vehículo registrado.", "INFORMATION",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame, "Vehículo no registrado", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						btnPagoSalida.setVisible(true);
						btnCajaDia.setVisible(true);
					}
				}

			}
		});
		btnRegistrar.setVisible(false);
		btnRegistrar.setBounds(161, 213, 111, 37);
		frame.getContentPane().add(btnRegistrar);

		lblLlegada = new JLabel("Llegada:");
		lblLlegada.setVisible(false);
		lblLlegada.setBounds(21, 142, 56, 14);
		frame.getContentPane().add(lblLlegada);

		lblSalida = new JLabel("Salida:");
		lblSalida.setVisible(false);
		lblSalida.setBounds(21, 181, 46, 14);
		frame.getContentPane().add(lblSalida);

		textFieldLlegada = new JTextField();
		textFieldLlegada.setVisible(false);
		textFieldLlegada.setBounds(98, 136, 131, 27);
		frame.getContentPane().add(textFieldLlegada);
		textFieldLlegada.setColumns(10);

		textFieldSalida = new JTextField();
		textFieldSalida.setVisible(false);
		textFieldSalida.setBounds(98, 175, 131, 27);
		frame.getContentPane().add(textFieldSalida);
		textFieldSalida.setColumns(10);

		btnPagarYSalir = new JButton("Pagar y salir");
		btnPagarYSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (textFieldImporte.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Debe ingresar el importe.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String Aux = textFieldImporte.getText();
					float dineroAux = Float.parseFloat(Aux);
					if (dineroAux >= precioAux) {
						float operacion = dineroAux - precioAux;
						String o = Float.toString(operacion);
						textFieldCambio.setText(o);
						textFieldCambio.setEnabled(false);
						textFieldCambio.setBackground(Color.BLACK);
						textFieldImporte.setEnabled(false);
						btnPagarYSalir.setVisible(false);
						btnCerrar.setVisible(true);
						c.set_precio(precioAux);
						JOptionPane.showMessageDialog(frame, "Pago Realizado. Su cambio se mostrará a continuación.",
								"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frame, "Debe insertar una cantidad mayor.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnPagarYSalir.setVisible(false);
		btnPagarYSalir.setBounds(282, 213, 131, 37);
		frame.getContentPane().add(btnPagarYSalir);

		lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setVisible(false);
		lblTotalAPagar.setBounds(324, 78, 89, 14);
		frame.getContentPane().add(lblTotalAPagar);

		textFieldTotal = new JTextField();
		textFieldTotal.setVisible(false);
		textFieldTotal.setBounds(324, 98, 86, 20);
		frame.getContentPane().add(textFieldTotal);
		textFieldTotal.setColumns(10);

		textFieldImporte = new JTextField();
		textFieldImporte.setVisible(false);
		textFieldImporte.setBounds(324, 143, 86, 20);
		frame.getContentPane().add(textFieldImporte);
		textFieldImporte.setColumns(10);

		lblImporte = new JLabel("Importe: ");
		lblImporte.setVisible(false);
		lblImporte.setBounds(324, 128, 89, 14);
		frame.getContentPane().add(lblImporte);

		lblCambio = new JLabel("Cambio:");
		lblCambio.setVisible(false);
		lblCambio.setBounds(324, 166, 89, 14);
		frame.getContentPane().add(lblCambio);

		textFieldCambio = new JTextField();
		textFieldCambio.setVisible(false);
		textFieldCambio.setBounds(324, 181, 86, 20);
		frame.getContentPane().add(textFieldCambio);
		textFieldCambio.setColumns(10);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				PaginaPrincipal pagPrin = new PaginaPrincipal();
				pagPrin.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnCerrar.setVisible(false);
		btnCerrar.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnCerrar);
	}

	/**
	 * Método para mostrar los datos en las casillas correspondientes
	 */
	public void mostrarDatos() {
		String horaAuxLlegada = Integer.toString(horaLlegadaAux);
		String minutosAuxLlegada = Integer.toString(minutosLlegadaAux);
		String horaAuxSalida = Integer.toString(horaSalidaAux);
		String minutosAuxSalida = Integer.toString(minutosSalidaAux);

		lblMatricula.setVisible(true);
		textFieldMatricula.setVisible(true);
		textFieldMatricula.setEnabled(false);
		textFieldMatricula.setText(matriculaAux);

		lblLlegada.setVisible(true);
		textFieldLlegada.setVisible(true);
		textFieldLlegada.setEnabled(false);
		textFieldLlegada.setText(horaAuxLlegada + ":" + minutosAuxLlegada);

		lblSalida.setVisible(true);
		textFieldSalida.setVisible(true);
		textFieldSalida.setEnabled(false);
		textFieldSalida.setText(horaAuxSalida + ":" + minutosAuxSalida);

		btnEntradaVehiculo.setVisible(false);
		btnCajaDia.setVisible(false);

		btnPagarYSalir.setVisible(true);

		lblTotalAPagar.setVisible(true);
		textFieldTotal.setVisible(true);
		textFieldTotal.setEnabled(false);

		lblImporte.setVisible(true);
		textFieldImporte.setVisible(true);

		lblCambio.setVisible(true);
		textFieldCambio.setVisible(true);
		textFieldCambio.setEnabled(false);
		String aux = Float.toString(precioAux);
		textFieldTotal.setText(aux);
	}

	/**
	 * Método para calcular el precio por minuto
	 * @return precioAux
	 */
	public float precioMinuto() {
		float operacionMinutos;

		operacionMinutos = minutosSalidaAux - minutosLlegadaAux;

		if (operacionMinutos <= 30) {
			precioAux += MINUTO_X;
		} else if (operacionMinutos >= 31 && operacionMinutos <= 60) {
			precioAux += MINUTO_Y;
		} else if (operacionMinutos >= 61 && operacionMinutos <= 720) {
			precioAux += MINUTO_Z;
		} else if (operacionMinutos > 720) {
			precioAux += MINUTO_FINAL;
		}
		return precioAux;
	}

	/**
	 * Método para calcular el total de la caja
	 * @return cajaDia
	 */
	public float calcularCaja() {
		lblTotalAPagar.setVisible(true);
		lblTotalAPagar.setText("Caja del día");
		textFieldTotal.setText("");
		textFieldTotal.setVisible(true);
		textFieldTotal.setEnabled(false);

		for (Coche c : Container.getListaCoches()) {
			cajaDia += c.get_precio();
		}
		return cajaDia;
	}

}
