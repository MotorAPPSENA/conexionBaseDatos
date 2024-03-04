package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoUsuario;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnLimpiar;
	private JTable tblUsuarios;
	DaoUsuario dao = new DaoUsuario();
	private JButton btnAgregar;
	private JTextField txtContraseña;
	private JTextField txtUsuario;
	private JLabel lblid;
	DefaultTableModel modelo = new DefaultTableModel();
	ArrayList<Usuario> lista;
	int fila = -1;
	Usuario usuario = new Usuario();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actualizarTabla() {

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista = dao.consultaUsuarios();
		for (Usuario u : lista) {
			Object user[] = new Object[4];
			user[0] = u.getId();
			user[1] = u.getUsuario();
			user[2] = u.getConstraseña();
			user[3] = u.getNombre();
			modelo.addRow(user);
		}
		tblUsuarios.setModel(modelo);

	}

	public void limpiar() {
		lblid.setText("");
		txtUsuario.setText("");
		txtContraseña.setText("");
		txtNombre.setText("");
	}

	public vUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 476);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icono.jpg")));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("CRUD USUARIOS");
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 34, 106, 43);
		contentPane.add(lblNewLabel);

		lblid = new JLabel("0");
		lblid.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblid.setBounds(178, 34, 77, 43);
		contentPane.add(lblid);

		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsuario.setBounds(36, 90, 106, 43);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("CONTRASEÑA:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblContrasea.setBounds(36, 144, 106, 43);
		contentPane.add(lblContrasea);

		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNombre.setBounds(36, 198, 106, 43);
		contentPane.add(lblNombre);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(169, 102, 170, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(169, 156, 170, 20);
		contentPane.add(txtContraseña);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(169, 210, 170, 20);
		contentPane.add(txtNombre);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUsuario.getText().equals("") || txtContraseña.getText().equals("")
							|| txtNombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
						return;
					}
					Usuario user = new Usuario();
					user.setUsuario(txtUsuario.getText());
					user.setConstraseña(txtContraseña.getText());
					user.setNombre(txtNombre.getText());
					if (dao.insertarUsuaruio(user)) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(169, 256, 92, 23);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion = JOptionPane.showConfirmDialog(null,"¿Estás seguro de eliminar este Usuario?",
							"ELIMINAR USUARIO",JOptionPane.YES_NO_OPTION);
						if(opcion == 0) {
							if (dao.eliminarUsuario(usuario.getId())&&usuario.getId()>0) {
						actualizarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE!!");
						}else {
							JOptionPane.showMessageDialog(null, "ERROR");
						}
							} 
						}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnEliminar.setBounds(169, 291, 92, 23);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
							if (txtUsuario.getText().equals("") || txtContraseña.getText().equals("")
								|| txtNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					return;
				}
				usuario.setUsuario(txtUsuario.getText());
				usuario.setConstraseña(txtContraseña.getText());
				usuario.setNombre(txtNombre.getText());
				if (dao.editarUsuaruio(usuario)) {
					actualizarTabla();
					limpiar();
					JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE");
				} else {
					JOptionPane.showMessageDialog(null, "ERROR");
						}
					}catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnActualizar.setBounds(169, 325, 92, 23);
		contentPane.add(btnActualizar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(169, 356, 92, 23);
		contentPane.add(btnLimpiar);

		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblUsuarios.getSelectedRow();
				usuario = lista.get(fila);
				lblid.setText("" + usuario.getId());
				txtUsuario.setText(usuario.getUsuario());
				txtContraseña.setText(usuario.getConstraseña());
				txtNombre.setText(usuario.getNombre());
			}
		});
		tblUsuarios.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		tblUsuarios.setBounds(383, 34, 352, 373);
		contentPane.add(tblUsuarios);
		modelo.addColumn("ID");
		modelo.addColumn("USUARIO");
		modelo.addColumn("CONTRASEÑA");
		modelo.addColumn("NOMBRE");
		actualizarTabla();
	}
	}
