import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEncript;
	private JTextField tfDescript;
	private JButton btnDescript;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\willian.de\\Desktop\\encrypt-icon-29.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Digite a palavra: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(24, 25, 165, 48);
		contentPane.add(lblNewLabel);

		tfEncript = new JTextField();
		tfEncript.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfEncript.setBounds(24, 79, 165, 54);
		contentPane.add(tfEncript);
		tfEncript.setColumns(10);

		tfDescript = new JTextField();
		tfDescript.setFont(new Font("Tahoma", Font.BOLD, 12));
		tfDescript.setEnabled(false);
		tfDescript.setEditable(false);
		tfDescript.setColumns(10);
		tfDescript.setBounds(362, 79, 165, 54);
		contentPane.add(tfDescript);

		JButton btnEncript = new JButton("Encriptografar");
		btnEncript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!tfEncript.getText().isBlank()) {
					try {

						// AES, RC2,RC4,RC5, IDEA, BLOWFISH
						KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
						SecretKey sk = kg.generateKey();
						Cipher cipher;
						cipher = Cipher.getInstance("Blowfish");
						cipher.init(Cipher.ENCRYPT_MODE, sk);

						String senha = tfEncript.getText();
						byte[] criptografia = senha.getBytes();
		

						System.out.println(criptografia);
						tfDescript.setText(criptografia.toString());

						btnDescript.setEnabled(true);

					} catch (Exception e1) {
						// TODO: handle exception
					}

				}

				else {
					JOptionPane.showMessageDialog(null, "Digite uma palavra","Erro", JOptionPane.WARNING_MESSAGE);

				}
			}

		});
		btnEncript.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEncript.setBounds(24, 156, 162, 39);
		contentPane.add(btnEncript);

		JButton btnDescript = new JButton("Descriptografar");
		btnDescript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					KeyGenerator kg = KeyGenerator.getInstance("Blowfish");

					SecretKey sk = kg.generateKey();
					Cipher cipher;
					cipher = Cipher.getInstance("Blowfish");
					cipher.init(Cipher.ENCRYPT_MODE, sk);
					String senha = tfEncript.getText();
					byte[] criptografia = cipher.doFinal(senha.getBytes());

					cipher.init(Cipher.DECRYPT_MODE, sk);

					byte[] descriptografia = cipher.doFinal(criptografia);
					String dsc = new String(descriptografia);
					tfEncript.setText(" ");
					if (tfEncript.getText() != null) {
						System.out.println("Entrou aqui");
						tfEncript.setText("Descriptografado: " + dsc);
						System.out.println("Descriptografado: " + dsc);
						tfEncript.setEditable(false);
						tfEncript.setEnabled(false);
						btnEncript.setEnabled(false);
						btnDescript.setEnabled(false);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDescript.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDescript.setBounds(362, 156, 162, 39);
		contentPane.add(btnDescript);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tfEncript.setText(" ");
				tfEncript.setEnabled(true);
				tfEncript.setEditable(true);

				tfDescript.setText(" ");
				btnEncript.setEnabled(true);
				btnDescript.setEnabled(true);

			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpar.setBounds(182, 238, 162, 39);
		contentPane.add(btnLimpar);
	}
}
